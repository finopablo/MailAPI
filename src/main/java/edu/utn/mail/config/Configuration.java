package edu.utn.mail.config;


import edu.utn.mail.domain.City;
import edu.utn.mail.domain.Country;
import edu.utn.mail.domain.User;
import edu.utn.mail.session.SessionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.context.annotation.Configuration
@PropertySource("app.properties")
@EnableScheduling
@EnableCaching
public class Configuration {

    @Autowired
    SessionFilter sessionFilter;
    @Value("${db.driver}")
    String driver;
    @Value("${db.name}")
    String db;
    @Value("${db.host}")
    String host;
    @Value("${db.port}")
    int port;
    @Value("${db.user}")
    String username;
    @Value("${db.password}")
    String password;

    @Bean
    public Connection getConnection() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        Class.forName(driver).newInstance(  );
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/" + db + "?user=" + username + "&password=" + password + "");
        return connection;
    }

    @Bean(name="defaultUserList")
    public List<User> getDefaultList() {
            Country country = new Country(1,"Argentina");
            City city = new City(1,"Mar del Plata", country);

            List<User> userList = new ArrayList<>();
            userList.add(new User(1, "Juan","juanperez","1234", "Perez", city));
            userList.add(new User(2, "Jose","joseperez","1234", "Perez", city));
            return userList;
    }
    @Bean(name="defaultUserList2")
    public List<User> getDefaultList2() {
        Country country = new Country(1,"Argentina");
        City city = new City(1,"Mar del Plata", country);
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "Juan","juanperez2","1234", "Perez", city));
        userList.add(new User(2, "Jose","joseperez2","1234", "Perez", city));
        return userList;
    }


    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(sessionFilter);
        registration.addUrlPatterns("/api/*");
        return registration;
    }
}
