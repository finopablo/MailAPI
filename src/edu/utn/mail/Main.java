package edu.utn.mail;

import edu.utn.mail.controller.UserController;
import edu.utn.mail.dao.UserDao;
import edu.utn.mail.dao.mysql.UserMySQLDao;
import edu.utn.mail.domain.City;
import edu.utn.mail.domain.Country;
import edu.utn.mail.domain.User;
import edu.utn.mail.exceptions.UserAlreadyExistsException;
import edu.utn.mail.exceptions.UserNotexistException;
import edu.utn.mail.exceptions.ValidationException;
import edu.utn.mail.service.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        // 1) Connection
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mail?user=root&password=a");

        UserDao userDao = new UserMySQLDao(conn);
        UserService userService = new UserService(userDao);
        UserController userController = new UserController(userService);
        User u = new User("jose", "juanperez", "1234", "perez", new City(1, "Buenos Aires", new Country(1, "Argentina")));
        try {
            u = userController.createUser(u);
            System.out.println(u);
            User u2 = userController.login("assass", "asss");
        } catch (UserNotexistException userNotExist) {
            userNotExist.printStackTrace();
        }catch(UserAlreadyExistsException userExists) {
            userExists.printStackTrace();
        } catch(ValidationException validationException) {
            validationException.printStackTrace();;
        } finally {
            conn.close();
        }

    }
}
