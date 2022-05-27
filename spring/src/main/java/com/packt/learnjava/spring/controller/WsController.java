package com.packt.learnjava.spring.controller;

import com.packt.learnjava.spring.model.Person;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class WsController {
    @GetMapping("/list")
    public List<Person> list() {
        Person mike = new Person(1, "Mike", "Brown", LocalDate.of(2002, 8, 14));
        Person jane = new Person(2, "Jane", "McDonald", LocalDate.of(2000, 3, 21));
        Person jill = new Person(3, "Jill", "Grey", LocalDate.of(2001, 4, 1));
        return List.of(mike, jane, jill);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> update(@RequestBody Person person){
        return ResponseEntity.ok().body(person);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        Person person = new Person(id, "Mike", "Brown", LocalDate.of(2002, 8, 14));
        return ResponseEntity.ok().body(person + " successfully deleted.");
    }

}
