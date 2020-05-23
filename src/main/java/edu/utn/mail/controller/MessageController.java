package edu.utn.mail.controller;

import edu.utn.mail.domain.Message;
import edu.utn.mail.domain.User;
import edu.utn.mail.exceptions.UserNotexistException;
import edu.utn.mail.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class MessageController {

    MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    public List<Message> getMessages(User user) {
        return messageService.getMessagesByUser(user);
    }

    public List<Message> getMessagesByDate(User user, Date from, Date to) {
        return messageService.getMessagesByUserFilterByDate(user, from, to);
    }


    public Message newMessage(Message message) throws UserNotexistException {
        return messageService.newMessage(message);
    }

    public Message getMessage(Long messageId) {
        return messageService.getMessageById(messageId);
    }
}
