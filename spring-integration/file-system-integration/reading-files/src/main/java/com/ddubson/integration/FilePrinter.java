package com.ddubson.integration;

import com.ddubson.LogAdapter;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.ddubson.ANSIColor.ANSI_GREEN;

@Component("filePrinter")
public class FilePrinter {
    private LogAdapter logAdapter;

    public FilePrinter(LogAdapter logAdapter) {
        this.logAdapter = logAdapter;
    }

    public void print(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.lines().forEach(line -> logAdapter.info("[2][file:"+file.getName()+"] " + line, ANSI_GREEN));
        }
    }
}
