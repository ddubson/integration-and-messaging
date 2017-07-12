package com.ddubson.aggregators;

import org.springframework.integration.aggregator.AbstractAggregatingMessageGroupProcessor;
import org.springframework.integration.store.MessageGroup;

import java.util.Map;

import static org.springframework.integration.IntegrationMessageHeaderAccessor.CORRELATION_ID;

public class CustomAggregator extends AbstractAggregatingMessageGroupProcessor {
    @Override
    protected Object aggregatePayloads(MessageGroup group, Map<String, Object> defaultHeaders) {
       StringBuilder builder = new StringBuilder();

       group.getMessages().forEach(message -> {
           System.out.println(message.getHeaders().get(CORRELATION_ID));
           builder.append(message.getPayload());
       });

       return builder.toString();
    }
}
