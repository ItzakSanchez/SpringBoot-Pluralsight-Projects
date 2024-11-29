package com.pluralsight.hellopeople.repositories;

import com.pluralsight.hellopeople.models.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PeopleRepository implements DataRepository<Person> {
    private final List<Person> people;

    public PeopleRepository() {
        final String[] names = { "John Doe", "Jane Doe", "Johanna Doe", "Dane Joe", "Dohn Joe" };
        this.people = new ArrayList<>();

        // Add dummy data
        for (int i = 0; i < 5; i++) {
            Person p = new Person();
            p.setId(i);
            p.setAge(25 + i);
            p.setName(names[i % names.length]);
            this.people.add(p);
        }
    }

    @Override
    public List<Person> getAll() {
        return people;
    }

    @Override
    public Person get(int index) {
        return people.get(index);
    }

    @Override
    public int create(Person item) {
        people.add(item);
        return people.size() - 1;
    }

    @Override
    public boolean update(Person item) {
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getId() == item.getId()) {
                people.remove(i);
                people.add(i, item);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Person item) {
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getId() == item.getId()) {
                people.remove(i);
                return true;
            }
        }

        // Could not find the element.
        return false;
    }
}
