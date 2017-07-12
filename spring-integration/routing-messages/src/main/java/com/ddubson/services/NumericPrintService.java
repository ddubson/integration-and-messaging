package com.ddubson.services;

import org.springframework.messaging.Message;

public class NumericPrintService implements PrintService {
    @Override
    public void print(Message<String> msg) {
        System.out.format("[%s] Numeric: %s\n", Thread.currentThread().getName(), msg.getPayload());
    }
}
