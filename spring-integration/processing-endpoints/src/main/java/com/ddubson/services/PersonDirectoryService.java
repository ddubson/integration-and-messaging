package com.ddubson.services;

import com.ddubson.Person;

public class PersonDirectoryService {
    public Person findNewPeople() {
        System.out.println("Inbound polled.");
        return new Person("Jane", "Doe");
    }
}
