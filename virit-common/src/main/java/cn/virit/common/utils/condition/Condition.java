package cn.virit.common.utils.condition;

import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Condition extends BaseCondition<Condition> {

    private Map<String, Object> params;

    private List<Sort> sorts;

    private Page page;

    private Condition() {
    }

    public static Condition newInstance() {
        return new Condition();
    }

    public List<Sort> getSorts() {
        return sorts;
    }

    public void setSorts(List<Sort> sorts) {
        this.sorts = sorts;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return getSqlString();
    }

    public String getSqlString() {

        StringBuilder sb = new StringBuilder();
        params = new HashMap<>();
        AtomicInteger counter = new AtomicInteger();
        toSqlString(getItems(), sb, params, counter);
        return sb.toString();
    }

    private void toSqlString(List<ConditionItem> items, StringBuilder sb, Map<String, Object> params, AtomicInteger counter) {

        for (int i = 0; i < items.size(); i++) {
            var item = items.get(i);
            if (i != 0) {
                sb.append(item.getLogical()).append(" ");
            }
            if (CollectionUtils.isNotEmpty(item.getItems())) {
                if (item.getItems().size() > 1) {
                    sb.append("(");
                }
                toSqlString(item.getItems(), sb, params, counter);
                if (item.getItems().size() > 1) {
                    sb.append(")");
                }
            } else {
                sb.append(item.toSqlString(params, counter));
            }
            if (i != items.size() - 1) {
                sb.append(" ");
            }
        }
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public String getOrderBy() {
        if (getSorts() != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("ORDER BY ");
            for (int i = 0; i < getSorts().size(); i++) {
                Sort sort = getSorts().get(i);
                sb.append(sort.getField()).append(" ").append(sort.getType());
                if (i != getSorts().size() - 1) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }
        return "";
    }

    public String getLimit() {
        if (page != null) {
            return "LIMIT " + ((page.getIndex() - 1) * page.getSize()) + ", " + page.getSize();
        }
        return "";
    }
}
