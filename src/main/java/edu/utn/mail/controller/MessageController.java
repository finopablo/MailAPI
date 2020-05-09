package edu.utn.mail.controller;

import edu.utn.mail.domain.Message;
import edu.utn.mail.domain.User;
import edu.utn.mail.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MessageController {

    MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    public List<Message> getMessages(Integer userId) {
            return messageService.getMessagesByUser(userId);
        }

}
