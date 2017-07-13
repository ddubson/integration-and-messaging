package com.ddubson.services;

import com.ddubson.Person;

public class UppercaseStringService {
   public String execute(Person person) {
       return (person.getFirstName() + " " + person.getLastName()).toUpperCase();
   }
}
