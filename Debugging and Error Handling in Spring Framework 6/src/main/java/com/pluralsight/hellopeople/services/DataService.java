package com.pluralsight.hellopeople.services;

import java.util.List;

public interface DataService<T> {
    List<T> getAll();
    T get(int index);
    int create(T item);
    boolean update(T item);
    boolean delete(T item);
}
