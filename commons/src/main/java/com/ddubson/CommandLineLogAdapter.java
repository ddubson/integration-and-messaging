package com.ddubson;

public class CommandLineLogAdapter implements LogAdapter {
    @Override
    public void info(String message) {
        System.out.println(message);
    }
}
