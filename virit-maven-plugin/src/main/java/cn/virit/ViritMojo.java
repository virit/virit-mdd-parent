package cn.virit;

import cn.virit.metadata.generator.CodeGenerator;
import cn.virit.metadata.generator.CodeGeneratorImpl;
import cn.virit.metadata.generator.InitDataBuilder;
import cn.virit.metadata.generator.InitDataBuilderImpl;
import cn.virit.metadata.model.ModelConfig.Domain;
import cn.virit.metadata.model.ModelInfo;
import cn.virit.metadata.model.ModelLoader;
import cn.virit.metadata.model.ModelLoaderImpl;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @goal build
 * @phase process-sources
 */
public class ViritMojo extends AbstractMojo {

    private static ModelLoader modelLoader = new ModelLoaderImpl();
    private static CodeGenerator codeGenerator = new CodeGeneratorImpl();
    private static InitDataBuilder initDataBuilder = new InitDataBuilderImpl();

    private static void buildDir(String dir) {
        System.out.println(dir);
        System.out.println("start build project");
        Map<Domain, List<ModelInfo>> domainModels = modelLoader.loadModelsFromProject(dir);
        domainModels.forEach((domain, models) -> {
            for (ModelInfo model : models) {
                codeGenerator.generateCode(domain, model, dir);
            }
        });
        initDataBuilder.build(dir);
        System.out.println("build success");
    }

    public static void main(String[] args) {

        String[] dirs = new String[]{
            "E:/code/java/virit/virit-common",
            "E:/code/java/virit/westore-server"
        };
        for (String dir : dirs) {
            buildDir(dir);
        }
    }

    public void execute() throws MojoExecutionException {

        var dir = new File("").getAbsolutePath().replaceAll("\\\\", "/");
        System.out.println(dir);
        buildDir(dir);
    }
}
