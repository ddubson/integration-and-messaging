package com.ddubson;

import org.springframework.messaging.Message;

import java.util.concurrent.Future;

/**
 * Created by pivotal on 7/11/17.
 */
public interface PrinterGateway {
    Future<Message<?>> print(Message<?> message);
}
