package cn.virit.common.utils.condition;

import java.util.ArrayList;
import java.util.List;

public class BaseCondition<T extends BaseCondition<?>> {

    protected List<ConditionItem> items = new ArrayList<>();

    public List<ConditionItem> getItems() {
        return items;
    }

    public void setItems(List<ConditionItem> items) {
        this.items = items;
    }

    public T item(ConditionItem item) {
        items.add(item);
        return (T) this;
    }

    public T addItems(List<ConditionItem> items) {
        this.items.addAll(items);
        return (T) this;
    }
}
