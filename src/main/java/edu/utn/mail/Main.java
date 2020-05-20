package edu.utn.mail;

import edu.utn.mail.controller.MessageController;
import edu.utn.mail.controller.UserController;
import edu.utn.mail.dao.MessageDao;
import edu.utn.mail.dao.UserDao;
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
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class Main {


}
