package com.ddubson.integration.services;

import com.ddubson.LogAdapter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.ddubson.ANSIColor.ANSI_CYAN;
import static java.lang.String.format;

@Component("numericPrintService")
public class NumericPrintService implements PrintService {
    private LogAdapter logAdapter;

    public NumericPrintService(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    @Override
    public void print(Message<String> msg) {
        logAdapter.info("[2] Integer service: " +
                format("[%s] %s", Thread.currentThread().getName(),
                        msg.getPayload()), ANSI_CYAN);    }
}
