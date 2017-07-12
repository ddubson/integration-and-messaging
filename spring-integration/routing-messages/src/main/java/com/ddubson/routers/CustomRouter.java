package com.ddubson.routers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.router.AbstractMessageRouter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import java.util.ArrayList;
import java.util.Collection;

public class CustomRouter extends AbstractMessageRouter {
    @Autowired
    @Qualifier("intChannel")
    private MessageChannel intChannel;

    @Autowired
    @Qualifier("stringChannel")
    private MessageChannel stringChannel;

    @Autowired
    @Qualifier("defaultChannel")
    private MessageChannel defaultChannel;

    @Override
    protected Collection<MessageChannel> determineTargetChannels(Message<?> message) {
        Collection<MessageChannel> targetChannels = new ArrayList<>();

        if(message.getPayload().getClass().equals(Integer.class)) {
            targetChannels.add(intChannel);
        } else if(message.getPayload().getClass().equals(String.class)) {
            targetChannels.add(stringChannel);
        } else {
            targetChannels.add(defaultChannel);
        }

        return targetChannels;
    }
}
