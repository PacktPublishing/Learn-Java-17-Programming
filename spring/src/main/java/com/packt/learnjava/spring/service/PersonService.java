package com.packt.learnjava.spring.service;

import com.packt.learnjava.spring.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component
public class PersonService {
    @Autowired
    DataSource ds;

    public Person newPerson(Person person){
        try(Connection conn = getConnection()){
            int id = Person.insert(conn, person);
            person.setId(id);
            return person;
        } catch (SQLException ex){
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<Person> getAllPersons(){
        try(Connection conn = getConnection()){
            return Person.selectAll(conn);
        } catch (SQLException ex){
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    public Person getPersonById(int id){
        try(Connection conn = getConnection()){
            return Person.selectById(conn, id);
        } catch (SQLException ex){
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    public Person updatePerson(Person existingPerson, Person newPerson){
        try(Connection conn = getConnection()){
            existingPerson.prepareForUpdate(newPerson);
            Person.updateById(conn, existingPerson);
            return existingPerson;
        } catch (SQLException ex){
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void deletePerson(int id){
        try(Connection conn = getConnection()){
            Person.deleteById(getConnection(), id);
        } catch (SQLException ex){
            ex.printStackTrace();
            throw new RuntimeException();
        }
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
