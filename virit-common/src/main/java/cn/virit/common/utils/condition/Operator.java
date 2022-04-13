package cn.virit.common.utils.condition;

public enum Operator {

    EQ("EQ", "${item.field} = #{${params.get(0)}}"),
    NE("NE", "${item.field} != #{${params.get(0)}}"),
    IN("IN", "${item.field} in (#foreach($param in $params)#{${param}}#if($foreach.hasNext),#end#end)"),
    LIKE("LIKE", "");

    private final String name;

    private final String template;

    Operator(String name, String template) {
        this.name = name;
        this.template = template;
    }

    public String getName() {
        return name;
    }

    public String getTemplate() {
        return template;
    }
}
