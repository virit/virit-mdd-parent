package cn.virit.metadata.generator;

import cn.virit.metadata.model.Child;
import cn.virit.metadata.model.Field;
import cn.virit.metadata.model.ModelInfo;
import cn.virit.metadata.model.TypeMapper;
import org.apache.commons.text.StringEscapeUtils;
import org.yaml.snakeyaml.Yaml;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static final Utils INSTANCE = new Utils();
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    private CodeGenContext context;
    private Yaml yaml = new Yaml();

    private Utils() {

    }

    public static Utils getInstance() {
        return INSTANCE;
    }

    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static String humpToLine(String str, String split) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, split + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public String imports(Object... objs) {

        Set<String> classes = new LinkedHashSet<>();
        for (var obj : objs) {
            if (obj instanceof Map) {
                var map = (Map<?, ?>) obj;
                map.forEach((key, value) -> {
                    if (value instanceof Field) {
                        var field = (Field) value;
                        var javaType = TypeMapper.getInstance().getJavaType(field);
                        if (javaType.contains(".")) {
                            classes.add(javaType);
                        }
                    } else if (value instanceof Child) {
                        // 导入子表
                        var child = (Child) value;
                        String className = context.getDomain().getBasePackage() + '.'
                            + context.getGenConfigs().getTemplates().get("entity").getParams().get("package")
                            + '.' + child.getRefer();
                        classes.add(className);
                        classes.add("java.util.List");
                    }
                });
            } else if (obj instanceof String) {
                String clazz = (String) obj;
                if (clazz.contains(".")) {
                    classes.add(clazz);
                }
            } else if (obj instanceof Collection) {
                Collection<?> collection = (Collection<?>) obj;
                collection.forEach(clazz -> {
                    classes.add((String) clazz);
                });
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String clazz : classes) {
            sb.append("import ").append(clazz).append(";\n");
        }
        return sb.toString();
    }

    public void setContext(CodeGenContext context) {
        this.context = context;
    }

    public String dumpYaml(Object obj) {
        return yaml.dump(obj).replace("'", "''");
    }

    public String unescapeText(String str) {
        return StringEscapeUtils.unescapeJson(str);
    }

    public String firstUpperCase(String fieldName) {
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    public String firstLowerCase(String fieldName) {
        return fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
    }

    public String setterName(String fieldName) {
        return "set" + firstUpperCase(fieldName);
    }

    public String getterName(String fieldName) {
        return "get" + firstUpperCase(fieldName);
    }

    public String upperCase(String str) {

        return humpToLine(str).toUpperCase();
    }

    public String tableName(ModelInfo model) {

        return humpToLine(model.getDomain()) + '_' + humpToLine(firstLowerCase(model.getName()));
    }

    public String simpleClassName(String clazz) {
        if (clazz.contains(".")) {
            return clazz.substring(clazz.lastIndexOf(".") + 1);
        } else {
            return clazz;
        }
    }

    public Object test(boolean check, Object o1, Object o2) {
        return check ? o1 : o2;
    }

    public String humpToLine(String str) {
        return humpToLine(firstLowerCase(str), "_");
    }

    public String humpToMiddleLine(String str) {
        return humpToLine(firstLowerCase(str), "-");
    }
}
