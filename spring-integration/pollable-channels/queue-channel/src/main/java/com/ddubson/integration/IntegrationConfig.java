package com.ddubson.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

@Configuration
public class IntegrationConfig {
    private final PrintService printService;

    public IntegrationConfig(PrintService printService) {
        this.printService = printService;
    }

    @Bean("inputChannel")
    public MessageChannel queueChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean(PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(2000).maxMessagesPerPoll(2).get();
    }

    /**
     * PrinterGateway::print ->
     *  -> inputChannel
     *  -> PrintService::print (Service Activator)
     */
    @Bean
    public IntegrationFlow integrationFlow() {
        return IntegrationFlows
                .from("inputChannel")
                .<Message<String>,Void>handle(printService)
                .get();
    }
}
