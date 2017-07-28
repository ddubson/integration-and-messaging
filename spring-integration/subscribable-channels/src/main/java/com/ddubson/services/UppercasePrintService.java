package com.ddubson.services;

import org.springframework.messaging.Message;

public class UppercasePrintService {
    public void print(Message<String> msg) {
        System.out.format("[%s] %s\n", Thread.currentThread().getName(), msg.getPayload().toUpperCase());
    }
}
