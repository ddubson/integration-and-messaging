package com.ddubson.integration;

import com.ddubson.CommandLineLogAdapter;
import com.ddubson.LogAdapter;
import com.ddubson.integration.gateways.PrinterGateway;
import com.ddubson.integration.interceptors.CustomChannelInterceptor;
import com.ddubson.integration.services.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.stream.Stream;

import static com.ddubson.ANSIColor.ANSI_WHITE;

@SpringBootApplication
public class Application implements ApplicationRunner {
    @Autowired
    private PrinterGateway gateway;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        channelInterceptor();
    }

    private void channelInterceptor() {
        logAdapter().info("[1] Subscribable Round-Robin Direct Channel - Channel Interceptor", ANSI_WHITE);
        Stream.iterate(0, n -> n + 1).limit(10).forEach(i -> {
            Message<String> message = MessageBuilder.withPayload("Message " + i)
                    .build();
            logAdapter().info("[1] Sending message " + i + " to input channel.", ANSI_WHITE);
            this.gateway.print(message);
        });
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public LogAdapter logAdapter() {
        return new CommandLineLogAdapter();
    }

    @Bean
    public PrintService printService() {
        return new PrintService(logAdapter());
    }

    @Bean
    public CustomChannelInterceptor customChannelInterceptor() {
        return new CustomChannelInterceptor(logAdapter());
    }
}
