package cn.virit.domain.base.gen.controller;

import cn.virit.common.base.BaseController;
import cn.virit.domain.base.entity.Feature;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * BaseFeatureController
 */
public abstract class BaseFeatureController extends BaseController<Feature> {

    @Override
    @PostMapping
    public String add(@RequestBody @Valid Feature entity, BindingResult result) {
        return super.add(entity, result);
    }

    @Override
    @PutMapping
    public void update(@RequestBody @Valid Feature entity, BindingResult result) {
        super.update(entity, result);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        super.delete(id);
    }

    @Override
    @GetMapping("/{id}")
    public Feature get(@PathVariable("id") String id) {
        return super.get(id);
    }
}
