package edu.utn.mail.service;

import edu.utn.mail.dao.MessageDao;
import edu.utn.mail.domain.Message;
import edu.utn.mail.domain.User;
import edu.utn.mail.exceptions.UserNotexistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Instant;
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

    @Cacheable(value = "messagesByUser", key = "#user.userId")
    public List<Message> getMessagesByUser(User user) {
        return messageDao.getByUser(user);
    }

    @Cacheable(value = "messagesByDate", key = "{#user.userId, #from.toString(), #to.toString()}")
    public List<Message> getMessagesByUserFilterByDate(User user, Date from, Date to) {
        return messageDao.getByUser(user, LocalDateTime.ofInstant(from.toInstant(),
                ZoneId.systemDefault()), LocalDateTime.ofInstant(to.toInstant(),
                ZoneId.systemDefault()));
    }
    
    @CacheEvict(value={"messagesByUser", "messagesByDate"} , key="#message.to.userId")
    public Message newMessage(Message message) throws UserNotexistException {
        User userTo = userService.getUser(message.getTo().getUserId());
        message.setMessageDate(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        Message savedMessage =  messageDao.save(message);
        return savedMessage;

    }
    @Cacheable(value = "messages", key = "#messageId")
    public Message getMessageById(Long messageId) {
        return messageDao.findById(messageId).orElse(null);
    }
}
