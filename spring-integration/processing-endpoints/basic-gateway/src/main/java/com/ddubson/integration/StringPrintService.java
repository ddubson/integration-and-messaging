package com.ddubson.integration;

import com.ddubson.ANSIColor;
import com.ddubson.LogAdapter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class StringPrintService implements PrintService {
    private LogAdapter logAdapter;

    public StringPrintService(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    @Override
    public void print(Message<?> msg) {
        logAdapter.info("[2.A] In print method.", ANSIColor.ANSI_CYAN);
        msg.getHeaders().forEach((k, v) -> logAdapter.info(format("\t[HEADER] %s : %s", k, v), ANSIColor.ANSI_YELLOW));
        logAdapter.info(format("\t[%s] %s", Thread.currentThread().getName(), msg.getPayload()), ANSIColor.ANSI_YELLOW);
    }
}
