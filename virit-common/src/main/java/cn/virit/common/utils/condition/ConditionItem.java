package cn.virit.common.utils.condition;

import cn.virit.metadata.utils.VelocityUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ConditionItem extends BaseCondition<ConditionItem> {

    private Logical logical;

    private String field;

    private Operator operator;

    private List<Object> values;

    public ConditionItem() {

    }

    public ConditionItem(Logical logical, List<ConditionItem> items) {
        this.logical = logical;
        this.items = items;
    }

    public ConditionItem(Logical logical, String field, Operator operator, List<Object> values) {
        this.logical = logical;
        this.field = field;
        this.operator = operator;
        this.values = values;
    }

    public static ConditionItem newInstance(Logical logical) {

        var item = new ConditionItem();
        item.logical = logical;
        return item;
    }

    public Logical getLogical() {
        return logical;
    }

    public void setLogical(Logical logical) {
        this.logical = logical;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }

    String toSqlString(Map<String, Object> params, AtomicInteger counter) {

        if (CollectionUtils.isNotEmpty(items)) {
            return super.toString();
        } else {
            String template = operator.getTemplate();
            List<String> paramKeys = new ArrayList<>();
            for (Object value : values) {
                String key = "p" + counter.incrementAndGet();
                params.put(key, value);
                paramKeys.add("params." + key);
            }
            var context = new VelocityUtils.VelocityContextBuilder()
                .set("item", this)
                .set("params", paramKeys)
                .build();
            return VelocityUtils.render(template, context);
        }
    }
}
