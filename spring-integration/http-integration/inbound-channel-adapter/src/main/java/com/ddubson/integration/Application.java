package com.ddubson.integration;

import com.ddubson.CommandLineLogAdapter;
import com.ddubson.LogAdapter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.ddubson.ANSIColor.ANSI_WHITE;

@SpringBootApplication
public class Application implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logAdapter().info("[1] Listen on HTTP endpoint.", ANSI_WHITE);
    }

    @Bean
    public LogAdapter logAdapter() {
        return new CommandLineLogAdapter();
    }

    @Bean
    public MessagePrinter messagePrinter() {
        return new MessagePrinter(logAdapter());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
