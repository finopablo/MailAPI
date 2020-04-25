package edu.utn.mail.dao;

import edu.utn.mail.domain.City;
import edu.utn.mail.domain.User;
import edu.utn.mail.exceptions.UserAlreadyExistsException;

import java.util.List;

public interface UserDao extends AbstractDao<User> {
    User getByUsername(String username, String password);
    List<User> getByCity(City city);
    User add(User u) throws UserAlreadyExistsException;
    Integer update(User u);
}
