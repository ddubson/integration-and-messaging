package com.ddubson.integration;

import com.ddubson.LogAdapter;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import static com.ddubson.ANSIColor.*;

public class PrintService {
    private final LogAdapter logAdapter;

    public PrintService(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    public Message<?> print(Message<String> msg) {
        msg.getHeaders().forEach((k, v) -> logAdapter.info("[2] Header: " + k + ":" + v, ANSI_GREEN));
        logAdapter.info("[2] Payload: " + msg.getPayload(), ANSI_GREEN);

        return MessageBuilder.withPayload(msg.getHeaders().get("id")).build();
    }
}
