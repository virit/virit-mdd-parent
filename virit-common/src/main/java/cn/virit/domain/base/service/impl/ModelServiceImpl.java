package cn.virit.domain.base.service.impl;

import cn.virit.common.base.BaseEntity;
import cn.virit.common.base.BaseService;
import cn.virit.common.utils.condition.Condition;
import cn.virit.common.utils.condition.ConditionItem;
import cn.virit.common.utils.condition.Logical;
import cn.virit.common.utils.condition.Operator;
import cn.virit.domain.base.entity.Feature;
import cn.virit.domain.base.entity.Model;
import cn.virit.domain.base.gen.constants.ModelColumns;
import cn.virit.domain.base.repo.FeatureRepository;
import cn.virit.domain.base.service.ModelService;
import cn.virit.metadata.constants.FeatureName;
import cn.virit.metadata.model.ModelInfo;
import cn.virit.metadata.utils.YamlUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ModelServiceImpl
 */
@Service
public class ModelServiceImpl extends BaseService<Model> implements ModelService {

    private final FeatureRepository featureRepository;

    private final Yaml yaml = YamlUtils.getYaml();

    public ModelServiceImpl(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    @Override
    public ModelInfo getModelInfo(BaseEntity entity) {

        return getModelInfo(entity.getModelDomain(), entity.getModelName());
    }

    @Override
    public ModelInfo getModelInfo(String domain, String modelName) {

        String id = domain + '.' + modelName;
        Model model = this.findById(id);
        if (model == null) {
            return null;
        }
        return yaml.loadAs(model.getSchemaData(), ModelInfo.class);
    }

    @Override
    public List<ModelInfo> getAllModelInfo() {

        return this.findAll().stream().map(it -> yaml.loadAs(it.getSchemaData(), ModelInfo.class))
            .collect(Collectors.toList());
    }

    @Override
    @Cacheable(cacheNames = "BASE_MODEL_INFO", key = "#domain + '.' + #modelName")
    public Model getByDomainModel(String domain, String modelName) {
        return super.findById(domain + "." + modelName);
    }

    @Override
    public Model getByName(String modelName) {

        var condition = Condition.newInstance()
            .item(new ConditionItem(Logical.AND, ModelColumns.NAME.getName(), Operator.EQ, List.of(modelName)));
        return this.repository.findOneByCondition(condition);
    }

    @Override
    public ModelInfo getModelInfo(String modelName) {

        return yaml.loadAs(getByName(modelName).getSchemaData(), ModelInfo.class);
    }

    @Override
    public ModelInfo getFeature(FeatureName name) {

        Feature feature = featureRepository.selectById(name.name());
        return yaml.loadAs(feature.getSchemaData(), ModelInfo.class);
    }
}
