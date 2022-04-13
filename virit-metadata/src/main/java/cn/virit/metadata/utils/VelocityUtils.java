package cn.virit.metadata.utils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * Velocity工具类
 *
 * @author Virit
 * @since 1.0
 */
public class VelocityUtils {

    public static final String RESOURCES_PATH = "src/main/resources/";
    public static VelocityEngine ENGINE;

    static {
        Properties props = new Properties();
        props.setProperty(VelocityEngine.RESOURCE_LOADER, "class");
        props.setProperty("class.resource.loader.class",
            "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        ENGINE = new VelocityEngine();
        ENGINE.init();
    }

    public static void render(Template template, VelocityContext context, String filePath) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(filePath);
        template.merge(context, writer);
        writer.close();
    }

    public static void render(String template, VelocityContext context, String filePath) {

        FileUtils.createIfNotExistsDir(FileUtils.getFileDir(filePath));
        try {
            PrintWriter writer = new PrintWriter(filePath);
            render(template, context, writer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void render(String template, VelocityContext context, PrintWriter writer) {
        Velocity.evaluate(context, writer, "", template);
        writer.close();
    }

    public static String render(String template, VelocityContext context) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(baos);
        render(template, context, writer);
        String result = new String(baos.toByteArray());
        try {
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static final class VelocityContextBuilder {

        private final VelocityContext context = new VelocityContext();

        public VelocityContextBuilder set(String key, Object value) {
            context.put(key, value);
            return this;
        }

        public VelocityContext build() {
            return context;
        }
    }
}
