package edu.utn.mail.dao;

import java.util.List;

public interface AbstractDao<T> {

    T add(T value);

    T update(T value);

    void remove(Integer id);

    void remove(T value);

    T getById(Integer id);

    List<T> getAll();
}
