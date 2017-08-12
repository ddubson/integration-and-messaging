package com.ddubson.integration;

import com.ddubson.LogAdapter;
import org.springframework.messaging.Message;

import static com.ddubson.ANSIColor.ANSI_YELLOW;

public class MessagePrinter {
    private LogAdapter logAdapter;

    public MessagePrinter(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    public void print(Message<?> message) {
        logAdapter.info("[2] Consumed message from inbound http request: " + message, ANSI_YELLOW);
    }
}
