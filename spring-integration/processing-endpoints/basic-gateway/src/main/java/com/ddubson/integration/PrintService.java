package com.ddubson.integration;

import org.springframework.messaging.Message;

public interface PrintService {
    void print(Message<?> msg);
}
