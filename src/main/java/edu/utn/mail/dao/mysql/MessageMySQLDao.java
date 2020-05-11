package edu.utn.mail.dao.mysql;

import edu.utn.mail.dao.MessageDao;
import edu.utn.mail.dao.UserDao;
import edu.utn.mail.domain.Message;
import edu.utn.mail.exceptions.RecordExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class MessageMySQLDao implements MessageDao {

    Connection connection;

    UserDao userDao;

    @Autowired
    public MessageMySQLDao(Connection connection, @Qualifier("userMySqlDao") UserDao userDao) throws SQLException {
        this.connection = connection;
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
    public Message add(Message value) {
        try {
            PreparedStatement ps = connection.prepareStatement(MySQLUtils.ADD_MESSAGE_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, value.getFrom().getUserId());
            ps.setInt(2, value.getTo().getUserId());
            ps.setString(3, value.getSubject());
            ps.setString(4, value.getBody());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                value.setMessageId(rs.getInt(1));
            }
            return value;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
