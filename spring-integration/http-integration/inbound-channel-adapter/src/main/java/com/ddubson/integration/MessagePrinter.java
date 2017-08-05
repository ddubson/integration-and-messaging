package com.ddubson.integration;

import com.ddubson.LogAdapter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.ddubson.ANSIColor.*;

@Component
public class MessagePrinter {
    private LogAdapter logAdapter;

    public MessagePrinter(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    public void print(Message<?> message) {
        logAdapter.info("[2] Consumed message from inbound http request: " + message, ANSI_YELLOW);
    }
}
