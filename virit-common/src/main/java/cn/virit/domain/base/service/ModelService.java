package cn.virit.domain.base.service;

import cn.virit.common.base.BaseEntity;
import cn.virit.common.base.IService;
import cn.virit.domain.base.entity.Model;
import cn.virit.metadata.constants.FeatureName;
import cn.virit.metadata.model.ModelInfo;

import java.util.List;

/**
 * ModelService
 */
public interface ModelService extends IService<Model> {

    ModelInfo getFeature(FeatureName feature);

    ModelInfo getModelInfo(BaseEntity baseEntity);

    ModelInfo getModelInfo(String domain, String modelName);

    List<ModelInfo> getAllModelInfo();

    Model getByDomainModel(String domain, String modelName);

    Model getByName(String modelName);

    ModelInfo getModelInfo(String modelName);
}
