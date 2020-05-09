package edu.utn.mail.service;

import edu.utn.mail.dao.MessageDao;
import edu.utn.mail.domain.Message;
import edu.utn.mail.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    MessageDao messageDao;

    @Autowired
    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public List<Message> getMessagesByUser(Integer userId) {
        return messageDao.getByUser(userId);
    }

    public List<Message> getMessagesByUserFilterByDate(Integer userId, Date from, Date to) {
        return messageDao.getbyUserAndDates(userId, from, to);
    }

}
