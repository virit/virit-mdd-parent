package cn.virit.metadata.generator;

import cn.virit.metadata.model.ModelConfig.Domain;
import cn.virit.metadata.model.ModelInfo;

/**
 * 代码生成器
 *
 * @author Virit
 * @since 1.0
 */
public interface CodeGenerator {

    void generateCode(Domain domain, ModelInfo model, String dir);
}
