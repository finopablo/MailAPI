package edu.utn.mail.dao;

import edu.utn.mail.domain.City;
import edu.utn.mail.domain.User;
import edu.utn.mail.exceptions.UserAlreadyExistsException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    @Query(value = "SELECT u FROM User u WHERE u.username = :username and u.password = :pwd")
    public User getByUsername(@Param("username") String username, @Param("pwd") String password);
}
