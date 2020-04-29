package edu.utn.mail.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Message {
    long messageId;
    User from;
    User to;
    String message;
    String body;
    LocalDateTime messageDate;


    public Message(long messageId, User from, User to, String message, String body, LocalDateTime messageDate) {
        this.messageId = messageId;
        this.from = from;
        this.to = to;
        this.message = message;
        this.body = body;
        this.messageDate = messageDate;
    }

    public long getMessageId() {
        return messageId;
    }

    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getMessageDate() {
        return messageDate;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setMessageDate(LocalDateTime messageDate) {
        this.messageDate = messageDate;
    }
}
