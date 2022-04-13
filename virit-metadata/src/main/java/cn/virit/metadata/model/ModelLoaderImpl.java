package cn.virit.metadata.model;

import cn.virit.metadata.constants.FeatureName;
import cn.virit.metadata.constants.FieldType;
import cn.virit.metadata.model.ModelConfig.Domain;
import cn.virit.metadata.utils.FileUtils;
import cn.virit.metadata.utils.IOUtils;
import cn.virit.metadata.utils.YamlUtils;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 模型加载器实现类
 *
 * @author Virit
 * @since 1.0
 */
public class ModelLoaderImpl implements ModelLoader {

    private static final String MODEL_CONFIG_FILE = "resources://model.config.yml";
    private final Yaml yaml = YamlUtils.getYaml();

    @Override
    public ModelInfo loadModel(String name, String content, boolean feature) {

        ModelInfo model = yaml.loadAs(content, ModelInfo.class);
        model.setFeature(feature);
        model.setName(name);
        setFieldName(model);
        loadFeatures(model);
        modelPreHandle(model);
        return model;
    }

    @Override
    public ModelInfo loadModel(String name, InputStream in, boolean feature) {

        return loadModel(name, IOUtils.loadAsString(in), feature);
    }

    @Override
    public ModelInfo loadModelFromResource(String name, String resourcePath) {

        return loadModelFromResource(name, resourcePath, false);
    }

    public ModelInfo loadModelFromResource(String name, String resourcePath, boolean feature) {

        InputStream in = ModelLoaderImpl.class.getClassLoader().getResourceAsStream(resourcePath);
        ModelInfo model = loadModel(name, in, feature);
        model.setFeature(feature);
        return model;
    }

    private void setFieldName(ModelInfo model) {

        model.getFields().forEach((fieldName, field) -> {
            field.setName(fieldName);
        });
        if (model.getChildren() != null) {
            model.getChildren().forEach((fieldName, child) -> {
                child.setName(fieldName);
            });
        }
    }

    private void loadFeatures(ModelInfo model) {

        List<FeatureName> features = model.getFeatures();
        if (!model.isFeature() && features == null) {
            model.setFeatures(new ArrayList<>());
            features = model.getFeatures();
        }
        if (features != null) {
            // 每个模型都会有Base的特性
            if (!model.isFeature() && !features.contains(FeatureName.BASE)) {
                features.add(FeatureName.BASE);
            }
            Map<String, Field> mergedFields = new LinkedHashMap<>();
            for (FeatureName feature : features) {
                var featureName = getFeatureName(feature.name());
                var res = new ClassPathResource("features/Feature" + featureName + ".yml");
                ModelInfo featureEntity = null;
                try {
                    featureEntity = loadModel(null, res.getInputStream(), true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                mergedFields.putAll(featureEntity.getFields());
                if (model.getSuperTypes() == null) {
                    model.setSuperTypes(new LinkedHashSet<>());
                }
                if (featureEntity.getSuperTypes() != null) {
                    model.getSuperTypes().addAll(featureEntity.getSuperTypes());
                }
            }
            mergedFields.putAll(model.getFields());
            model.setFields(mergedFields);
        }
    }

    private void modelPreHandle(ModelInfo modelInfo) {

        Map<String, Field> newFields = new LinkedHashMap<>();

        for (String key : modelInfo.getFields().keySet()) {
            Field field = modelInfo.getFields().get(key);
            newFields.put(key, field);
            // 对于关联对象字段，需要创建对应的数据库字段field
            if (field.getType() == FieldType.OBJECT) {
                Field referField = new Field();
                String referFieldName = field.getName() + "Id";
                referField.setName(referFieldName);
                referField.setTitle(field.getTitle() + "(关联字段)");
                referField.setType(FieldType.STRING);
                Map<String, Object> properties = new LinkedHashMap<>();
                properties.put("length", 64);
                referField.setProperties(properties);
                newFields.put(referFieldName, referField);
            }
        }
        modelInfo.setFields(newFields);
        if (modelInfo.getChildren() != null) {
            for (String name : modelInfo.getChildren().keySet()) {
                Child child = modelInfo.getChildren().get(name);
                child.setName(name);
            }
        }
    }

    private String getFeatureName(String enumName) {

        StringBuilder sb = new StringBuilder();
        for (String word : enumName.split("_")) {
            sb.append(word.substring(0, 1)).append(word.substring(1).toLowerCase());
        }
        return sb.toString();
    }

    @Override
    public Map<Domain, List<ModelInfo>> loadModelsFromProject(String dir) {

        Map<Domain, List<ModelInfo>> modelsMap = new HashMap<>();
        ModelConfig modelConfig = null;
        InputStream in = null;
        try {
            in = IOUtils.getFileStream(dir, MODEL_CONFIG_FILE);
            modelConfig = yaml.loadAs(in, ModelConfig.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.close(in);
        }

        String tempDir = null;
        String modelDir = null;
        if ("project".equals(modelConfig.getRepository())) {
            modelDir = dir + "/src/main/resources/models";
        } else {
            try {
                tempDir = System.getProperty("java.io.tmpdir") + File.separator + UUID.randomUUID() + File.separator;
                FileUtils.createIfNotExistsDir(tempDir);
                Process process = Runtime.getRuntime().exec("git clone " + modelConfig.getRepository(), null, new File(tempDir));
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            modelDir = tempDir + FileUtils.getDirName(modelConfig.getRepository());
        }

        String finalModelDir = modelDir;

        modelConfig.getDomains().forEach((name, domain) -> {

            List<ModelInfo> models = new ArrayList<>();
            var domainModelPath = FileUtils.concatPath(finalModelDir, File.separator, name);
            var domainModelDir = new File(domainModelPath);
            if (domain.getModels().contains("_ALL")) {
                for (var modelFile : domainModelDir.listFiles()) {
                    if (!modelFile.isFile()) {
                        continue;
                    }
                    var modelName = FileUtils.getFileName(modelFile.getName());
                    var model = loadModel(modelName, FileUtils.openFileStream(modelFile), true);
                    models.add(model);
                }
            }
            domain.setModelInfos(models);
            modelsMap.put(domain, models);
        });
        if (tempDir != null) {
            FileUtils.removeDir(new File(tempDir));
        }
        return modelsMap;
    }
}
