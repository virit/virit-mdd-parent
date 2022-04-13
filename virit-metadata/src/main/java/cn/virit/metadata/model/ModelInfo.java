package cn.virit.metadata.model;

import cn.virit.metadata.constants.CodeLevel;
import cn.virit.metadata.constants.FeatureName;
import cn.virit.metadata.constants.FieldType;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 领域模型
 *
 * @author Virit
 * @since 1.0
 */
public class ModelInfo {

    /**
     * 名称
     */
    private String name;

    /**
     * 标题
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 领域
     */
    private String domain;

    /**
     * 描述
     */
    private String description;

    /**
     * 特性
     */
    private List<FeatureName> features;

    /**
     * 访问级别
     */
    private Set<CodeLevel> codeLevels;

    /**
     * 字段
     */
    private Map<String, Field> fields;

    /**
     * 子对象
     */
    private Map<String, Child> children;

    /**
     * yaml模型内容
     */
    private String content;

    /**
     * 父类类型
     */
    private Set<String> superTypes;

    /**
     * 是否是特性
     */
    private boolean feature;

    /**
     * 特性适配器
     */
    private String adapter;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FeatureName> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureName> features) {
        this.features = features;
    }

    public Set<CodeLevel> getCodeLevels() {
        return codeLevels;
    }

    public void setCodeLevels(Set<CodeLevel> codeLevels) {
        this.codeLevels = codeLevels;
    }

    public Map<String, Field> getFields() {
        return fields;
    }

    public void setFields(Map<String, Field> fields) {
        this.fields = fields;
    }

    public Map<String, Field> getDbFields() {
        Map<String, Field> fieldMap = new LinkedHashMap<>();
        getFields().forEach((name, field) -> {
            if (field.getType() != FieldType.OBJECT) {
                fieldMap.put(name, field);
            }
        });
        return fieldMap;
    }

    public Map<String, Child> getChildren() {
        return children;
    }

    public void setChildren(Map<String, Child> children) {
        this.children = children;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<String> getSuperTypes() {
        return superTypes;
    }

    public void setSuperTypes(Set<String> superTypes) {
        this.superTypes = superTypes;
    }

    public boolean isFeature() {
        return feature;
    }

    public void setFeature(boolean feature) {
        this.feature = feature;
    }

    public String getAdapter() {
        return adapter;
    }

    public void setAdapter(String adapter) {
        this.adapter = adapter;
    }
}
