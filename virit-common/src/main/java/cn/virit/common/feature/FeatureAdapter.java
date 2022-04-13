package cn.virit.common.feature;

import cn.virit.common.base.BaseEntity;

/**
 * 特性适配器
 *
 * @author Virit
 * @since 1.0
 */
public interface FeatureAdapter {

    default void beforeRepoInsert(BaseEntity entity) {
    }

    ;

    default void afterRepoInsert(BaseEntity entity) {
    }

    ;

    default void beforeRepoUpdate(BaseEntity entity) {
    }

    ;

    default void afterRepoUpdate(BaseEntity entity) {
    }

    ;

    default void beforeServiceAdd(BaseEntity entity) {
    }

    ;

    default void afterServiceAdd(BaseEntity entity) {
    }

    ;

    default void beforeServiceUpdate(BaseEntity entity) {
    }

    ;

    default void afterServiceUpdate(BaseEntity entity) {
    }

    ;
}
