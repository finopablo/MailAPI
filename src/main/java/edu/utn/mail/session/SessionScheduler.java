package edu.utn.mail.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class SessionScheduler {

    @Autowired
    SessionManager sessionManager;

    @Async
    @Scheduled(fixedRate = 300000)
    public void expiresSessions() {
        sessionManager.expireSessions();
    }

}
