package cn.virit.common.base;

import cn.virit.common.utils.condition.Condition;

import java.util.List;

/**
 * Service接口类
 *
 * @param <T> 实体类型
 * @author Virit
 * @since 1.0
 */
public interface IService<T extends BaseEntity> {

    void add(T entity);

    void update(T entity);

    void save(T entity);

    void removeById(String id);

    T findById(String id);

    List<T> findAll();

    T findOne(Condition condition);

    List<T> findAll(Condition condition);
}
