package cn.virit.metadata.constants;

/**
 * 模型字段属性
 *
 * @author Virit
 * @since 1.0
 */
public enum FieldProperty {

    /**
     * 标识字段的长度，仅type = STRING设置有效
     */
    length,
    /**
     * 标识字段为主键
     */
    primaryKey,
    /**
     * 标识字段为关联对象
     */
    referObject
}
