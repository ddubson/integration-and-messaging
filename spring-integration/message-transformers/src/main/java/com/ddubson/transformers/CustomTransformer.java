package com.ddubson.transformers;

import org.springframework.messaging.Message;

public class CustomTransformer {
    public String transform(Message<String> message) {
        String[] tokens = message.getPayload().split(" ");
        return tokens[0] + ", " + tokens[1];
    }
}
