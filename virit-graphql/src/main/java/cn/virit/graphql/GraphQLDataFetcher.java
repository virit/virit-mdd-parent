package cn.virit.graphql;

import cn.virit.common.base.BaseEntity;
import cn.virit.common.base.BaseService;
import cn.virit.common.utils.SpringContextUtils;
import cn.virit.common.utils.condition.Condition;
import cn.virit.common.utils.condition.ConditionItem;
import cn.virit.common.utils.condition.Logical;
import cn.virit.common.utils.condition.Operator;
import cn.virit.domain.base.service.ModelService;
import cn.virit.metadata.generator.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * GraphQLDataFetcher
 *
 * @author Virit
 * @since 1.0
 */
@Component
public class GraphQLDataFetcher implements DataFetcher<Object> {

    private static final String FILTER = "filter";
    private final ObjectMapper objectMapper;
    private final ModelService modelService;

    public GraphQLDataFetcher(ModelService modelService, ObjectMapper objectMapper) {
        this.modelService = modelService;
        this.objectMapper = objectMapper;
    }

    @Override
    public Object get(DataFetchingEnvironment env) throws Exception {

        var filterObject = env.getArgument(FILTER);
        Condition filter = null;
        if (filterObject != null) {
            filter = objectMapper.readValue(objectMapper.writeValueAsString(filterObject), Condition.class);
        }
        String parentName = "";
        var parentType = env.getParentType();
        if (parentType instanceof GraphQLObjectType) {
            parentName = ((GraphQLObjectType) parentType).getName();
        }
        var field = env.getField();
        String objectType;
        if ("Query".equals(parentName)) {
            objectType = field.getName();
            var model = modelService.getByName(objectType);
            BaseService<?> service = (BaseService<?>) SpringContextUtils.getBean(Class.forName(model.getServiceClass()));
            return service.findAll(filter);
        } else {
            BaseEntity entity = env.getSource();
            var modelInfo = modelService.getModelInfo(parentName);
            var fieldName = field.getName();
            if (modelInfo.getChildren() != null && modelInfo.getChildren().containsKey(fieldName)) {
                var child = modelInfo.getChildren().get(fieldName);
                objectType = child.getRefer();
                var model = modelService.getByName(objectType);
                String referField = child.getReferField();
                String fullFieldName = Utils.getInstance().humpToLine(referField) + "_id";
                var conditionItem = new ConditionItem(Logical.AND, fullFieldName, Operator.EQ, List.of(entity.getId()));
                Condition condition = Condition.newInstance().item(conditionItem);
                if (filter != null && CollectionUtils.isNotEmpty(filter.getItems())) {
                    condition.item(ConditionItem.newInstance(Logical.AND).addItems(filter.getItems()));
                }
                BaseService<?> service = (BaseService<?>) SpringContextUtils.getBean(Class.forName(model.getServiceClass()));
                return service.findAll(condition);
            } else {
                var referField = modelInfo.getFields().get(fieldName);
                objectType = (String) referField.getProperties().get("objectType");
                var model = modelService.getByName(objectType);
                BaseService<?> service = (BaseService<?>) SpringContextUtils.getBean(Class.forName(model.getServiceClass()));
                String id = (String) entity.get(field.getName() + "Id");
                return service.findById(id);
            }
        }
    }
}
