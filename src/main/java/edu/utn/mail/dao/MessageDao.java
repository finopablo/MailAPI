package edu.utn.mail.dao;

import edu.utn.mail.domain.Message;

import java.util.Date;
import java.util.List;

public interface MessageDao extends AbstractDao<Message> {
    List<Message> getByUser(Integer userId);

    List<Message> getbyUserAndDates(Integer userId, Date from, Date to);
}
