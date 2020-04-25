package edu.utn.mail.dao.factory;

import edu.utn.mail.dao.MessageDao;
import edu.utn.mail.dao.UserDao;

import java.sql.SQLException;
import java.util.Properties;

public abstract class AbstractDaoFactory {

    Properties config;

    public AbstractDaoFactory(Properties config) {
        this.config = config;
    }
    public abstract UserDao getUserDao() ;
    public abstract MessageDao getMessageDao();
}
