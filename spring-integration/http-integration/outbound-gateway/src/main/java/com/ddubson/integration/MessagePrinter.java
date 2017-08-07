package com.ddubson.integration;

import com.ddubson.LogAdapter;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import static com.ddubson.ANSIColor.ANSI_CYAN;
import static com.ddubson.ANSIColor.ANSI_YELLOW;

@Component
public class MessagePrinter {
    private LogAdapter logAdapter;

    public MessagePrinter(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    public Message<?> print(Message<?> message) {
        logAdapter.info("[3] Consumed message from inbound http request: " + message, ANSI_YELLOW);
        logAdapter.info("[4] Sending reply to gateway", ANSI_CYAN);
        return MessageBuilder.withPayload("{proxy} " + message.getPayload()).build();
    }

    public void printConsole(Message<?> message) {
        logAdapter.info("[5] Http reply: " +message.getPayload(), ANSI_YELLOW);
    }
}
