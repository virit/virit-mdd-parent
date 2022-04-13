package cn.virit.metadata.constants;

/**
 * 模型特性
 * 模型可以声明多个Feature，会自动绑定多个特性
 *
 * @author Virit
 * @since 1.0
 */
public enum FeatureName {
    /**
     * 基准特性
     * 会生成id,createTime,createUserId等字段
     */
    BASE,
    /**
     * 树形特性
     */
    TREE,
    /**
     * 逻辑删除
     */
    LOGICAL_DELETE
}
