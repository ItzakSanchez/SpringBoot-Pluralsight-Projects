package com.pluralsight.hellopeople.repositories;

import java.util.List;

public interface DataRepository<T> {
    List<T> getAll();
    T get(int index);
    int create(T item);
    boolean update(T item);
    boolean delete(T item);
}
