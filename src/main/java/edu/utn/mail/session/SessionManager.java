package edu.utn.mail.session;

import edu.utn.mail.domain.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SessionManager {

        Map<String, Session> sessionMap = new Hashtable<>();

        int sesionExpiration = 60;

        public String createSession(User user) {
            String token = UUID.randomUUID().toString();
            sessionMap.put(token, new Session(token, user, new Date(System.currentTimeMillis())));
            return token;
        }

        public Session getSession(String token) {
            Session session = sessionMap.get(token);
            if (session!=null) {
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
                if (v.getLastAction().getTime() < System.currentTimeMillis() + (sesionExpiration*1000)) {
                    System.out.println("Expiring session " + k);
                    sessionMap.remove(k);
                }
            }
        }

        public User getCurrentUser(String token) {
            return getSession(token).getLoggedUser();
        }
}
