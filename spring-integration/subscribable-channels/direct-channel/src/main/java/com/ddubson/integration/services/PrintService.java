package com.ddubson.integration.services;

import com.ddubson.ANSIColor;
import com.ddubson.LogAdapter;
import org.springframework.messaging.Message;

import static java.lang.String.format;

public class PrintService {
    private final LogAdapter logAdapter;

    public PrintService(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    public void print(Message<String> msg) {
        logAdapter.info(
                format("[2] Consuming input channel -- [%s] %s", Thread.currentThread().getName(),
                        msg.getPayload()), ANSIColor.ANSI_GREEN);
    }
}
