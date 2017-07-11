package com.ddubson;

import org.springframework.messaging.Message;

public class PrintService {
    public void print(Message<String> msg) {
        System.out.println(msg.getPayload());
    }
}
