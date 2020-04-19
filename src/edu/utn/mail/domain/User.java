package edu.utn.mail.domain;

public class User {

    Integer userId;
    String name;
    String username;
    String password;
    String surname;
    City city;

    public User(Integer userId, String name, String username, String password, String surname, City city) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.surname = surname;
        this.city = city;
    }

    public User( String name, String username, String password, String surname, City city) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.surname = surname;
        this.city = city;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", surname='" + surname + '\'' +
                ", city=" + city +
                '}';
    }
}
