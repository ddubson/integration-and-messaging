package com.ddubson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("integration-context.xml")
public class FTPIntegrationApp {
    public static void main(String[] args) {
        SpringApplication.run(FTPIntegrationApp.class, args);
    }
}
