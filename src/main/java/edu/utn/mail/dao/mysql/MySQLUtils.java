package edu.utn.mail.dao.mysql;

import com.mysql.cj.exceptions.MysqlErrorNumbers;
import edu.utn.mail.exceptions.UserAlreadyExistsException;

import java.util.HashMap;
import java.util.Map;

public class MySQLUtils {

    protected static String BASE_USER_QUERY = "select * from users u inner join cities c inner join countries co on co.id_country = c.id_country on u.id_city = c.id_city ";
    protected static String GET_BY_ID_USER_QUERY = BASE_USER_QUERY + " where u.id_user = ?";
    protected static String GET_BY_USERNAME_USER_QUERY = BASE_USER_QUERY + "  where username = ? and pwd=?";
    protected static final String INSERT_USER_QUERY = "insert into users(name,surname,username,pwd,id_city) values (?,?,?,?,?);";
    protected static final String REMOVE_USER_QUERY = "delete from user where id_user = ?";
    protected static final String UPDATE_USER_QUERY = "update users set name=?, surname=?, id_city=?, pwd=?  where id_user =? ";

    protected static final String BASE_MESSAGE_QUERY = "select * from messages";


    protected static final String GET_MESSAGES_BY_USER = BASE_MESSAGE_QUERY + " where id_to = ?";
}
