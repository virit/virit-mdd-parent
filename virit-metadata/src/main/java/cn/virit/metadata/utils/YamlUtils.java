package cn.virit.metadata.utils;

import org.yaml.snakeyaml.Yaml;

public class YamlUtils {

    private static final Yaml YAML = new Yaml();

    public static Yaml getYaml() {
        return YAML;
    }
}
