package cn.virit.common.base;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

@Component
public class ObjectGetter {

    @Cacheable(cacheNames = "OBJECT_INSTANCE", key = "#targetType.name")
    public BaseEntity getEntityInstance(Class<?> targetType) {
        ParameterizedType ptClass = (ParameterizedType) targetType.getGenericSuperclass();
        Class<?> clazz = (Class<?>) ptClass.getActualTypeArguments()[0];
        try {
            Object obj = clazz.getDeclaredConstructor().newInstance();
            if (obj instanceof BaseEntity) {
                return (BaseEntity) obj;
            } else {
                throw new RuntimeException("实体类型必须继承BaseEntity");
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
