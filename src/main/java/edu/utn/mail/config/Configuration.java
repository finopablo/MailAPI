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



    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(sessionFilter);
        registration.addUrlPatterns("/api/*");
        return registration;
    }
}
