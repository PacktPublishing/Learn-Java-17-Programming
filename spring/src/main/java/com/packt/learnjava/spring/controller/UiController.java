package com.packt.learnjava.spring.controller;

import com.packt.learnjava.spring.model.Person;
import com.packt.learnjava.spring.service.PersonService;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Controller
@RequestMapping("/ui")
public class UiController{
    private static Logger logger = LoggerFactory.getLogger(UiController.class);
    private static String INTERNAL_ERROR = "Unexpected condition. Please, contact the application administrator or try again later";

    @Value("${server.port}")
    private String port;

    @Autowired
    PersonService personService;

    @GetMapping(value = {"", "/"})
    private String home(Model model) {
        addActuatorKey(model, "health");
        addActuatorKey(model, "info");
        logger.info("Show home page!");
        return "home";
    }

    private void addActuatorKey(Model model, String key) {
        String resp = new RestTemplate().getForObject("http://localhost:" + port + "/actuator/" + key, String.class);
        JSONParser parser = new JSONParser();
        try {
            model.addAttribute(key, parser.parse(resp));
        } catch (ParseException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    @GetMapping("/list")
    private String list(Model model){
        try {
            model.addAttribute("persons", personService.getAllPersons());
        } catch (Exception ex){
            model.addAttribute("persons", new ArrayList<>());
            model.addAttribute("message", INTERNAL_ERROR);
        }
        return "list";
    }

    @GetMapping("/delete/{id}")
    private String delete(Model model, @PathVariable int id){
        try {
            Person existingPerson = personService.getPersonById(id);
            if(existingPerson.getId() > 0){
                personService.deletePerson(id);
                model.addAttribute("message", existingPerson + " successfully deleted");
            } else {
                model.addAttribute("message", "Person record is not found");
            }
        } catch (Exception ex){
            model.addAttribute("message", INTERNAL_ERROR);
        }
        return list(model);
    }

    @GetMapping("/new")
    private String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "person";
    }

    @GetMapping("/edit/{id}")
    private String update(Model model, @PathVariable int id){
        try {
            Person existingPerson = personService.getPersonById(id);
            if(existingPerson.getId() > 0){
                model.addAttribute("person", existingPerson);
            } else {
                model.addAttribute("message", "Person record is not found");
            }
        } catch (Exception ex){
            model.addAttribute("message", INTERNAL_ERROR);
        }
        return "person";
    }

    @PostMapping("/save")
    private String save(Model model, @ModelAttribute Person person){
        try {
            if(person.getId() == 0){
                String error = person.validateForInsert();
                if("".equals(error)){
                    Person newPerson = personService.newPerson(person);
                    model.addAttribute("message", "New " + newPerson + " successfully created");
                } else {
                    model.addAttribute("message", error);
                }
            } else {
                Person existingPerson = personService.getPersonById(person.getId());
                if(existingPerson.getId() > 0){
                    personService.updatePerson(existingPerson, person);
                    model.addAttribute("message", person + " successfully updated");
                } else {
                    model.addAttribute("message", person + " record not found.");
                }
            }
        } catch (Exception ex){
            model.addAttribute("message", INTERNAL_ERROR);
        }
        return list(model);
    }
}
