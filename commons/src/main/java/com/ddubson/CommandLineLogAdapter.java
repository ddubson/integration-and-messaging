package com.ddubson;

public class CommandLineLogAdapter implements LogAdapter {
    @Override
    public void info(String message) {
        System.out.println(message);
    }

    @Override
    public void info(String message, ANSIColor color) {
        System.out.format("%s %s %s\n", color.color(), message, ANSIColor.ANSI_RESET.color());
    }
}
