package cn.virit.common.base;

import cn.virit.common.utils.condition.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * BaseService实现
 *
 * @param <T> 实体类型
 * @author Virit
 * @since 1.0
 */
public abstract class BaseService<T extends BaseEntity> implements IService<T> {

    protected IRepository<T> repository;

    @Override
    @Transactional
    public void add(T entity) {
        repository.insert(entity);
    }

    @Override
    @Transactional
    public void update(T entity) {
        repository.update(entity);
    }

    @Override
    @Transactional
    public void save(T entity) {

    }

    @Override
    @Transactional
    public void removeById(String id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public T findById(String id) {
        return repository.selectById(id);
    }

    @Override
    @Transactional
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findOne(Condition condition) {
        return repository.findOneByCondition(condition);
    }

    @Override
    public List<T> findAll(Condition condition) {
        if (condition == null) {
            return findAll();
        }
        return repository.findByCondition(condition);
    }

    @Autowired
    public void setRepository(IRepository<T> repository) {
        if (this.repository == null) {
            this.repository = repository;
        }
    }
}
