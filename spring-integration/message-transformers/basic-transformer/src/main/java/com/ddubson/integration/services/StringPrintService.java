package com.ddubson.integration.services;

import com.ddubson.LogAdapter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.ddubson.ANSIColor.ANSI_GREEN;
import static java.lang.String.format;

@Component
public class StringPrintService implements PrintService {
    private LogAdapter logAdapter;

    public StringPrintService(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    @Override
    public void print(Message<String> msg) {
        logAdapter.info("[2] Transformed message (uppercase): " + format("[%s] %s", Thread.currentThread().getName(),
                msg.getPayload()), ANSI_GREEN);
    }
}
