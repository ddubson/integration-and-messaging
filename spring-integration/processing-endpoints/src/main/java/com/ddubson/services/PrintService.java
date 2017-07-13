package com.ddubson.services;

import org.springframework.messaging.Message;

public interface PrintService {
    void print(Message<?> msg);
}
