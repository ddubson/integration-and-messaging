package com.ddubson.integration;

import com.ddubson.CommandLineLogAdapter;
import com.ddubson.LogAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("integration-context.xml")
public class Application implements ApplicationRunner{
    private String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><people>" +
            "<person><firstName>John</firstName><lastName>Doe</lastName></person>" +
            "<person><firstName>Jane</firstName><lastName>Smith</lastName></person>" +
            "</people>";

    @Qualifier("transformerGateway")
    @Autowired
    private SimpleGateway gateway;

    @Qualifier("headerEnricherGateway")
    @Autowired
    private SimpleGateway headerEnricherGateway;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public LogAdapter logAdapter() {
        return new CommandLineLogAdapter();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.gateway.execute(xml);
        this.headerEnricherGateway.execute(xml);
    }
}
