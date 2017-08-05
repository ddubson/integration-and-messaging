package com.ddubson.integration;

import com.ddubson.LogAdapter;
import org.springframework.stereotype.Component;

import static com.ddubson.ANSIColor.ANSI_CYAN;

@Component("customEnricher")
public class CustomHeaderEnricher {
    private LogAdapter logAdapter;

    public CustomHeaderEnricher(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    public String getHeaderValue() {
        logAdapter.info("[2] Adding header value to message.", ANSI_CYAN);
        return "this is the header value";
    }
}
