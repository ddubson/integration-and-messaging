package com.ddubson.services;

import com.ddubson.Person;

public class PersonRegistrationService {
    public void registerEmail(Person person) {
        System.out.println("Outbound called.");
        System.out.format("Email created for %s\n", person.getLastName());
    }
}
