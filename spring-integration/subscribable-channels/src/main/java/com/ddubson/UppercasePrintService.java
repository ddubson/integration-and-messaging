package com.ddubson;

import org.springframework.messaging.Message;

public class UppercasePrintService {
    public void print(Message<String> msg) {
        System.out.println(msg.getPayload().toUpperCase());
    }
}
