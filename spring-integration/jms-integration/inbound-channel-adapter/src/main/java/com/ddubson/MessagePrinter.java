package com.ddubson;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.ddubson.ANSIColor.ANSI_PURPLE;

@Component
public class MessagePrinter {
    private LogAdapter logAdapter;

    public MessagePrinter(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    public void print(Message<?> message) {
        logAdapter.info("[4] Retrieved from sample queue via jmsChannel: " + message.getPayload(), ANSI_PURPLE);
    }
}
