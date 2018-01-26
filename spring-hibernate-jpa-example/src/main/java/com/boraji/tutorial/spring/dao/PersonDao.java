package com.boraji.tutorial.spring.dao;

import java.util.List;

import com.boraji.tutorial.spring.entity.Person;

public interface PersonDao {
   void add(Person person);
   List<Person> listPersons();
}
