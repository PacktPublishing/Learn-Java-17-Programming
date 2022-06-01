package com.packt.learnjava.spring.service;

import com.packt.learnjava.spring.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class PersonService {
    @Autowired
    DataSource ds;

    public Person insert(Person person){
        int id = Person.insert(getConnection(), person);
        person.setId(id);
        return person;
    }

    private Connection getConnection(){
        try {
            return ds.getConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }
}
