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
public class SplitAggregateApp implements ApplicationRunner {
    private final String[] payloads = {"Hello World !", "Hey There", "Good Bye"};

    @Autowired
    @Qualifier("gateway")
    private PrinterGateway gateway;

    @Autowired
    @Qualifier("aggGateway")
    private PrinterGateway aggGateway;

    @Autowired
    @Qualifier("customAggGateway")
    private PrinterGateway customAggGateway;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //splitter();
        //splitterAndAggregator();
        customAggregator();
    }

    private void customAggregator() throws InterruptedException {
        printBanner("CUSTOM AGGREGATOR");

        Stream.iterate(0, n -> n + 1).limit(payloads.length).forEach(i -> {
            Message<?> message = MessageBuilder
                    .withPayload(payloads[i]).build();
            this.customAggGateway.print(message);
        });

        printBanner("END CUSTOM AGGREGATOR");
    }

    private void splitterAndAggregator() {
        printBanner("SPLITTER AND AGGREGATOR");

        Stream.iterate(0, n -> n + 1).limit(payloads.length).forEach(i -> {
            Message<?> intMessage = MessageBuilder
                    .withPayload(payloads[i])
                    .build();
            this.aggGateway.print(intMessage);
        });

        printBanner("END SPLITTER AND AGGREGATOR");
    }

    private void splitter() {
        printBanner("CUSTOM SPLITTER");
        Stream.iterate(0, n -> n + 1).limit(10).forEach(i -> {
            Message<?> intMessage = MessageBuilder.withPayload("Hello World").build();
            this.gateway.print(intMessage);
        });

        printBanner("END CUSTOM SPLITTER");
    }

    public static void main(String[] args) {
        SpringApplication.run(SplitAggregateApp.class, args);
    }
}
