package com.ddubson;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public class PrintService {
    public Message<?> print(Message<String> msg) {
        System.out.println("PrintService: "+ msg.getPayload());
        return MessageBuilder.withPayload("PrintService Response Message: " + msg.getHeaders().get("id")).build();
    }
}
