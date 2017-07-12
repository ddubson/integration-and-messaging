package com.ddubson;

import org.springframework.messaging.Message;

/**
 * Created by pivotal on 7/12/17.
 */
public class DefaultService implements PrintService {
    @Override
    public void print(Message<String> msg) {
        System.out.println("Printing default: " + msg.getPayload());
    }
}
