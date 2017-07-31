package com.ddubson.integration;

import com.ddubson.CommandLineLogAdapter;
import com.ddubson.LogAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static com.ddubson.ANSIColor.ANSI_CYAN;
import static com.ddubson.ANSIColor.ANSI_PURPLE;
import static java.util.stream.Collectors.toList;

@SpringBootApplication
@ImportResource("integration-context.xml")
public class Application implements ApplicationRunner {
    @Autowired
    private PrinterGateway gateway;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logAdapter().info("[1] Sending messages to input channel via gateway.", ANSI_PURPLE);

        List<Future<Message<String>>> futures = Stream.iterate(0, n -> n + 1).limit(10).map(i -> {
            Message<String> message =
                    MessageBuilder.withPayload("Msg payload for " + i)
                            .setPriority(i)
                            .setHeader("messageNumber", i)
                            .build();
            logAdapter().info("[1] Sending message " + i + " to input channel.", ANSI_PURPLE);
            return this.gateway.print(message);
        }).collect(toList());

        fetchReturnMessages(futures);
    }

    private void fetchReturnMessages(List<Future<Message<String>>> futures) {
        futures.forEach(f -> {
            try {
                logAdapter().info("[3] Returned message: " + f.get().getPayload(), ANSI_CYAN);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public LogAdapter logAdapter() {
        return new CommandLineLogAdapter();
    }
}
