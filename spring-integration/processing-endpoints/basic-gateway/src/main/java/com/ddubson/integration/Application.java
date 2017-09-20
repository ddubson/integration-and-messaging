package com.ddubson.integration;

import com.ddubson.CommandLineLogAdapter;
import com.ddubson.LogAdapter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.ddubson.ANSIColor.*;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.iterate;

@SpringBootApplication
@ImportResource("integration-context.xml")
public class Application implements ApplicationRunner {
    private EnhancedPrinterGateway enhancedPrinterGateway;

    public Application(EnhancedPrinterGateway enhancedPrinterGateway) {
        this.enhancedPrinterGateway = enhancedPrinterGateway;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logAdapter().info("[1] Send messages to gateway.", ANSI_YELLOW);
        iterate(0, n -> n + 1)
                .limit(3)
                .forEach(i -> {
                    logAdapter().info("[1.A] Send person message to print method in gateway.", ANSI_CYAN);
                    this.enhancedPrinterGateway.print(new Person("Shane", "Doe"));
                });

        List<ListenableFuture<String>> futures =
                iterate(0, n -> n + 1)
                        .limit(3)
                        .map(i -> {
                            logAdapter().info("[1.B] Send person message to uppercase method in gateway.", ANSI_PURPLE);
                            return this.enhancedPrinterGateway.uppercase(new Person("John", "Doe"));
                        })
                        .peek(future -> future.addCallback(
                                (result) -> {
                                    logAdapter().info("[2.B] Returned from uppercase method with result: " + result, ANSI_WHITE);
                                },
                                (ex) -> {
                                    logAdapter().info("[2.X] There was an error in uppercase method.", ANSI_YELLOW);
                                }))
                        .collect(toList());

        futures.forEach(f -> {
            try {
                f.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    @Bean
    public LogAdapter logAdapter() {
        return new CommandLineLogAdapter();
    }
}
