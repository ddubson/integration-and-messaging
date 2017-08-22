package com.ddubson.integration.services;

import com.ddubson.ANSIColor;
import com.ddubson.LogAdapter;
import org.springframework.messaging.Message;

import static java.lang.String.format;

public class NumericPrintService implements PrintService {
    private final LogAdapter logAdapter;

    public NumericPrintService(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    @Override
    public void print(Message<String> msg) {
        logAdapter.info(format("[2] [%s] Numeric Service: %s", Thread.currentThread().getName(), msg.getPayload()), ANSIColor.ANSI_GREEN);
    }
}
