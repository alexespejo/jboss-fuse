package com.boraji.tutorial.spring.service;

import java.util.List;

import com.boraji.tutorial.spring.entity.Person;

public interface PersonService {
    void add(Person person);
    List<Person> listPersons();
}
