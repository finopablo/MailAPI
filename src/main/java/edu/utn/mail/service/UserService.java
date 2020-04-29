package edu.utn.mail.service;

import edu.utn.mail.dao.UserDao;
import edu.utn.mail.domain.User;
import edu.utn.mail.exceptions.UserAlreadyExistsException;
import edu.utn.mail.exceptions.UserNotexistException;

import java.util.Optional;

public class UserService {

    UserDao dao;

    public UserService(UserDao dao) {
        this.dao = dao;
    }

    public User login(String username, String password) throws UserNotexistException {
        User user = dao.getByUsername(username, password);
        return Optional.ofNullable(user).orElseThrow(() -> new UserNotexistException());
    }

    public User createUser(User user) throws UserAlreadyExistsException {
        return dao.add(user);
    }

    
    public void removeUser(User user) throws UserNotexistException {
        if (dao.remove(user) == 0) {
            throw new UserNotexistException();
        }
    }

    public User updateUser(User user) throws UserNotexistException {
        if (dao.update(user) > 0) {
            return user;
        } else {
            throw new UserNotexistException();
        }
    }
}
