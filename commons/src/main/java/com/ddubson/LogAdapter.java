package com.ddubson;

public interface LogAdapter {
    void info(String message);

    void info(String message, ANSIColor color);
}
