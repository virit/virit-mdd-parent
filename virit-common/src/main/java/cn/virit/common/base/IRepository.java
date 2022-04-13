package cn.virit.common.base;

import cn.virit.common.utils.condition.Condition;

import java.util.List;

public interface IRepository<T extends BaseEntity> {

    void insert(T entity);

    void update(T entity);

    void deleteById(String id);

    T selectById(String id);

    List<T> findAll();

    List<T> findByCondition(Condition condition);

    T findOneByCondition(Condition condition);
}
