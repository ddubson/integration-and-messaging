package com.ddubson.integration;

import com.ddubson.LogAdapter;
import org.springframework.stereotype.Component;

import static com.ddubson.ANSIColor.ANSI_YELLOW;

@Component
public class PayloadEnricherService {
    private LogAdapter logAdapter;

    public PayloadEnricherService(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    public String getPhoneNumber(Person person) {
        logAdapter.info("[2] Adding to payload.", ANSI_YELLOW);
        return "867-5309";
    }
}
