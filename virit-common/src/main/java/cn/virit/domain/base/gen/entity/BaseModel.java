package cn.virit.domain.base.gen.entity;

import cn.virit.common.base.BaseEntity;
import cn.virit.domain.base.entity.Model;
import cn.virit.domain.base.gen.constants.ModelFields;

import java.util.Date;
import java.util.List;

/**
 * 模型
 * <p>
 * 模型
 */
public abstract class BaseModel implements BaseEntity {

    /**
     * ID
     */
    protected String id;

    /**
     * 创建人
     */
    protected String createdUserId;

    /**
     * 修改人
     */
    protected String modifiedUserId;

    /**
     * 创建时间
     */
    protected Date createdTime;

    /**
     * 修改时间
     */
    protected Date modifiedTime;

    /**
     * 名称
     */
    protected String name;

    /**
     * 领域
     */
    protected String domain;

    /**
     * 模型定义
     */
    protected String schemaData;

    /**
     * mapperClass
     */
    protected String mapperClass;

    /**
     * serverClass
     */
    protected String serviceClass;

    /**
     * repoClass
     */
    protected String repoClass;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedUserId() {
        return this.createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }

    public String getModifiedUserId() {
        return this.modifiedUserId;
    }

    public void setModifiedUserId(String modifiedUserId) {
        this.modifiedUserId = modifiedUserId;
    }

    public Date getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return this.modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSchemaData() {
        return this.schemaData;
    }

    public void setSchemaData(String schemaData) {
        this.schemaData = schemaData;
    }

    public String getMapperClass() {
        return this.mapperClass;
    }

    public void setMapperClass(String mapperClass) {
        this.mapperClass = mapperClass;
    }

    public String getServiceClass() {
        return this.serviceClass;
    }

    public void setServiceClass(String serviceClass) {
        this.serviceClass = serviceClass;
    }

    public String getRepoClass() {
        return this.repoClass;
    }

    public void setRepoClass(String repoClass) {
        this.repoClass = repoClass;
    }

    @Override
    public String getModelDomain() {
        return "base";
    }

    @Override
    public String getModelName() {
        return "Model";
    }

    @Override
    public void set(String fieldName, Object value) {
        if ("id".equals(fieldName)) {
            this.setId((String) value);
        }
        if ("createdUserId".equals(fieldName)) {
            this.setCreatedUserId((String) value);
        }
        if ("modifiedUserId".equals(fieldName)) {
            this.setModifiedUserId((String) value);
        }
        if ("createdTime".equals(fieldName)) {
            this.setCreatedTime((Date) value);
        }
        if ("modifiedTime".equals(fieldName)) {
            this.setModifiedTime((Date) value);
        }
        if ("name".equals(fieldName)) {
            this.setName((String) value);
        }
        if ("domain".equals(fieldName)) {
            this.setDomain((String) value);
        }
        if ("schemaData".equals(fieldName)) {
            this.setSchemaData((String) value);
        }
        if ("mapperClass".equals(fieldName)) {
            this.setMapperClass((String) value);
        }
        if ("serviceClass".equals(fieldName)) {
            this.setServiceClass((String) value);
        }
        if ("repoClass".equals(fieldName)) {
            this.setRepoClass((String) value);
        }
    }

    @Override
    public Object get(String fieldName) {
        if ("id".equals(fieldName)) {
            return this.getId();
        }
        if ("createdUserId".equals(fieldName)) {
            return this.getCreatedUserId();
        }
        if ("modifiedUserId".equals(fieldName)) {
            return this.getModifiedUserId();
        }
        if ("createdTime".equals(fieldName)) {
            return this.getCreatedTime();
        }
        if ("modifiedTime".equals(fieldName)) {
            return this.getModifiedTime();
        }
        if ("name".equals(fieldName)) {
            return this.getName();
        }
        if ("domain".equals(fieldName)) {
            return this.getDomain();
        }
        if ("schemaData".equals(fieldName)) {
            return this.getSchemaData();
        }
        if ("mapperClass".equals(fieldName)) {
            return this.getMapperClass();
        }
        if ("serviceClass".equals(fieldName)) {
            return this.getServiceClass();
        }
        if ("repoClass".equals(fieldName)) {
            return this.getRepoClass();
        }
        return null;
    }

    public void set(ModelFields field, Object value) {
        this.set(field.getName(), value);
    }

    @Override
    public void setChildData(String childName, List<? extends BaseEntity> value) {
    }

    @Override
    public List<? extends BaseEntity> getChildData(String childName) {
        return null;
    }

    public static class Builder {

        /**
         * ID
         */
        private String id;

        /**
         * 创建人
         */
        private String createdUserId;

        /**
         * 修改人
         */
        private String modifiedUserId;

        /**
         * 创建时间
         */
        private Date createdTime;

        /**
         * 修改时间
         */
        private Date modifiedTime;

        /**
         * 名称
         */
        private String name;

        /**
         * 领域
         */
        private String domain;

        /**
         * 模型定义
         */
        private String schemaData;

        /**
         * mapperClass
         */
        private String mapperClass;

        /**
         * serverClass
         */
        private String serviceClass;

        /**
         * repoClass
         */
        private String repoClass;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder createdUserId(String createdUserId) {
            this.createdUserId = createdUserId;
            return this;
        }

        public Builder modifiedUserId(String modifiedUserId) {
            this.modifiedUserId = modifiedUserId;
            return this;
        }

        public Builder createdTime(Date createdTime) {
            this.createdTime = createdTime;
            return this;
        }

        public Builder modifiedTime(Date modifiedTime) {
            this.modifiedTime = modifiedTime;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public Builder schemaData(String schemaData) {
            this.schemaData = schemaData;
            return this;
        }

        public Builder mapperClass(String mapperClass) {
            this.mapperClass = mapperClass;
            return this;
        }

        public Builder serviceClass(String serviceClass) {
            this.serviceClass = serviceClass;
            return this;
        }

        public Builder repoClass(String repoClass) {
            this.repoClass = repoClass;
            return this;
        }

        public Model build() {

            Model entity = new Model();
            entity.setId(id);
            entity.setCreatedUserId(createdUserId);
            entity.setModifiedUserId(modifiedUserId);
            entity.setCreatedTime(createdTime);
            entity.setModifiedTime(modifiedTime);
            entity.setName(name);
            entity.setDomain(domain);
            entity.setSchemaData(schemaData);
            entity.setMapperClass(mapperClass);
            entity.setServiceClass(serviceClass);
            entity.setRepoClass(repoClass);
            return entity;
        }
    }
}
