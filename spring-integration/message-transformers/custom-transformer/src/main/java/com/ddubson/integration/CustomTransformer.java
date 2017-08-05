package com.ddubson.integration;

import com.ddubson.LogAdapter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.ddubson.ANSIColor.ANSI_CYAN;

@Component
public class CustomTransformer {
    private LogAdapter logAdapter;

    public CustomTransformer(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    public String transform(Message<String> message) {
        logAdapter.info("[2] Custom transform, add comma to message: " + message.getPayload(), ANSI_CYAN);
        String[] tokens = message.getPayload().split(" ");
        return tokens[0] + ", " + tokens[1];
    }
}
