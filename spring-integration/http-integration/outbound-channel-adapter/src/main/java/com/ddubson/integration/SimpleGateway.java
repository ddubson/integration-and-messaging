package com.ddubson.integration;

import java.util.concurrent.Future;

public interface SimpleGateway {
    public Future<String> execute(String message);
}
