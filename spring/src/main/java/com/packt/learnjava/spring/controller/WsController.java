package com.packt.learnjava.spring.controller;

import com.packt.learnjava.spring.model.Person;
import com.packt.learnjava.spring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ws")
public class WsController {
    @Autowired
    PersonService personService;

    @GetMapping("/list")
    private ResponseEntity<List<Person>> list() {
        try {
            return ResponseEntity.ok().body(personService.getAllPersons());
        } catch (Exception ex){
            return ResponseEntity.internalServerError().body(new ArrayList<>());
        }
    }

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> newPerson(@RequestBody Person person){
        String error = person.validateForInsert();
        if(!"".equals(error)){
            return ResponseEntity.badRequest().body(error);
        }
        try {
            Person newPerson = personService.newPerson(person);
            return ResponseEntity.ok().body("New " + newPerson + " successfully created");
        } catch (Exception ex){
            return ResponseEntity.internalServerError().body("");
        }
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> update(@RequestBody Person person){
        try {
            if(person.getId() == 0){
                return ResponseEntity.badRequest().body("The id value is required.");
            } else {
                Person existingPerson = personService.getPersonById(person.getId());
                if(existingPerson.getId() > 0){
                    personService.updatePerson(existingPerson, person);
                    return ResponseEntity.ok().body(person + " successfully updated.");
                } else {
                    return ResponseEntity.badRequest().body("Person record with id=" + person.getId() + " not found.");
                }
            }
        } catch (Exception ex){
            return ResponseEntity.internalServerError().body("");
        }
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<String> delete(@PathVariable int id){
        try {
            if(id <= 0){
                return ResponseEntity.badRequest().body("The id value has to be positive integer");
            } else {
                Person existingPerson = personService.getPersonById(id);
                if(existingPerson.getId() > 0){
                    personService.deletePerson(id);
                    return ResponseEntity.ok().body(existingPerson + " successfully deleted.");
                } else {
                    return ResponseEntity.badRequest().body("Person record with id=" + id + " not found.");
                }
            }
        } catch (Exception ex){
            return ResponseEntity.internalServerError().body("");
        }
    }

    @GetMapping("/get/{id}")
    private ResponseEntity<String> get(@PathVariable int id){
        try {
            if(id <= 0){
                return ResponseEntity.badRequest().body("The id value has to be positive integer");
            } else {
                Person person = personService.getPersonByIdCB(id);
                if(person.getId() > 0){
                    return ResponseEntity.ok().body(person.toString());
                } else {
                    return ResponseEntity.badRequest().body("Person record with id=" + id + " not found.");
                }
            }
        } catch (Exception ex){
            return ResponseEntity.internalServerError().body("");
        }
    }
}
