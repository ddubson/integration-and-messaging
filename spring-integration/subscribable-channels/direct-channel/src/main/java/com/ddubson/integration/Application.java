package com.ddubson.integration;


import com.ddubson.CommandLineLogAdapter;
import com.ddubson.LogAdapter;
import com.ddubson.integration.gateways.PrinterGateway;
import com.ddubson.integration.services.PrintService;
import com.ddubson.integration.services.UppercasePrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.util.stream.Stream;

import static com.ddubson.ANSIColor.ANSI_WHITE;

@SpringBootApplication
public class Application implements ApplicationRunner {
    private int numberOfMessages = 10;

    @Autowired
    private PrinterGateway gateway;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logAdapter().info("[1] Subscribable Round-Robin Direct Channel", ANSI_WHITE);
        Stream.iterate(0, n -> n + 1).limit(numberOfMessages).forEach(i -> {
            Message<String> message = MessageBuilder.withPayload("Message " + i)
                    .build();
            logAdapter().info("[1] Sending message " + i + " to input channel.", ANSI_WHITE);
            this.gateway.print(message);
        });
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public LogAdapter logAdapter() {
        return new CommandLineLogAdapter();
    }

    @Bean
    public PrintService printService() {
        return new PrintService(logAdapter());
    }

    @Bean
    public UppercasePrintService uppercasePrintService() {
        return new UppercasePrintService(logAdapter());
    }
}
