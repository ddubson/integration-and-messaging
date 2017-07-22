package com.ddubson.printers;

import java.io.*;

public class FilePrinter {
    public void print(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.lines().forEach(line -> System.out.println("[FILE-PARSED] " + line));
        }
    }

    public void print(String fileContents) {
        System.out.println("[FILE-AS-STRING] Invoking print method with a string.");
        System.out.println("[FILE-AS-STRING] " + fileContents);
    }
}
