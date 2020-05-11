package edu.utn.mail.controller.web;


import edu.utn.mail.controller.MessageController;
import edu.utn.mail.domain.Message;
import edu.utn.mail.domain.User;
import edu.utn.mail.exceptions.UserNotexistException;
import edu.utn.mail.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageWebController {

    private final MessageController messageController;
    private final SessionManager sessionManager;

    @Autowired
    public MessageWebController(MessageController messageController, SessionManager sessionManager) {
        this.messageController = messageController;
        this.sessionManager = sessionManager;
    }

    @GetMapping
    public ResponseEntity<List<Message>> getMessages(@RequestHeader("Authorization") String sessionToken) {
        User currentUser = sessionManager.getCurrentUser(sessionToken);
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        List<Message> messages = messageController.getMessages(currentUser.getUserId());
        return (messages.size() > 0) ? ResponseEntity.ok(messages) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/add")
    public ResponseEntity newMessage(@RequestHeader("Authorization") String sessionToken, @RequestBody Message message ) throws UserNotexistException {
        User currentUser = sessionManager.getCurrentUser(sessionToken);
        message.setFrom(currentUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(messageController.newMessage(message));

    }
}
