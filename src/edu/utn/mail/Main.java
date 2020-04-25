package edu.utn.mail;

import edu.utn.mail.controller.MessageController;
import edu.utn.mail.controller.UserController;
import edu.utn.mail.dao.AbstractDao;
import edu.utn.mail.dao.MessageDao;
import edu.utn.mail.dao.UserDao;
import edu.utn.mail.dao.factory.AbstractDaoFactory;
import edu.utn.mail.dao.factory.MySQLDaoFactory;
import edu.utn.mail.domain.City;
import edu.utn.mail.domain.Country;
import edu.utn.mail.domain.Message;
import edu.utn.mail.domain.User;
import edu.utn.mail.exceptions.UserAlreadyExistsException;
import edu.utn.mail.exceptions.UserNotexistException;
import edu.utn.mail.exceptions.ValidationException;
import edu.utn.mail.service.MessageService;
import edu.utn.mail.service.UserService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // 1) Connection

        Properties config = new Properties();
        config.load(new FileInputStream("./conf/app.properties"));
        AbstractDaoFactory daoFactory =  (AbstractDaoFactory) Class.forName(config.getProperty("db.dao.factory")).getDeclaredConstructor(Properties.class).newInstance(config);


        MessageDao messageDao = daoFactory.getMessageDao();
        MessageService messageService = new MessageService(messageDao);
        MessageController messageController = new MessageController(messageService);


        List<Message> messageList = messageController.getMessages(1);


        UserDao userDao = daoFactory.getUserDao();
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
        }
    }
}
