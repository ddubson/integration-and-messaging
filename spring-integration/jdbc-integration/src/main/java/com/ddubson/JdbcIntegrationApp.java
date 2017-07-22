package com.ddubson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("integration-context.xml")
public class JdbcIntegrationApp implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(JdbcIntegrationApp.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
