package edu.utn.mail.dao.factory;

import edu.utn.mail.dao.UserDao;
import edu.utn.mail.dao.mysql.UserMySQLDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLDaoFactory extends AbstractDaoFactory {

    static UserMySQLDao userMySQLDao;

    public MySQLDaoFactory(Properties config) {
        super(config);
    }

    public UserDao getUserDao() {
        try {
            if (userMySQLDao == null) {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                String username = config.getProperty("db.user");
                String password = config.getProperty("db.password");
                String db = config.getProperty("db.name");

                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/"+db+"?user="+username+"&password="+ password+"");
                userMySQLDao = new UserMySQLDao(conn);
            }
            return userMySQLDao;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
