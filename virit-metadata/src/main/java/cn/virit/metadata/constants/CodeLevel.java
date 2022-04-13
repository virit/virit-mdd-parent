package cn.virit.metadata.constants;

/**
 * 模型访问级别
 * <p>
 * 标识一个模型的访问级别
 * 例如: 声明为DAO，则只能通过Dao层对象访问
 *
 * @author Virit
 * @since 1.0
 */
public enum CodeLevel {

    DDL,
    ENTITY,
    MAPPER,
    REPO,
    SERVICE,
    CONTROLLER,
    FIELD_CONSTANTS,
    COLUMN_CONSTANTS
}
