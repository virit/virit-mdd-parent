package cn.virit.metadata.model;

import cn.virit.metadata.constants.CodeLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模型配置
 *
 * @author Virit
 * @since 1.0
 */
public class ModelConfig {

    /**
     * 模型库,git地址
     */
    private String repository;
    /**
     * 领域配置
     */
    private Map<String, Domain> domains;

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public Map<String, Domain> getDomains() {
        return domains;
    }

    public void setDomains(Map<String, Domain> domains) {
        this.domains = domains;
    }

    /**
     * 领域配置
     */
    public static class Domain {

        /**
         * 需要引用的领域模型
         * *表示所有模型
         */
        private List<String> models;

        private Map<String, ModelInfo> modelInfos = new HashMap<>();

        /**
         * 代码级别
         *
         * @see CodeLevel
         */
        private List<CodeLevel> codeLevels;

        /**
         * 是否同步数据库
         */
        private boolean syncDb;

        /**
         * 包根路径
         */
        private String basePackage;

        public List<String> getModels() {
            return models;
        }

        public void setModels(List<String> models) {
            this.models = models;
        }

        public void setModelInfos(List<ModelInfo> models) {
            for (var model : models) {
                modelInfos.put(model.getName(), model);
            }
        }

        public ModelInfo getModel(String name) {
            return modelInfos.get(name);
        }

        public List<CodeLevel> getCodeLevels() {
            return codeLevels;
        }

        public void setCodeLevels(List<CodeLevel> codeLevels) {
            this.codeLevels = codeLevels;
        }

        public boolean isSyncDb() {
            return syncDb;
        }

        public void setSyncDb(boolean syncDb) {
            this.syncDb = syncDb;
        }

        public String getBasePackage() {
            return basePackage;
        }

        public void setBasePackage(String basePackage) {
            this.basePackage = basePackage;
        }

        public String getBasePackageDir() {
            return "src/main/java/" + getBasePackage().replaceAll("\\.", "\\/");
        }
    }

}
