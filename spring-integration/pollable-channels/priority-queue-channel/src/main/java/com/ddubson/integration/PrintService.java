package com.ddubson.integration;

import com.ddubson.ANSIColor;
import com.ddubson.LogAdapter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class PrintService {
    private final LogAdapter logAdapter;

    public PrintService(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    public Message<String> print(Message<String> msg) {
        logAdapter.info(
                format("[2] Consuming input channel -- [%s] %s", Thread.currentThread().getName(),
                        msg.getPayload()), ANSIColor.ANSI_GREEN);
        return msg;
    }
}
