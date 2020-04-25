package edu.utn.mail.dao.factory;

import edu.utn.mail.dao.MessageDao;
import edu.utn.mail.dao.UserDao;
import edu.utn.mail.dao.memory.UserMemoryDao;

import java.util.Properties;

public class MemoryDaoFactory extends AbstractDaoFactory {

    public MemoryDaoFactory(Properties config) {
        super(config);
    }

    public UserDao getUserDao() {
        return new UserMemoryDao();
    }

    @Override
    public MessageDao getMessageDao() {
        throw new UnsupportedOperationException();
    }
}
