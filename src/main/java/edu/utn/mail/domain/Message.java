package edu.utn.mail.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Message {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_message")
    long messageId;

    @ManyToOne
    @JoinColumn(name = "id_from")
    User from;
    @ManyToOne
    @JoinColumn(name = "id_to")
    User to;
    String subject;
    String body;
    LocalDateTime messageDate;


    public Message() {

    }

    public Message(long messageId, User from, User to, String subject, String body, LocalDateTime messageDate) {
        this.messageId = messageId;
        this.from = from;
        this.to = to;
        this.subject = subject;
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

    public String getSubject() {
        return subject;
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

    public void setSubject(String message) {
        this.subject = message;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setMessageDate(LocalDateTime messageDate) {
        this.messageDate = messageDate;
    }
}
