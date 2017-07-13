package com.ddubson;

import com.ddubson.gateways.EnhancedPrinterGateway;
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

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static com.ddubson.ConsolePrinterUtils.printBanner;
import static java.util.stream.Collectors.toList;

@SpringBootApplication
@ImportResource("integration-context.xml")
public class ProcessEndpointsApp implements ApplicationRunner {
    @Autowired
    @Qualifier("gateway")
    private PrinterGateway gateway;

    @Autowired
    @Qualifier("gateway2")
    private EnhancedPrinterGateway enhancedPrinterGateway;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //serviceActivator();
        gateways();
    }

    private void gateways() {
        printBanner("GATEWAYS");
        Stream.iterate(0, n -> n + 1).limit(3).forEach(i -> {
            this.enhancedPrinterGateway.print(new Person("Shane","Doe"));
        });

        List<Future<String>> futures = Stream
                .iterate(0, n -> n + 1)
                .limit(3)
                .map(i -> this.enhancedPrinterGateway.uppercase(new Person("John","Doe")))
                .collect(toList());

        futures.forEach(f -> {
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        printBanner("END GATEWAYS");
    }

    private void serviceActivator() {
        printBanner("SERVICE ACTIVATOR");
        Stream.iterate(0, n -> n + 1).limit(3).forEach(i -> {
            Message<?> message = MessageBuilder
                    .withPayload(new Person("John", "Doe"))
                    .build();
            this.gateway.print(message);
        });

        printBanner("END SERVICE ACTIVATOR");
    }

    public static void main(String[] args) {
        SpringApplication.run(ProcessEndpointsApp.class, args);
    }
}
