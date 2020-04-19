package edu.utn.mail.dao.mysql;

import com.mysql.cj.exceptions.MysqlErrorNumbers;
import edu.utn.mail.exceptions.UserAlreadyExistsException;

import java.util.HashMap;
import java.util.Map;

public class MySQLUtils {

    private static String BASE_USER_QUERY = "select * from users u inner join cities c inner join countries co on co.id_country = c.id_country on u.id_city = c.id_city";
    protected static String GET_BY_USERNAME_USER_QUERY = BASE_USER_QUERY +"  where username = ? and pwd=?";
    protected static final String INSERT_USER_QUERY = "insert into users(name,surname,username,pwd,id_city) values (?,?,?,?,?);";

}
