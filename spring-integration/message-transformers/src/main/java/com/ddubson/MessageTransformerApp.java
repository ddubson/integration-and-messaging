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
public class MessageTransformerApp implements ApplicationRunner {
    @Autowired
    @Qualifier("gateway4")
    private PrinterGateway headerEnricherGateway;

    @Autowired
    @Qualifier("gateway5")
    private PrinterGateway payloadEnricherGateway;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //headerEnricher();
        payloadEnricher();
    }

    private void payloadEnricher() {
        printBanner("PAYLOAD ENRICHER");
        Stream.iterate(0, n -> n + 1).limit(3).forEach(i -> {
            Message<?> intMessage = MessageBuilder
                    .withPayload(new Person("John", "Doe"))
                    .build();
            this.payloadEnricherGateway.print(intMessage);
        });

        printBanner("END PAYLOAD ENRICHER");
    }

    private void headerEnricher() {
        printBanner("HEADER ENRICHER");
        Stream.iterate(0, n -> n + 1).limit(3).forEach(i -> {
            Message<?> intMessage = MessageBuilder
                    .withPayload("Hello World")
                    .setHeader("privateKey", "12345")
                    .build();
            this.headerEnricherGateway.print(intMessage);
        });

        printBanner("END HEADER ENRICHER");
    }

    public static void main(String[] args) {
        SpringApplication.run(MessageTransformerApp.class, args);
    }
}
