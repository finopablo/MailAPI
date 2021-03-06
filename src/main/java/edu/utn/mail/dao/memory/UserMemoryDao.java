package edu.utn.mail.dao.memory;

import edu.utn.mail.dao.UserDao;
import edu.utn.mail.domain.City;
import edu.utn.mail.domain.User;
import edu.utn.mail.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Repository
@Qualifier("userMemoryDao")
public class UserMemoryDao implements UserDao {


    static int count = 0;
    List<User> userList;


    public UserMemoryDao() {
        userList = new ArrayList<>();
    }

    @Autowired
    public UserMemoryDao(@Qualifier("defaultUserList") List<User> userList) {
        this.userList = userList;
    }

    @Override
    public User getByUsername(String username, String password) {
        return userList.stream()
                .filter(user -> (username.equals(user.getUsername())) && password.equals(user.getPassword()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getByCity(City city) {
        return userList.stream()
                .filter(user -> user.getCity().equals(city))
                .collect(Collectors.toList());
    }

    @Override
    public User add(User u) throws UserAlreadyExistsException {
        u.setUserId(count++);
        userList.add(u);
        return u;
    }

    @Override
    public Integer update(User value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer remove(Integer id) {
        return 0;
    }

    @Override
    public Integer remove(User value) {
        return 0;
    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
