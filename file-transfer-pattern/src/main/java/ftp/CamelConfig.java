package ftp;

import org.apache.camel.main.Main;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelConfig {
    @Bean
    public Main camelMain() {
        return new Main();
    }
}
