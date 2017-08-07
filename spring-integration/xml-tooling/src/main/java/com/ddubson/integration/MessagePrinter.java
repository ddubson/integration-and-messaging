package com.ddubson.integration;

import com.ddubson.LogAdapter;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import static com.ddubson.ANSIColor.ANSI_YELLOW;

@Component
public class MessagePrinter {
    private LogAdapter logAdapter;

    public MessagePrinter(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    public Message<?> print(Message<?> message) {
        return MessageBuilder.withPayload("{proxy} " + message.getPayload()).build();
    }

    public void printConsole(Message<?> message) {
        logAdapter.info("[ServiceActivator] " + message.getPayload(), ANSI_YELLOW);
        message.getHeaders().forEach((k,v) -> logAdapter.info("[ServiceActivator][header] " + k + ": " + v));
    }
}
