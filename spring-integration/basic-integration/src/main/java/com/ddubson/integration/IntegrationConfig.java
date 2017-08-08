package com.ddubson.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

@Configuration
public class IntegrationConfig {
    private final PrintService printService;

    public IntegrationConfig(PrintService printService) {
        this.printService = printService;
    }

    @Bean("inputChannel")
    public DirectChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean("outputChannel")
    public DirectChannel outputChannel() {
        return new DirectChannel();
    }

    /**
     * Input Channel
     * -> Print Service (Service Activator)
     * -> Output Channel
     */
    @Bean
    public IntegrationFlow basicFlow() {
        return IntegrationFlows
                .from(inputChannel())
                .handle(printService)
                .channel(outputChannel())
                .get();
    }
}
