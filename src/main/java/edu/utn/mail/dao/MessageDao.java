package edu.utn.mail.dao;

import edu.utn.mail.domain.Message;
import edu.utn.mail.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface MessageDao extends JpaRepository<Message, Long> {


    @Query(value = "SELECT m FROM Message m WHERE m.to = :user")
    List<Message> getByUser(@Param("user") User user);
    @Query(value = "SELECT m FROM Message m WHERE m.to = :user and m.messageDate between :from and :to", nativeQuery = false)
    List<Message> getByUser(@Param("user") User user, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}
