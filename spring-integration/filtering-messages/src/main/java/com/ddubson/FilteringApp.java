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
public class FilteringApp implements ApplicationRunner {
    @Autowired
    @Qualifier("gateway")
    private PrinterGateway gateway;

    @Autowired
    @Qualifier("customFilterGateway")
    private PrinterGateway customFilterGateway;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //basicFilter();
        customFilter();
    }

    private void customFilter() {
        printBanner("CUSTOM FILTER");
        Stream.iterate(0, n -> n + 1).limit(10).forEach(i -> {
            Message<?> intMessage = MessageBuilder.withPayload(i).build();
            this.customFilterGateway.print(intMessage);
        });

        printBanner("END CUSTOM FILTER");
    }

    private void basicFilter() {
        printBanner("BASIC FILTER");
        Stream.iterate(0, n -> n + 1).limit(10).forEach(i -> {
            Message<?> intMessage = MessageBuilder.withPayload(i).build();
            this.gateway.print(intMessage);
        });

        printBanner("END BASIC FILTER");
    }

    public static void main(String[] args) {
        SpringApplication.run(FilteringApp.class, args);
    }
}
