package com.ddubson.integration.services;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class DefaultPrintService implements PrintService {
    @Override
    public void print(Message<String> msg) {
        System.out.format("[%s] Default Channel: %s\n", Thread.currentThread().getName(), msg.getPayload());
    }
}
