package cn.virit.common.base;

import java.util.Date;
import java.util.List;

/**
 * Base特性实体基类
 * 拥有Base特性的模型对应的实体类都会继承BaseEntity
 *
 * @author Virit
 * @since 1.0
 */
public interface BaseEntity {

    String getId();

    void setId(String id);

    String getCreatedUserId();

    void setCreatedUserId(String getCreatedUserId);

    String getModifiedUserId();

    void setModifiedUserId(String updatedUserId);

    Date getCreatedTime();

    void setCreatedTime(Date createdTime);

    Date getModifiedTime();

    void setModifiedTime(Date modifiedTime);

    String getModelDomain();

    String getModelName();

    void set(String fieldName, Object value);

    Object get(String field);

    void setChildData(String childName, List<? extends BaseEntity> value);

    List<? extends BaseEntity> getChildData(String childName);
}
