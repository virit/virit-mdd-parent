package cn.virit.domain.base.gen.constants;

/**
 * 模型
 */
public enum ModelFields {

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
     * 领域
     */
    DOMAIN("domain"),

    /**
     * 模型定义
     */
    SCHEMA_DATA("schemaData"),

    /**
     * mapperClass
     */
    MAPPER_CLASS("mapperClass"),

    /**
     * serverClass
     */
    SERVICE_CLASS("serviceClass"),

    /**
     * repoClass
     */
    REPO_CLASS("repoClass");

    private String name;

    ModelFields(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
