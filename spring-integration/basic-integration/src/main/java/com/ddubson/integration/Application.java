package com.ddubson.integration;

import com.ddubson.CommandLineLogAdapter;
import com.ddubson.LogAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;

import java.util.HashMap;
import java.util.Map;

import static com.ddubson.ANSIColor.ANSI_BLUE;
import static com.ddubson.ANSIColor.ANSI_RED;

@SpringBootApplication
public class Application implements ApplicationRunner {
    @Autowired
    @Qualifier("inputChannel")
    private DirectChannel inputChannel;

    @Autowired
    @Qualifier("outputChannel")
    private DirectChannel outputChannel;

    @Autowired
    LogAdapter logAdapter;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // via Constructor
        Message<String> message = new GenericMessage<>("Hello World", createMessageHeaders());

        // via MessageBuilder
        Message<String> message2 = MessageBuilder
                .withPayload("Hello World (Builder Pattern)")
                .setHeader("newKey", "newValue")
                .build();

        // Add message handlers to output channel
        outputChannel.subscribe((msg) -> logAdapter.info("[3] Output channel received: " + msg, ANSI_BLUE));
        outputChannel.subscribe((msg) -> logAdapter.info("[4] Output channel received: " + msg, ANSI_BLUE));

        logAdapter.info("[1] Send two messages into input channel.", ANSI_RED);
        inputChannel.send(message);
        inputChannel.send(message2);
    }

    @Bean
    public LogAdapter logAdapter() {
        return new CommandLineLogAdapter();
    }

    private MessageHeaders createMessageHeaders() {
        Map<String, Object> map = new HashMap<>();
        map.put("key", "value");
        return new MessageHeaders(map);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
