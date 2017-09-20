package com.ddubson.integration;

import org.springframework.util.concurrent.ListenableFuture;

public interface EnhancedPrinterGateway {
    ListenableFuture<Void> print(Person person);

    ListenableFuture<String> uppercase(Person person);
}
