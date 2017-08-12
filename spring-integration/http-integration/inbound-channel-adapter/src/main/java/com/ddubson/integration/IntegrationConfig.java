package com.ddubson.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.http.Http;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

@Configuration
public class IntegrationConfig {
    private MessagePrinter messagePrinter;

    public IntegrationConfig(MessagePrinter messagePrinter) {
        this.messagePrinter = messagePrinter;
    }

    @Bean("httpChannel")
    public MessageChannel httpChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public HttpRequestHandlingMessagingGateway inboundHttpChannelAdapter() {
        return Http.inboundChannelAdapter("/")
                .requestChannel("httpChannel")
                .get();
    }

    @Bean
    public IntegrationFlow integrationFlow() {
        return IntegrationFlows.from(inboundHttpChannelAdapter())
                .<Message<?>, Void>handle(messagePrinter).get();
    }
}
