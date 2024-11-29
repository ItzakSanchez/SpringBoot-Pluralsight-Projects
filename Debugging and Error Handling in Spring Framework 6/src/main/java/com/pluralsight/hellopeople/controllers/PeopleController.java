package com.pluralsight.hellopeople.controllers;

import com.pluralsight.hellopeople.models.Person;
import com.pluralsight.hellopeople.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PeopleController {

    @Autowired private PeopleService service;

    @GetMapping("/people")
    public List<Person> getAll() {
        return service.getAll();
    }

    @GetMapping("/people/{id}")
    public Person get(@PathVariable int id) {
        return service.get(id);
    }

    @PostMapping("/people")
    public int create(@RequestBody Person person) {
        return service.create(person);
    }

    @PutMapping("/people/{id}")
    public boolean update(@PathVariable int id, @RequestBody Person person) {
        if (id != person.getId()) {
            return false;
        }
        return service.update(person);
    }

    @DeleteMapping("/people/{id}")
    public boolean delete(@PathVariable int id, @RequestBody Person person) {
        if (id != person.getId()) {
            return false;
        }
        return service.delete(person);
    }
}
