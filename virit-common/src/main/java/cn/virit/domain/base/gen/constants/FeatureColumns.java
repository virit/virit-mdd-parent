package cn.virit.domain.base.gen.constants;

/**
 * 特性
 */
public enum FeatureColumns {

    /**
     * ID
     */
    ID("id"),

    /**
     * 创建人
     */
    CREATED_USER_ID("created_user_id"),

    /**
     * 修改人
     */
    MODIFIED_USER_ID("modified_user_id"),

    /**
     * 创建时间
     */
    CREATED_TIME("created_time"),

    /**
     * 修改时间
     */
    MODIFIED_TIME("modified_time"),

    /**
     * 名称
     */
    NAME("name"),

    /**
     * 特性定义
     */
    SCHEMA_DATA("schema_data");

    private String name;

    FeatureColumns(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
