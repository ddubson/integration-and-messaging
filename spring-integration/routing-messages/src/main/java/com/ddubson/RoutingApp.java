package com.ddubson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.stream.Stream;

import static com.ddubson.ConsolePrinterUtils.printBanner;

@SpringBootApplication
@ImportResource("integration-context.xml")
public class RoutingApp implements ApplicationRunner {
    @Autowired
    private PrinterGateway gateway;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        payloadTypeRouter();
    }

    private void payloadTypeRouter() {
        printBanner("PAYLOAD ROUTER TYPE");
        Stream.iterate(0, n -> n + 1).limit(10).forEach(i -> {
            Message<String> message = MessageBuilder.withPayload("Message " + i)
                    .build();
            this.gateway.print(message);
        });

        Stream.iterate(0, n -> n + 1).limit(10).forEach(i -> {
            Message<Integer> message = MessageBuilder.withPayload(i)
                    .build();
            this.gateway.print(message);
        });

        printBanner("END PAYLOAD ROUTER TYPE");
    }

    public static void main(String[] args) {
        SpringApplication.run(RoutingApp.class, args);
    }
}
