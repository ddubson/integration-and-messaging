package com.ddubson.integration;

import com.ddubson.LogAdapter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.ddubson.ANSIColor.ANSI_GREEN;

@Component
public class MessagePrinter {
    private LogAdapter logAdapter;

    public MessagePrinter(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    public void print(Message<?> message) {
        logAdapter.info("[3] Gateway replied with: " + message, ANSI_GREEN);
    }
}
