package com.ddubson.services;

import org.springframework.messaging.Message;

public class DefaultPrintService implements PrintService {
    @Override
    public void print(Message<String> msg) {
        System.out.format("[%s] Default Channel: %s\n", Thread.currentThread().getName(), msg.getPayload());
    }
}
