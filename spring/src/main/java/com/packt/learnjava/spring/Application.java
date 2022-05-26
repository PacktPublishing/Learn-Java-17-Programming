package com.packt.learnjava.spring;

import com.packt.learnjava.database.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping("/list")
	public List<Person> list() {
		Person mike = new Person("Mike", "Brown", LocalDate.of(2002, 8, 14));
		Person jane = new Person("Jane", "McDonald", LocalDate.of(2000, 3, 21));
		Person jill = new Person("Jill", "Grey", LocalDate.of(2001, 4, 1));
        List<Person> list = new ArrayList<>();
		list.add(mike);
		list.add(jane);
		list.add(jill);
		return list;
	}
}


