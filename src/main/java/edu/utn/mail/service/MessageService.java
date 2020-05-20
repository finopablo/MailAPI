package edu.utn.mail.service;

import edu.utn.mail.dao.MessageDao;
import edu.utn.mail.domain.Message;
import edu.utn.mail.domain.User;
import edu.utn.mail.exceptions.RecordExistsException;
import edu.utn.mail.exceptions.UserNotexistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
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

    public List<Message> getMessagesByUser(User user) {
        return messageDao.getByUser(user);
    }

    public List<Message> getMessagesByUserFilterByDate(User user, Date from, Date to) {
        return messageDao.getByUser(user, LocalDateTime.ofInstant(from.toInstant(),
                ZoneId.systemDefault()), LocalDateTime.ofInstant(to.toInstant(),
                ZoneId.systemDefault()));
    }

    public Message newMessage(Message message) throws UserNotexistException {
        User userTo = userService.getUser(message.getTo().getUserId());
        return messageDao.save(message);
    }
    public Message getMessageById(Integer messageId) {
        return messageDao.findById(messageId).orElse(null);
    }
}
