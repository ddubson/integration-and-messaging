package com.ddubson.integration;

import java.util.concurrent.Future;

public interface SimpleGateway {
    Future<String> execute(String message);
}
