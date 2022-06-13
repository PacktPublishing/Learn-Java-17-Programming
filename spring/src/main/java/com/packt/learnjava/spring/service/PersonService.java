package com.packt.learnjava.spring.service;

import com.packt.learnjava.spring.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
    @Autowired
    private CacheManager cacheManager;

    @Scheduled(fixedRateString = "${cache.refresh.period.ms}",
            initialDelayString = "${cache.initial.period.ms}")
    public void loadCache() {
        //cacheManager.getCache("All persons").put(SimpleKey.EMPTY, getAllPersons());
    }

    @Cacheable(value="All persons", sync=true)
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

    //@Scheduled(fixedRate = 5000)
    private void doSomething(){
        System.out.println("Doing something at " + LocalDateTime.now());
    }
}
