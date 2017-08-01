package com.ddubson;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class MessagePrinter {
    public void print(Message<?> message) {
        System.out.println(message);
    }
}
