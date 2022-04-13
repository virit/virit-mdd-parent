package cn.virit.common.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * BaseController
 * 所有模型生成的Controller都会继承自BaseController
 *
 * @param <T> 实体类型，继承自BaseEntity
 * @author Virit
 * @since 1.0
 */
public class BaseController<T extends BaseEntity> {

    /**
     * 对应的实体Service
     */
    private IService<T> service;

    /**
     * 新增
     */
    @PostMapping
    @Transactional
    public String add(@RequestBody @Valid T entity, BindingResult result) {
        service.add(entity);
        return entity.getId();
    }

    /**
     * 修改
     */
    @PutMapping
    public void update(@RequestBody @Valid T entity, BindingResult result) {
        service.update(entity);
    }

    /**
     * 获取单个
     */
    @GetMapping("/{id}")
    public T get(@PathVariable("id") String id) {
        return this.service.findById(id);
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        this.service.removeById(id);
    }

    @Autowired
    public void setService(IService<T> service) {
        if (this.service == null) {
            this.service = service;
        }
    }
}
