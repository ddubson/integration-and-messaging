package com.ddubson.integration;

import com.ddubson.integration.services.PrintService;
import com.ddubson.integration.services.UppercasePrintService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class IntegrationConfig {
    private PrintService printService;
    private UppercasePrintService uppercasePrintService;

    public IntegrationConfig(PrintService printService, UppercasePrintService uppercasePrintService) {
        this.printService = printService;
        this.uppercasePrintService = uppercasePrintService;
    }

    @Bean("bridgedPollableChannel")
    public MessageChannel bridgedPollableChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor t = new ThreadPoolTaskExecutor();
        t.setCorePoolSize(5);
        return t;
    }

    @Bean
    public IntegrationFlow integrationFlow() {
        IntegrationFlow subscriber1 = f -> f.handle(printService, "print", c -> c.order(1));
        IntegrationFlow subscriber2 = f -> f.handle(uppercasePrintService, "print", c -> c.order(2));

        return IntegrationFlows.from("bridgedPollableChannel")
                .bridge(b -> b.id("messageBridge").poller(Pollers.fixedDelay(5000).maxMessagesPerPoll(2)))
                .publishSubscribeChannel(taskExecutor(),
                        f -> f.id("bridgedPubSubChannel")
                                .subscribe(subscriber1).subscribe(subscriber2)
                ).get();
    }
}
