package com.ddubson.gateways;

import com.ddubson.Person;

import java.util.concurrent.Future;

public interface EnhancedPrinterGateway {
    Future<Void> print(Person person);

    Future<String> uppercase(Person person);
}
