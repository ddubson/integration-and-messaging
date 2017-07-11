package com.ddubson;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageBuilder;

public class CustomChannelInterceptor extends ChannelInterceptorAdapter {
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        Message<?> msg = MessageBuilder.withPayload(message.getPayload().toString() + " [INTERCEPTED]").build();
        return super.preSend(msg, channel);
    }
}
