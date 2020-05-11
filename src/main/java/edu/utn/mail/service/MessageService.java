package edu.utn.mail.service;

import edu.utn.mail.dao.MessageDao;
import edu.utn.mail.domain.Message;
import edu.utn.mail.domain.User;
import edu.utn.mail.exceptions.RecordExistsException;
import edu.utn.mail.exceptions.UserNotexistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    MessageDao messageDao;

    UserService userService;

    @Autowired
    public MessageService(MessageDao messageDao, UserService userService) {
        this.messageDao = messageDao;
        this.userService = userService;
    }

    public List<Message> getMessagesByUser(Integer userId) {
        return messageDao.getByUser(userId);
    }

    public List<Message> getMessagesByUserFilterByDate(Integer userId, Date from, Date to) {
        return messageDao.getbyUserAndDates(userId, from, to);
    }

    public Message newMessage(Message message) throws UserNotexistException {
        try {
            User userTo =  userService.getUser(message.getTo().getUserId());
            return messageDao.add(message);
        } catch (RecordExistsException e) {
            //Is not possible to have this situation
        }
        return null;
    }
}
