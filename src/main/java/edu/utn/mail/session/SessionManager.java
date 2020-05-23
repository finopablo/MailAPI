package edu.utn.mail.session;

import edu.utn.mail.domain.City;
import edu.utn.mail.domain.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SessionManager {


    Map<String, Session> sessionMap;
    int sessionExpiration = 600000;


    public SessionManager() {
        sessionMap = new Hashtable<>();
        createSession(new User(1, "a", "aa", "bb", "cc", null), "1");
    }

    public String createSession(User user) {
        String token = UUID.randomUUID().toString();
        sessionMap.put(token, new Session(token, user, new Date(System.currentTimeMillis())));
        return token;
    }

    public String createSession(User user, String token) {
        sessionMap.put(token, new Session(token, user, new Date(System.currentTimeMillis())));
        return token;
    }

    public Session getSession(String token) {
        if (token == null) {
            return null;
        }
        Session session = Optional.of(sessionMap.get(token)).orElse(null);
        if (session != null) {
            session.setLastAction(new Date(System.currentTimeMillis()));
        }
        return session;
    }

    public void removeSession(String token) {
        sessionMap.remove(token);
    }

    public void expireSessions() {
        for (String k : sessionMap.keySet()) {
            Session v = sessionMap.get(k);
            if (v.getLastAction().getTime() + (sessionExpiration * 1000) < System.currentTimeMillis()) {
                System.out.println("Expiring session " + k);
                sessionMap.remove(k);
            }
        }
    }

    public User getCurrentUser(String token) {
        return getSession(token).getLoggedUser();
    }
}
