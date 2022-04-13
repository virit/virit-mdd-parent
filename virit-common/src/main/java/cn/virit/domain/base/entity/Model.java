package cn.virit.domain.base.entity;

import cn.virit.domain.base.gen.entity.BaseModel;
import cn.virit.metadata.model.ModelInfo;
import cn.virit.metadata.utils.YamlUtils;

/**
 * 模型
 * <p>
 * 模型
 */
public class Model extends BaseModel {

    private ModelInfo modelInfo;

    @Override
    public void setSchemaData(String schemaData) {

        modelInfo = YamlUtils.getYaml().loadAs(schemaData, ModelInfo.class);
        super.setSchemaData(schemaData);
    }

    public ModelInfo getModelInfo() {
        return modelInfo;
    }
}
