package cn.virit.domain.base.gen.constants;

/**
 * 特性
 */
public enum FeatureFields {

    /**
     * ID
     */
    ID("id"),

    /**
     * 创建人
     */
    CREATED_USER_ID("createdUserId"),

    /**
     * 修改人
     */
    MODIFIED_USER_ID("modifiedUserId"),

    /**
     * 创建时间
     */
    CREATED_TIME("createdTime"),

    /**
     * 修改时间
     */
    MODIFIED_TIME("modifiedTime"),

    /**
     * 名称
     */
    NAME("name"),

    /**
     * 特性定义
     */
    SCHEMA_DATA("schemaData");

    private String name;

    FeatureFields(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
