package com.ddubson;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public class PrintService {
    public Message<?> print(Message<String> msg) {
        msg.getHeaders().forEach((k, v) -> System.out.println("[2] Header: " + k + ":" + v));
        System.out.println("[2] Payload: " + msg.getPayload());

        return MessageBuilder.withPayload("[2] Sending message to output channel: " + msg.getHeaders().get("id")).build();
    }
}
