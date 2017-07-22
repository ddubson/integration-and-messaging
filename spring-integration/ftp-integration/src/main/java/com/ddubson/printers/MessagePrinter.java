package com.ddubson.printers;

import org.springframework.messaging.Message;

public class MessagePrinter {
    public void print(Message<?> message) {
        System.out.println(message);
    }
}
