package com.ddubson.integration.services;

import com.ddubson.ANSIColor;
import com.ddubson.LogAdapter;
import org.springframework.messaging.Message;

import static java.lang.String.format;

public class UppercasePrintService {
    private final LogAdapter logAdapter;

    public UppercasePrintService(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    public void print(Message<String> msg) {
        logAdapter.info(
                format("[2] Consuming pub-sub channel -- [%s] %s", Thread.currentThread().getName(),
                        msg.getPayload().toUpperCase()), ANSIColor.ANSI_CYAN);
    }
}
