package edu.utn.mail.dao;

import edu.utn.mail.domain.City;
import edu.utn.mail.domain.User;

import java.util.List;

public interface UserDao extends AbstractDao<User> {
    User getByUsername(String username, String password);
    List<User> getByCity(City city);
}
