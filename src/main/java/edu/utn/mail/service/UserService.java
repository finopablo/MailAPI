package edu.utn.mail.service;

import edu.utn.mail.dao.UserDao;
import edu.utn.mail.domain.User;
import edu.utn.mail.exceptions.UserAlreadyExistsException;
import edu.utn.mail.exceptions.UserNotexistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    UserDao dao;

    @Autowired
    public UserService(UserDao dao) {
        this.dao = dao;
    }

    public User login(String username, String password) throws UserNotexistException {
        User user = dao.getByUsername(username, password);
        return Optional.ofNullable(user).orElseThrow(() -> new UserNotexistException());
    }

    public User createUser(User user) throws UserAlreadyExistsException {
        return dao.save(user);
    }


    public void removeUser(User user) throws UserNotexistException {
        dao.delete(user);
    }

    public User updateUser(User user) throws UserNotexistException {
     return dao.save(user);
    }

    public User getUser(Integer userId){
        return dao.getOne(userId);
    }

}
