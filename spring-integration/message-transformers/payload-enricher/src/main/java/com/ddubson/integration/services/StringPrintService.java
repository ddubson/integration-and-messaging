package com.ddubson.integration.services;

import com.ddubson.LogAdapter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.ddubson.ANSIColor.ANSI_CYAN;
import static java.lang.String.format;

@Component
public class StringPrintService implements PrintService {
    private LogAdapter logAdapter;

    public StringPrintService(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    @Override
    public void print(Message<String> msg) {
        logAdapter.info("[3] Enriched message: " + format("[%s] %s",
                Thread.currentThread().getName(), msg.getPayload()), ANSI_CYAN);
        msg.getHeaders().forEach(
                (k, v) -> logAdapter.info("[3] Enriched header: (" + k + ":" + v + ")", ANSI_CYAN));
    }
}
