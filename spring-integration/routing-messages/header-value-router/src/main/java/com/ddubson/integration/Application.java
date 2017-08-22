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
        logAdapter().info("[1] Send messages to inputChannel.", ANSIColor.ANSI_YELLOW);
        Stream.iterate(0, n -> n + 1).limit(10).forEach(i -> {
            Message<String> message = MessageBuilder.withPayload("Message " + i)
                    .setHeader("routeHeader", "string")
                    .build();
            logAdapter().info("[1.1] Send string message " + message.getPayload(), ANSIColor.ANSI_PURPLE);
            this.gateway.print(message);
        });

        Stream.iterate(0, n -> n + 1).limit(10).forEach(i -> {
            Message<Integer> message = MessageBuilder
                    .withPayload(i).setHeader("routeHeader", "int")
                    .build();
            logAdapter().info("[1.1] Send int message " + message.getPayload(), ANSIColor.ANSI_CYAN);
            this.gateway.print(message);
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
