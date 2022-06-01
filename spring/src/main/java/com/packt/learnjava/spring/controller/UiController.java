package com.packt.learnjava.spring.controller;

import com.packt.learnjava.spring.model.Person;
import com.packt.learnjava.spring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/ui")
public class UiController{
    @Autowired
    PersonService personService;

    @GetMapping(value = {"", "/"})
    private String home(Model model){
        return "home";
    }

    @GetMapping("/list")
    private String list(Model model){
        //Read all Person records from the database
        Person mike = new Person(1, "Mike", "Brown", LocalDate.of(2002, 8, 14));
        Person jane = new Person(2, "Jane", "McDonald", LocalDate.of(2000, 3, 21));
        Person jill = new Person(3, "Jill", "Grey", LocalDate.of(2001, 4, 1));
        model.addAttribute("persons", List.of(mike, jane, jill));
        return "list";
    }

    @GetMapping("/delete/{id}")
    private String delete(Model model, @PathVariable int id){
        //Delete the Person record (by id) in the database
        Person person = new Person(id, "Mike", "Brown", LocalDate.of(2002, 8, 14));
        model.addAttribute("message", person + " successfully deleted");
        return list(model);
    }

    @GetMapping("/new")
    private String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "person";
    }

    @GetMapping("/edit/{id}")
    private String update(Model model, @PathVariable int id){
        //Read the Person record (by id) from the database
        Person person = new Person(id, "Mike", "Brown", LocalDate.of(2002, 8, 14));
        model.addAttribute("person", person);
        return "person";
    }

    @PostMapping("/save")
    private String save(Model model, @ModelAttribute Person person){
        if(person.getId() == 0){
            //Store new Person record (by id) in the database
            personService.insert(person);
            model.addAttribute("message", "New " + person + " successfully created");
        } else {
            //Update the Person record (by id) in the database
            model.addAttribute("message", person + " successfully updated");
        }
        return list(model);
    }
}
