package cn.virit.domain.base.gen.entity;

import cn.virit.common.base.BaseEntity;
import cn.virit.domain.base.entity.Feature;
import cn.virit.domain.base.gen.constants.FeatureFields;

import java.util.Date;
import java.util.List;

/**
 * 特性
 * <p>
 * 特性
 */
public abstract class BaseFeature implements BaseEntity {

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
     * 特性定义
     */
    protected String schemaData;

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

    public String getSchemaData() {
        return this.schemaData;
    }

    public void setSchemaData(String schemaData) {
        this.schemaData = schemaData;
    }

    @Override
    public String getModelDomain() {
        return "base";
    }

    @Override
    public String getModelName() {
        return "Feature";
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
        if ("schemaData".equals(fieldName)) {
            this.setSchemaData((String) value);
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
        if ("schemaData".equals(fieldName)) {
            return this.getSchemaData();
        }
        return null;
    }

    public void set(FeatureFields field, Object value) {
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
         * 特性定义
         */
        private String schemaData;

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

        public Builder schemaData(String schemaData) {
            this.schemaData = schemaData;
            return this;
        }

        public Feature build() {

            Feature entity = new Feature();
            entity.setId(id);
            entity.setCreatedUserId(createdUserId);
            entity.setModifiedUserId(modifiedUserId);
            entity.setCreatedTime(createdTime);
            entity.setModifiedTime(modifiedTime);
            entity.setName(name);
            entity.setSchemaData(schemaData);
            return entity;
        }
    }
}
