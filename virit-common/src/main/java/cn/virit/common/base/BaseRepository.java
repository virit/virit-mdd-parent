package cn.virit.common.base;

import cn.virit.common.feature.FeatureAdapter;
import cn.virit.common.utils.SpringContextUtils;
import cn.virit.common.utils.condition.Condition;
import cn.virit.common.utils.condition.ConditionItem;
import cn.virit.common.utils.condition.Logical;
import cn.virit.common.utils.condition.Operator;
import cn.virit.domain.base.repo.ModelRepository;
import cn.virit.domain.base.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class BaseRepository<T extends BaseEntity> implements IRepository<T> {

    protected ObjectGetter objectGetter;
    private BaseMapper<T> mapper;

    @Autowired
    public void setObjectGetter(ObjectGetter objectGetter) {
        this.objectGetter = objectGetter;
    }

    @Override
    public void insert(T entity) {

        var adapters = getFeatureAdapters(entity);
        for (var adapter : adapters) {
            adapter.beforeRepoInsert(entity);
        }
        mapper.insert(entity);
        insertChildren(entity);
        for (var adapter : adapters) {
            adapter.afterRepoInsert(entity);
        }
    }

    @Override
    public void update(T entity) {
        var adapters = getFeatureAdapters(entity);
        for (var adapter : adapters) {
            adapter.beforeRepoUpdate(entity);
        }
        mapper.update(entity);
        for (var adapter : adapters) {
            adapter.afterRepoUpdate(entity);
        }
    }

    @Override
    public void deleteById(String id) {
        mapper.deleteById(id);
    }

    @Override
    public T selectById(String id) {

        T entity = mapper.selectById(id);
        if (entity != null) {
            queryChildren(entity);
        }
        return entity;
    }

    private void queryChildren(T entity) {

        if (!(this instanceof ModelRepository)) {
            var modelService = SpringContextUtils.getBean(ModelService.class);
            var model = modelService.getByDomainModel(entity.getModelDomain(), entity.getModelName());
            var modelInfo = model.getModelInfo();
            var children = modelInfo.getChildren();
            if (children != null) {
                for (var name : children.keySet()) {
                    var child = children.get(name);
                    var referModelName = child.getRefer();
                    var childModel = modelService.getByDomainModel(entity.getModelDomain(), referModelName);
                    Class<? extends IRepository<? extends BaseEntity>> repoClazz;
                    try {
                        repoClazz = (Class<? extends IRepository<? extends BaseEntity>>) Class.forName(childModel.getRepoClass());
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    IRepository<? extends BaseEntity> childRepo = SpringContextUtils.getBean(repoClazz);
                    var condition = Condition.newInstance()
                        .item(new ConditionItem(Logical.AND, child.getReferColumn(), Operator.EQ, List.of(entity.getId())));
                    List<? extends BaseEntity> childData = childRepo.findByCondition(condition);
                    entity.setChildData(name, childData);
                }
            }
        }
    }

    private void insertChildren(T entity) {

        if (!(this instanceof ModelRepository)) {
            var modelService = SpringContextUtils.getBean(ModelService.class);
            var model = modelService.getByDomainModel(entity.getModelDomain(), entity.getModelName());
            var modelInfo = model.getModelInfo();
            var children = modelInfo.getChildren();
            if (children != null) {
                for (var name : children.keySet()) {
                    var child = children.get(name);
                    var referModelName = child.getRefer();
                    var childModel = modelService.getByDomainModel(entity.getModelDomain(), referModelName);
                    Class<? extends IRepository<? extends BaseEntity>> repoClazz;
                    try {
                        repoClazz = (Class<? extends IRepository<? extends BaseEntity>>) Class.forName(childModel.getRepoClass());
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    IRepository<BaseEntity> childRepo = (IRepository<BaseEntity>) SpringContextUtils.getBean(repoClazz);
                    List<? extends BaseEntity> childData = entity.getChildData(name);
                    if (childData != null) {
                        for (BaseEntity data : childData) {
                            data.set(child.getReferField() + "Id", entity.getId());
                            childRepo.insert(data);
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<T> findAll() {
        return mapper.findAll();
    }

    @Override
    public List<T> findByCondition(Condition condition) {
        return mapper.findByCondition(condition.getSqlString(), condition.getParams()
            , condition.getOrderBy(), condition.getLimit());
    }

    @Override
    public T findOneByCondition(Condition condition) {
        return mapper.findOneByCondition(condition.getSqlString(), condition.getParams());
    }

    @Autowired
    public void setMapper(BaseMapper<T> mapper) {
        if (this.mapper == null) {
            this.mapper = mapper;
        }
    }

    private List<FeatureAdapter> getFeatureAdapters(T entity) {

        var modelService = SpringContextUtils.getBean(ModelService.class);
        var modelInfo = modelService.getModelInfo(entity);
        var features = modelInfo.getFeatures();
        return features.stream().map(featureName -> {
                var featureInfo = modelService.getFeature(featureName);
                var adapter = featureInfo.getAdapter();
                if (adapter != null) {
                    Class<?> adapterClazz = null;
                    try {
                        adapterClazz = Class.forName(adapter);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    return (FeatureAdapter) SpringContextUtils
                        .getBean(adapterClazz);
                }
                return null;
            }).filter(Objects::nonNull)
            .collect(Collectors.toList());
    }
}
