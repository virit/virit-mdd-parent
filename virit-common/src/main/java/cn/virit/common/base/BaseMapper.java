package cn.virit.common.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * BaseMapper
 *
 * @author Virit
 * @since 1.0
 */
public interface BaseMapper<T extends BaseEntity> {

    /**
     * 新增
     */
    void insert(T entity);

    /**
     * 修改
     */
    void update(T entity);

    /**
     * 删除
     */
    void deleteById(String id);

    /**
     * 根据id查询
     */
    T selectById(String id);

    /**
     * 查询所有
     */
    List<T> findAll();

    /**
     * 根据条件查询
     */
    List<T> findByCondition(@Param("condition") String condition, @Param("params") Object params
        , @Param("orderBy") String orderBy, @Param("limit") String limit);

    /**
     * 根据条件查询
     */
    T findOneByCondition(@Param("condition") String condition, @Param("params") Object params);
}
