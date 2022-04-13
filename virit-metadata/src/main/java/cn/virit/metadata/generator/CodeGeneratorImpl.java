package cn.virit.metadata.generator;

import cn.virit.metadata.constants.CodeLevel;
import cn.virit.metadata.model.ModelConfig.Domain;
import cn.virit.metadata.model.ModelInfo;
import cn.virit.metadata.model.TypeMapper;
import cn.virit.metadata.utils.FileUtils;
import cn.virit.metadata.utils.IOUtils;
import cn.virit.metadata.utils.VelocityUtils;
import cn.virit.metadata.utils.VelocityUtils.VelocityContextBuilder;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 代码生成器实现类
 *
 * @author Virit
 * @since 1.0
 */
public class CodeGeneratorImpl implements CodeGenerator {

    private static final String CONFIG_FILE = "generator/codegenerator.config.yml";
    private final Yaml yaml = new Yaml();
    private final CodeGenConfigs genConfigs;
    private final Set<String> fileNames = new HashSet<>();
    private final Map<String, String> TEMPLATES = new HashMap<>();

    public CodeGeneratorImpl() {
        genConfigs = yaml.loadAs(CodeGeneratorImpl.class.getClassLoader().getResourceAsStream(CONFIG_FILE),
            CodeGenConfigs.class);
    }

    @Override
    public void generateCode(Domain domain, ModelInfo model, String dir) {

        FileUtils.createIfNotExistsDir(domain.getBasePackageDir());
        genConfigs.getTemplates().forEach((name, config) -> {

            CodeLevel codeLevel = config.getCodeLevel();
            Set<CodeLevel> modelCodeLevels = model.getCodeLevels();
            // 未配置相关代码级别，就不会生成相关级别的代码
            if (codeLevel != null && modelCodeLevels != null && modelCodeLevels.size() != 0 && !modelCodeLevels.contains(codeLevel)) {
                return;
            }

            var context = new CodeGenContext();
            context.setDomain(domain);
            context.setModel(model);
            context.setUtils(Utils.getInstance());
            context.setGenConfigs(genConfigs);
            context.setGenConfig(config);
            context.setTypeMapper(TypeMapper.getInstance());
            TypeMapper.getInstance().setContext(context);
            Utils.getInstance().setContext(context);
            context.setResourceDir("src/main/resources");

            var velocityContext = new VelocityContextBuilder()
                .set("ctx", context)
                .build();
            var fileName = dir + "/" + VelocityUtils.render(config.getTarget(), velocityContext);
            File file = new File(fileName);
            if (file.exists()) {
                if (config.isCover()) {
                    if (!fileNames.contains(fileName)) {
                        file.delete();
                    }
                } else {
                    return;
                }
            }

            var template = loadTemplate(config.getTemplate());
            if (fileNames.contains(fileName)) {
                FileUtils.appendFile(fileName, VelocityUtils.render(template, velocityContext));
            } else {
                VelocityUtils.render(template, velocityContext, fileName);
            }
            if (!fileNames.contains(fileName)) {
                fileNames.add(fileName);
            }
        });
    }

    private String loadTemplate(String path) {

        if (!TEMPLATES.containsKey(path)) {
            TEMPLATES.put(path, IOUtils.loadAsString(CodeGeneratorImpl.class.getClassLoader().getResourceAsStream(path)));
        }
        return TEMPLATES.get(path);
    }

    public static class CodeGenConfigs {

        private Map<String, CodeGenConfig> templates;

        public Map<String, CodeGenConfig> getTemplates() {
            return templates;
        }

        public void setTemplates(Map<String, CodeGenConfig> templates) {
            this.templates = templates;
        }
    }
}
