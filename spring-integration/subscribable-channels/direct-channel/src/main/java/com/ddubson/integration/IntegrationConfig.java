package com.ddubson.integration;

import com.ddubson.integration.services.PrintService;
import com.ddubson.integration.services.UppercasePrintService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import static java.util.concurrent.Executors.newCachedThreadPool;

@Configuration
public class IntegrationConfig {
    private PrintService printService;
    private UppercasePrintService uppercasePrintService;

    public IntegrationConfig(PrintService printService,
                             UppercasePrintService uppercasePrintService) {
        this.printService = printService;
        this.uppercasePrintService = uppercasePrintService;
    }

    @Bean("inputChannel")
    public MessageChannel inputChannel() {
        return MessageChannels.direct("inputChannel").get();
    }

    @Bean
    public IntegrationFlow integrationFlow() {
        IntegrationFlow consumer1 = f -> f.<Message<String>, Void>handle(printService, "print", c -> c.order(1));
        IntegrationFlow consumer2 = f -> f.<Message<String>, Void>handle(uppercasePrintService, "print", c -> c.order(2));

        return IntegrationFlows.from("inputChannel")
                .publishSubscribeChannel(newCachedThreadPool(),
                        s -> s.subscribe(consumer1).subscribe(consumer2))
                .get();
    }
}
