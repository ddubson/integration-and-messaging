package com.ddubson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@SpringBootApplication
@ImportResource("integration-context.xml")
public class PollableChannelsApp implements ApplicationRunner {
    @Autowired
    private PrinterGateway gateway;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        gatewayAndPollableChannel();
        gatewayAndPriorityPollableChannel();
    }

    private void gatewayAndPriorityPollableChannel() {
        printBanner("PRIORITY POLLABLE CHANNEL");
        List<Future<Message<String>>> futures = Stream.iterate(0, n -> n + 1).limit(10).map(i -> {
            Message<String> message = org.springframework.integration.support.MessageBuilder
                    .withPayload("Msg payload for " + i)
                    .setPriority(i)
                    .setHeader("messageNumber", i)
                    .build();
            System.out.println("Sending message " + i);
            return this.gateway.print(message);
        }).collect(toList());

        fetchReturnMessages(futures);
        printBanner("END PRIORITY POLLABLE CHANNEL");
    }

    private void gatewayAndPollableChannel() {
        printBanner("STANDARD POLLABLE CHANNEL");
        List<Future<Message<String>>> futures = Stream.iterate(0, n -> n + 1).limit(10).map(i -> {
            Message<String> message = MessageBuilder
                    .withPayload("Msg payload for " + i)
                    .setHeader("messageNumber", i)
                    .build();
            System.out.println("Sending message " + i);
            return this.gateway.print(message);
        }).collect(toList());

        fetchReturnMessages(futures);
        printBanner("END STANDARD POLLABLE CHANNEL");
    }

    private void fetchReturnMessages(List<Future<Message<String>>> futures) {
        futures.forEach(f -> {
            try {
                System.out.println(f.get().getPayload());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    private void printBanner(String x) {
        System.out.format("------ %s ------\n", x);
    }

    public static void main(String[] args) {
        SpringApplication.run(PollableChannelsApp.class, args);
    }
}
