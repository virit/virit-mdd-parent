package cn.virit.domain.base.gen.constants;

/**
 * 模型
 */
public enum ModelColumns {

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
     * 领域
     */
    DOMAIN("domain"),

    /**
     * 模型定义
     */
    SCHEMA_DATA("schema_data"),

    /**
     * mapperClass
     */
    MAPPER_CLASS("mapper_class"),

    /**
     * serverClass
     */
    SERVICE_CLASS("service_class"),

    /**
     * repoClass
     */
    REPO_CLASS("repo_class");

    private String name;

    ModelColumns(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
