package com.ddubson.services;

import org.springframework.messaging.Message;

public class StringPrintService implements PrintService {
    @Override
    public void print(Message<?> msg) {
        msg.getHeaders().forEach((k, v) -> System.out.format("[HEADER] %s : %s\n", k, v));
        System.out.format("[%s] %s\n", Thread.currentThread().getName(), msg.getPayload());
    }
}
