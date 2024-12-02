package com.pluralsight.hellopeople.services;

import com.pluralsight.hellopeople.models.Person;
import com.pluralsight.hellopeople.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleService implements DataService<Person> {
    private @Autowired PeopleRepository repository;

    @Override
    public List<Person> getAll() {
        return repository.getAll();
    }

    @Override
    public Person get(int index) {
        return repository.get(index);
    }

    @Override
    public int create(Person item) {
        return repository.create(item);
    }

    @Override
    public boolean update(Person item) {
        return repository.update(item);
    }

    @Override
    public boolean delete(Person item) {
        return repository.delete(item);
    }
}
