package cn.virit.common.feature;

import cn.virit.common.base.BaseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * Base适配器
 *
 * @author Virit
 * @since 1.0
 */
@Component
public class FeatureBase implements FeatureAdapter {

    @Override
    public void beforeRepoInsert(BaseEntity entity) {

        entity.setId(UUID.randomUUID().toString());
        entity.setCreatedTime(new Date());
        entity.setModifiedTime(new Date());
    }

    @Override
    public void beforeRepoUpdate(BaseEntity entity) {

        entity.setModifiedTime(new Date());
    }
}
