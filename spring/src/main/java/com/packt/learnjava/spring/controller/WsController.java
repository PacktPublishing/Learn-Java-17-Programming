package com.packt.learnjava.spring.controller;

import com.packt.learnjava.spring.model.Person;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ws")
public class WsController {
    @GetMapping("/list")
    private List<Person> list() {
        //Read all Person records from the database
        Person mike = new Person(1, "Mike", "Brown", LocalDate.of(2002, 8, 14));
        Person jane = new Person(2, "Jane", "McDonald", LocalDate.of(2000, 3, 21));
        Person jill = new Person(3, "Jill", "Grey", LocalDate.of(2001, 4, 1));
        return List.of(mike, jane, jill);
    }

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> newPerson(@RequestBody Person person){
        //Insert new Person record in the database
        person.setId(42);
        return ResponseEntity.ok().body(person + " successfully created.");
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> update(@RequestBody Person person){
        if(person.getId() == 0){
            return ResponseEntity.badRequest().body("The id value is required.");
        } else {
            //Update the Person record (by id) in the database
            return ResponseEntity.ok().body(person + " successfully updated.");
        }
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<String> delete(@PathVariable int id){
        //Delete the Person record (by id) in the database
        Person person = new Person(id, "Mike", "Brown", LocalDate.of(2002, 8, 14));
        return ResponseEntity.ok().body(person + " successfully deleted.");
    }

}
