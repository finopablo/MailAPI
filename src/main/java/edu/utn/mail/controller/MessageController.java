package edu.utn.mail.controller;

import edu.utn.mail.domain.Message;
import edu.utn.mail.domain.User;
import edu.utn.mail.service.MessageService;

import java.util.List;


public class MessageController {

        MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    public List<Message> getMessages(Integer userId) {
            return messageService.getMessagesByUser(userId);
        }

}
