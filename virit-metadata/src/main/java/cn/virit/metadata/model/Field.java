package cn.virit.metadata.model;

import cn.virit.metadata.constants.FieldType;

import java.util.Map;

/**
 * 模型字段
 * <p>
 * 表示一个模型中的字段
 *
 * @author Virit
 * @since 1.0
 */
public class Field {

    private String name;

    private String title;

    private FieldType type;

    private Map<String, Object> properties;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
