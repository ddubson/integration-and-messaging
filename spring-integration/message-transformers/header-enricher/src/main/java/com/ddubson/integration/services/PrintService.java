package com.ddubson.integration.services;

import org.springframework.messaging.Message;

public interface PrintService {
    void print(Message<String> msg);
}
