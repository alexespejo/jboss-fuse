package com.boraji.tutorial.spring;

import java.sql.SQLException;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.boraji.tutorial.spring.config.AppConfig;
import com.boraji.tutorial.spring.entity.Person;
import com.boraji.tutorial.spring.service.PersonService;

/**
 * @author imssbora
 *
 */
public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      PersonService personService = context.getBean(PersonService.class);

      // Add Persons
      personService.add(new Person("Sunil", "Bora", "suni.bora@example.com"));
      personService.add(new Person("David", "Miller", "david.miller@example.com"));
      personService.add(new Person("Sameer", "Singh", "sameer.singh@example.com"));
      personService.add(new Person("Paul", "Smith", "paul.smith@example.com"));

      // Get Persons
      List<Person> persons = personService.listPersons();
      for (Person person : persons) {
         System.out.println("Id = "+person.getId());
         System.out.println("First Name = "+person.getFirstName());
         System.out.println("Last Name = "+person.getLastName());
         System.out.println("Email = "+person.getEmail());
         System.out.println();
      }

      context.close();
   }
}
