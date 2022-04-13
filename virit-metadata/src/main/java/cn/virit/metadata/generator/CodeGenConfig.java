package cn.virit.metadata.generator;

import cn.virit.metadata.constants.CodeLevel;

import java.util.Map;

/**
 * 代码生成配置
 *
 * @author Virit
 * @since 1.0
 */
public class CodeGenConfig {

    private String template;

    private String target;

    private boolean cover;

    private Map<String, Object> params;

    private CodeLevel codeLevel;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public boolean isCover() {
        return cover;
    }

    public void setCover(boolean cover) {
        this.cover = cover;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public CodeLevel getCodeLevel() {
        return codeLevel;
    }

    public void setCodeLevel(CodeLevel codeLevel) {
        this.codeLevel = codeLevel;
    }
}
