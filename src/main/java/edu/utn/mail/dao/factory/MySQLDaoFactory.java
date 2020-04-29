package edu.utn.mail.dao.factory;

import edu.utn.mail.dao.MessageDao;
import edu.utn.mail.dao.UserDao;
import edu.utn.mail.dao.mysql.MessageMySQLDao;
import edu.utn.mail.dao.mysql.UserMySQLDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLDaoFactory extends AbstractDaoFactory {



    static Connection connection;


    static UserMySQLDao userMySQLDao;
    static MessageMySQLDao messageMySQLDao;

    public MySQLDaoFactory(Properties config) {
        super(config);
    }


    private  Connection getConnection() {

        try {
            if (connection == null) {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                String username = config.getProperty("db.user");
                String password = config.getProperty("db.password");
                String db = config.getProperty("db.name");
                connection = DriverManager.getConnection("jdbc:mysql://localhost/" + db + "?user=" + username + "&password=" + password + "");
            }
            return connection;
        } catch(Exception e) {
            throw new RuntimeException("Imposible conectar a la base de datos MYSQL", e);
        }
    }

    public UserDao getUserDao() {
            if (userMySQLDao == null) {
                userMySQLDao = new UserMySQLDao(getConnection());
            }
            return userMySQLDao;
    }

    public MessageDao getMessageDao() {
        if (messageMySQLDao == null) {
            messageMySQLDao = new MessageMySQLDao(getConnection(), getUserDao());
        }
        return messageMySQLDao;
    }

}
