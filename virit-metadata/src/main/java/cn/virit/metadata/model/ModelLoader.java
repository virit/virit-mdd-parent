package cn.virit.metadata.model;

import cn.virit.metadata.model.ModelConfig.Domain;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 模型加载器
 *
 * @author Virit
 * @since 1.0
 */
public interface ModelLoader {

    ModelInfo loadModel(String name, String content, boolean feature);

    ModelInfo loadModel(String name, InputStream in, boolean feature);

    ModelInfo loadModelFromResource(String name, String resourcePath);

    Map<Domain, List<ModelInfo>> loadModelsFromProject(String dir);
}
