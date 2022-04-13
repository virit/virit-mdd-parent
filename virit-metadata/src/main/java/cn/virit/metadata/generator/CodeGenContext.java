package cn.virit.metadata.generator;

import cn.virit.metadata.model.ModelConfig;
import cn.virit.metadata.model.ModelInfo;
import cn.virit.metadata.model.TypeMapper;

public class CodeGenContext {

    private ModelConfig.Domain domain;

    private ModelInfo model;

    private Utils utils;

    private CodeGeneratorImpl.CodeGenConfigs genConfigs;

    private CodeGenConfig genConfig;

    private TypeMapper typeMapper;

    private String resourceDir;

    public ModelConfig.Domain getDomain() {
        return domain;
    }

    public void setDomain(ModelConfig.Domain domain) {
        this.domain = domain;
    }

    public ModelInfo getModel() {
        return model;
    }

    public void setModel(ModelInfo model) {
        this.model = model;
    }

    public Utils getUtils() {
        return utils;
    }

    public void setUtils(Utils utils) {
        this.utils = utils;
    }

    public CodeGeneratorImpl.CodeGenConfigs getGenConfigs() {
        return genConfigs;
    }

    public void setGenConfigs(CodeGeneratorImpl.CodeGenConfigs genConfigs) {
        this.genConfigs = genConfigs;
    }

    public CodeGenConfig getGenConfig() {
        return genConfig;
    }

    public void setGenConfig(CodeGenConfig genConfig) {
        this.genConfig = genConfig;
    }

    public TypeMapper getTypeMapper() {
        return typeMapper;
    }

    public void setTypeMapper(TypeMapper typeMapper) {
        this.typeMapper = typeMapper;
        this.typeMapper.setContext(this);
    }

    public String getResourceDir() {
        return resourceDir;
    }

    public void setResourceDir(String resourceDir) {
        this.resourceDir = resourceDir;
    }
}
