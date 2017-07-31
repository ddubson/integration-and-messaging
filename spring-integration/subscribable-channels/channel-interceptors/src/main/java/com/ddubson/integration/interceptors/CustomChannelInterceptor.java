package com.ddubson.integration.interceptors;

import com.ddubson.LogAdapter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import static com.ddubson.ANSIColor.ANSI_GREEN;

@Component
public class CustomChannelInterceptor extends ChannelInterceptorAdapter {
    private LogAdapter logAdapter;

    public CustomChannelInterceptor(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        logAdapter.info("[2] " + message.getPayload() + " intercepted. Putting back to input channel.", ANSI_GREEN);
        Message<?> msg = MessageBuilder.withPayload(message.getPayload().toString() + " [INTERCEPTED]").build();
        return super.preSend(msg, channel);
    }
}
