package com.ddubson.services;

import org.springframework.messaging.Message;

public interface PrintService {
    public void print(Message<String> msg);
}
