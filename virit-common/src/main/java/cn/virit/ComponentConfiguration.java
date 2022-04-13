package cn.virit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

@Component
@Configuration
public class ComponentConfiguration {

    @Bean
    public Yaml yaml() {
        return new Yaml();
    }
}
