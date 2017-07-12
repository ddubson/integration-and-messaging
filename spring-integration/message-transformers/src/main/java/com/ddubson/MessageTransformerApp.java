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
    @Qualifier("gateway")
    private PrinterGateway gateway;

    @Autowired
    @Qualifier("customTransformerGateway")
    private PrinterGateway customTransformerGateway;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        basicTransformer();
        //customTransformer();
    }

    private void customTransformer() {
        printBanner("CUSTOM TRANSFORMER");
        Stream.iterate(0, n -> n + 1).limit(10).forEach(i -> {
            Message<?> intMessage = MessageBuilder.withPayload("Hello World").build();
            this.customTransformerGateway.print(intMessage);
        });

        printBanner("END CUSTOM TRANSFORMER");
    }

    private void basicTransformer() {
        printBanner("BASIC TRANSFORMER");
        Stream.iterate(0, n -> n + 1).limit(10).forEach(i -> {
            Message<?> intMessage = MessageBuilder.withPayload("Hello World").build();
            this.gateway.print(intMessage);
        });

        printBanner("END BASIC TRANSFORMER");
    }

    public static void main(String[] args) {
        SpringApplication.run(MessageTransformerApp.class, args);
    }
}
