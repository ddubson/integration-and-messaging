package com.ddubson;

import org.springframework.messaging.Message;

public class StringPrintService implements PrintService {
    @Override
    public void print(Message<String> msg) {
        System.out.format("[%s] %s\n", Thread.currentThread().getName(), msg.getPayload());
    }
}
