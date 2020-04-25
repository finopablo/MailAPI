package edu.utn.mail.dao.mysql;

import edu.utn.mail.dao.MessageDao;
import edu.utn.mail.dao.UserDao;
import edu.utn.mail.domain.Message;
import edu.utn.mail.exceptions.RecordExistsException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageMySQLDao implements MessageDao {

    Connection connection;

    UserDao userDao;

    public MessageMySQLDao(Connection con, UserDao userDao) {
        this.connection = con;
        this.userDao = userDao;
    }


    @Override
    public List<Message> getByUser(Integer userId) {

        try {
            PreparedStatement ps = connection.prepareStatement(MySQLUtils.GET_MESSAGES_BY_USER);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            List<Message> messageList = new ArrayList<>();
            while (rs.next()) {
                messageList.add(createMessage(rs));
            }
            return messageList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Message createMessage(ResultSet rs) throws SQLException {
        return new Message(rs.getInt("id_message"), userDao.getById(rs.getInt("id_from")), userDao.getById(rs.getInt("id_to"))
                , rs.getString("subject"), rs.getString("body"), rs.getObject("message_date", LocalDateTime.class));
    }

    @Override
    public List<Message> getbyUserAndDates(Integer userId, Date from, Date to) {
        return null;
    }

    @Override
    public Message add(Message value) throws RecordExistsException {
        return null;
    }

    @Override
    public Integer update(Message value) {
        return null;
    }

    @Override
    public Integer remove(Integer id) {
        return null;
    }

    @Override
    public Integer remove(Message value) {
        return null;
    }

    @Override
    public Message getById(Integer id) {
        return null;
    }

    @Override
    public List<Message> getAll() {
        return null;
    }
}
