package com.ddubson;

import com.ddubson.gateways.PrinterGateway;
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
    @Qualifier("interceptedChannelGateway")
    private PrinterGateway interceptedChannelGateway;

    @Override
    public void run(ApplicationArguments args) throws Exception {
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

    public static void main(String[] args) {
        SpringApplication.run(SubscribableChannelsApp.class, args);
    }
}
