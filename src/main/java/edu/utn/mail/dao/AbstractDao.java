package edu.utn.mail.dao;

import edu.utn.mail.exceptions.RecordExistsException;

import java.util.List;

public interface AbstractDao<T> {

    T add(T value) throws RecordExistsException;

    Integer update(T value);

    Integer remove(Integer id);

    Integer remove(T value);

    T getById(Integer id);

    List<T> getAll();
}
