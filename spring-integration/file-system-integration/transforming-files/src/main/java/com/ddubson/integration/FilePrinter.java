package com.ddubson.integration;

import com.ddubson.LogAdapter;
import org.springframework.stereotype.Component;

import static com.ddubson.ANSIColor.ANSI_GREEN;

@Component("filePrinter")
public class FilePrinter {
    private LogAdapter logAdapter;

    public FilePrinter(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    public void print(String fileContents) {
        logAdapter.info("[2] File as string transformed: " + fileContents, ANSI_GREEN);
    }
}
