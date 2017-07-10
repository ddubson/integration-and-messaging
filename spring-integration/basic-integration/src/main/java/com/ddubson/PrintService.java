package com.ddubson;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public class PrintService {
    public Message<?> print(Message<String> msg) {
        msg.getHeaders().forEach((k, v) -> System.out.println(k + ":" + v));
        System.out.println(msg.getPayload());

        return MessageBuilder.withPayload("Received msg " + msg.getHeaders().get("id")).build();
    }
}
