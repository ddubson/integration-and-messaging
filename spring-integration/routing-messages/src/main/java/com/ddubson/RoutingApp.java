package com.ddubson;

import com.ddubson.gateways.PrinterGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    @Qualifier("recipientListGateway")
    private PrinterGateway recipientListGateway;

    @Autowired
    @Qualifier("customRouterGateway")
    private PrinterGateway customRouterGateway;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //recipientListRouter();
        customRouter();
    }

    private void customRouter() {
        printBanner("CUSTOM ROUTER");
        Stream.iterate(0, n -> n + 1).limit(10).forEach(i -> {
            Message<?> intMessage = MessageBuilder.withPayload(i).build();
            Message<String> stringMessage = MessageBuilder.withPayload("Message " + i).build();
            Message<?> floatMessage = MessageBuilder.withPayload(i+2.5).build();
            this.customRouterGateway.print(intMessage);
            this.customRouterGateway.print(stringMessage);
            this.customRouterGateway.print(floatMessage);
        });

        printBanner("END CUSTOM ROUTER");
    }

    private void recipientListRouter() {
        printBanner("RECIPIENT LIST ROUTER");
        Stream.iterate(0, n -> n + 1).limit(10).forEach(i -> {
            Message<?> message = MessageBuilder.withPayload(i).build();
            Message<String> message2 = MessageBuilder.withPayload("Message " + i).build();
            this.recipientListGateway.print(message);
            this.recipientListGateway.print(message2);
        });

        printBanner("END RECIPIENT LIST ROUTER");
    }

    public static void main(String[] args) {
        SpringApplication.run(RoutingApp.class, args);
    }
}
