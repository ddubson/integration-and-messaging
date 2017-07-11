package com.ddubson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.stream.Stream;

@SpringBootApplication
public class SubscribableChannelsApp implements ApplicationRunner {
    @Autowired
    private PrinterGateway gateway;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        subscribableRoundRobin();
    }

    private void subscribableRoundRobin() {
        printBanner("SUBSCRIBABLE ROUND-ROBIN CHANNEL");
        Stream.iterate(0, n -> n + 1).limit(10).forEach(i -> {
            Message<String> message = MessageBuilder.withPayload("Message " + i)
                    .build();
            this.gateway.print(message);
        });

        printBanner("END SUBSCRIBABLE ROUND-ROBIN CHANNEL");
    }

    private void printBanner(String x) {
        System.out.format("------ %s ------\n", x);
    }

    public static void main(String[] args) {
        SpringApplication.run(SubscribableChannelsApp.class, args);
    }
}
