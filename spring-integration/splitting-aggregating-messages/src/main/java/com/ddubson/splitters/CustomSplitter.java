package com.ddubson.splitters;

import org.springframework.messaging.Message;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class CustomSplitter {
    public List<String> split(Message<?> message) {
        return new ArrayList<>(
                asList(message.getPayload().toString().split(" ")));
    }
}
