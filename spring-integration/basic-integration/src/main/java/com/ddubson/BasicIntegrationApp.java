package com.ddubson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@ImportResource("integration-context.xml")
public class BasicIntegrationApp implements ApplicationRunner {
    @Autowired
    @Qualifier("inputChannel")
    private DirectChannel inputChannel;

    @Autowired
    @Qualifier("outputChannel")
    private DirectChannel outputChannel;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Message<String> message = new GenericMessage<>("Hello World", createMessageHeaders());
        Message<String> message2 = MessageBuilder
                .withPayload("Hello World (Builder Pattern)")
                .setHeader("newKey", "newValue")
                .build();

        outputChannel.subscribe((System.out::println));
        inputChannel.send(message);
        inputChannel.send(message2);
    }

    private MessageHeaders createMessageHeaders() {
        Map<String, Object> map = new HashMap<>();
        map.put("key", "value");
        return new MessageHeaders(map);
    }

    public static void main(String[] args) {
        SpringApplication.run(BasicIntegrationApp.class, args);
    }
}
