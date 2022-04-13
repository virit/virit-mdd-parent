package cn.virit.metadata.model;

import cn.virit.metadata.generator.Utils;

/**
 * 子对象
 * <p>
 * 声明模型的子对象
 *
 * @author Virit
 * @since 1.0
 */
public class Child {

    /**
     * 子字段名称
     */
    private String name;

    /**
     * 描述
     */
    private String title;

    /**
     * 关联对象
     */
    private String refer;

    /**
     * 关联对象的关联字段
     */
    private String referField;

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

    public String getRefer() {
        return refer;
    }

    public void setRefer(String refer) {
        this.refer = refer;
    }

    public String getReferField() {
        return referField;
    }

    public void setReferField(String referField) {
        this.referField = referField;
    }

    public String getReferColumn() {
        return Utils.getInstance().humpToLine(getReferField() + "Id");
    }

}
