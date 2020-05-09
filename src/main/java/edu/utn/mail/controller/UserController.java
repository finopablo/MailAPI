package edu.utn.mail.controller;

import edu.utn.mail.domain.User;
import edu.utn.mail.exceptions.UserAlreadyExistsException;
import edu.utn.mail.exceptions.UserNotexistException;
import edu.utn.mail.exceptions.ValidationException;
import edu.utn.mail.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserController {
    UserService userService;

    static Logger logger = LogManager.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User login(String username, String password) throws UserNotexistException, ValidationException {
        if ((username != null) && (password != null)) {
            logger.info("Getting user for {}" , username);
            return userService.login(username, password);
        } else {
            throw new ValidationException();
        }
    }


    public User getUserById(Integer userId) {
        return userService.getUser(userId);
    }


    public User createUser(User user) throws UserAlreadyExistsException {
        return userService.createUser(user);
    }

    public void removeUser(User user) throws UserNotexistException {
        userService.removeUser(user);
    }

    public void removeUsers(List<User> userList) throws UserNotexistException {
        for (User u : userList) {
            userService.removeUser(u);
        }
    }



    public void updateUser(User user) throws UserNotexistException {
        userService.updateUser(user);
    }
}
