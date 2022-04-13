package cn.virit.domain.base.gen.controller;

import cn.virit.common.base.BaseController;
import cn.virit.domain.base.entity.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * BaseModelController
 */
public abstract class BaseModelController extends BaseController<Model> {

    @Override
    @PostMapping
    public String add(@RequestBody @Valid Model entity, BindingResult result) {
        return super.add(entity, result);
    }

    @Override
    @PutMapping
    public void update(@RequestBody @Valid Model entity, BindingResult result) {
        super.update(entity, result);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        super.delete(id);
    }

    @Override
    @GetMapping("/{id}")
    public Model get(@PathVariable("id") String id) {
        return super.get(id);
    }
}
