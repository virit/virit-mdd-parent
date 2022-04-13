package cn.virit.metadata.model;

import cn.virit.metadata.constants.FieldProperty;
import cn.virit.metadata.constants.FieldType;
import cn.virit.metadata.generator.CodeGenContext;
import cn.virit.metadata.generator.CodeGeneratorImpl;
import cn.virit.metadata.utils.VelocityUtils;
import cn.virit.metadata.utils.VelocityUtils.VelocityContextBuilder;
import org.apache.velocity.VelocityContext;
import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;
import java.util.Map;

public class TypeMapper {

    private static final Yaml yaml = new Yaml();
    private static final String CONFIG_FILE = "typemappers/typemapper.yml";
    private static TypeMapper instance = null;
    private CodeGenContext context;
    private Map<FieldType, TypeMap> types;

    public static TypeMapper getInstance() {
        if (instance == null) {
            instance = yaml.loadAs(CodeGeneratorImpl.class.getClassLoader().getResourceAsStream(CONFIG_FILE),
                TypeMapper.class);
        }
        return instance;
    }

    public Map<FieldType, TypeMap> getTypes() {
        return types;
    }

    public void setTypes(Map<FieldType, TypeMap> types) {
        this.types = types;
    }

    public String getSimpleJavaType(Field field) {

        String javaType = getJavaType(field);
        if (javaType.contains(".")) {
            return javaType.substring(javaType.lastIndexOf(".") + 1);
        } else {
            return javaType;
        }
    }

    public String getDbType(Field field) {

        FieldType fieldType = field.getType();
        TypeMap typeMap = types.get(fieldType);
        return renderField(typeMap.getDbType(), field);
    }

    public String getJavaType(Field field) {

        FieldType fieldType = field.getType();
        TypeMap typeMap = types.get(fieldType);
        return renderField(typeMap.getJavaType(), field);
    }

    private String renderField(String template, Field field) {

        VelocityContext velocityContext = new VelocityContextBuilder()
            .set("ctx", this.context)
            .set("field", field)
            .build();
        return VelocityUtils.render(template, velocityContext);
    }

    public void setContext(CodeGenContext context) {
        this.context = context;
    }

    private Map<String, Object> transferProperties(Map<FieldProperty, Object> properties) {

        final Map<String, Object> res = new HashMap<>();
        properties.forEach((k, v) -> res.put(k.name(), v));
        return res;
    }

    public static class TypeMap {

        private String javaType;

        private String dbType;

        public String getJavaType() {
            return javaType;
        }

        public void setJavaType(String javaType) {
            this.javaType = javaType;
        }

        public String getDbType() {
            return dbType;
        }

        public void setDbType(String dbType) {
            this.dbType = dbType;
        }
    }
}
