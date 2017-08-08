package com.ddubson.integration;

import com.ddubson.integration.services.PrintService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class IntegrationConfig {
    private ChannelInterceptorAdapter customChannelInterceptor;
    private PrintService printService;

    public IntegrationConfig(ChannelInterceptorAdapter customChannelInterceptor, PrintService printService) {
        this.customChannelInterceptor = customChannelInterceptor;
        this.printService = printService;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        return executor;
    }

    @Bean("inputChannel")
    public MessageChannel pubSubChannel() {
        return MessageChannels.publishSubscribe("inputChannel", taskExecutor())
                .interceptor(customChannelInterceptor).get();
    }

    @Bean
    public IntegrationFlow integrationFlow() {
        return IntegrationFlows.from("inputChannel")
                .<Message<String>, Void>handle(printService, "print").get();
    }

}
