package com.ddubson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.stream.Stream;

import static com.ddubson.ConsolePrinterUtils.printBanner;

@SpringBootApplication
@ImportResource("integration-context.xml")
public class SubscribableChannelsApp implements ApplicationRunner {
    @Autowired
    @Qualifier("directChannelGateway")
    private PrinterGateway directChannelGateway;

    @Autowired
    @Qualifier("pubSubChannelGateway")
    private PrinterGateway pubSubChannelGateway;

    @Autowired
    @Qualifier("interceptedChannelGateway")
    private PrinterGateway interceptedChannelGateway;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //subscribableRoundRobin();
        //pubSubChannel();
        //messagingBridgeBetweenPollableAndSubscribableChannels();
        channelInterceptor();
    }

    private void channelInterceptor() {
        printBanner("SUBSCRIBABLE CHANNEL INTERCEPTOR CHANNEL");
        Stream.iterate(0, n -> n + 1).limit(10).forEach(i -> {
            Message<String> message = MessageBuilder.withPayload("[INTERCEPTORS] Message " + i)
                    .build();
            this.interceptedChannelGateway.print(message);
        });

        printBanner("END SUBSCRIBABLE CHANNEL INTERCEPTOR CHANNEL");
    }

    private void messagingBridgeBetweenPollableAndSubscribableChannels() {
        printBanner("BRIDGED CHANNEL");
        Stream.iterate(0, n -> n + 1).limit(10).forEach(i -> {
            Message<String> message = MessageBuilder.withPayload("[BRIDGED] Message " + i)
                    .build();
            this.pubSubChannelGateway.print(message);
        });

        printBanner("END BRIDGED CHANNEL");
    }

    private void pubSubChannel() {
        printBanner("SUBSCRIBABLE PUB-SUB CHANNEL");
        Stream.iterate(0, n -> n + 1).limit(10).forEach(i -> {
            Message<String> message = MessageBuilder.withPayload("[PUB-SUB] Message " + i)
                    .build();
            this.pubSubChannelGateway.print(message);
        });

        printBanner("END SUBSCRIBABLE PUB-SUB CHANNEL");
    }

    private void subscribableRoundRobin() {
        printBanner("SUBSCRIBABLE ROUND-ROBIN CHANNEL");
        Stream.iterate(0, n -> n + 1).limit(10).forEach(i -> {
            Message<String> message = MessageBuilder.withPayload("[ROUND-ROBIN] Message " + i)
                    .build();
            this.directChannelGateway.print(message);
        });

        printBanner("END SUBSCRIBABLE ROUND-ROBIN CHANNEL");
    }

    public static void main(String[] args) {
        SpringApplication.run(SubscribableChannelsApp.class, args);
    }
}
