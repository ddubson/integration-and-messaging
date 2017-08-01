package com.ddubson.integration;

import com.ddubson.ANSIColor;
import com.ddubson.CommandLineLogAdapter;
import com.ddubson.LogAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.stream.Stream;

@SpringBootApplication
@ImportResource("integration-context.xml")
public class Application implements ApplicationRunner {
    @Autowired
    private PrinterGateway gateway;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logAdapter().info("[1] Send messages to inputChannel.", ANSIColor.ANSI_WHITE);
        Stream.iterate(0, n -> n + 1).limit(10).forEach(i -> {
            Message<?> intMessage = MessageBuilder.withPayload(i).build();
            logAdapter().info("[1] Send message " + i, ANSIColor.ANSI_WHITE);
            this.gateway.print(intMessage);
        });
    }

    @Bean
    public LogAdapter logAdapter() {
        return new CommandLineLogAdapter();
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
