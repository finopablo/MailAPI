package edu.utn.mail.dao.mysql;

import edu.utn.mail.dao.UserDao;
import edu.utn.mail.domain.City;
import edu.utn.mail.domain.Country;
import edu.utn.mail.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserMySQLDao implements UserDao {

    Connection connection;

    public UserMySQLDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User getByUsername(String username, String password) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from users u inner join cities c inner join countries co on co.id_country = c.id_country on u.id_city = c.id_city where username = ? and pwd=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            User u = null;
            if (rs.next()) {
                u =  new User(rs.getInt("id_user"), rs.getString("name"), rs.getString("pwd"),
                            rs.getString("surname"), rs.getString("username"), new City(rs.getInt("id_city"),
                        rs.getString("city_name"), new Country(rs.getInt("id_country"), rs.getString("country_name"))));
            }
            rs.close();
            ps.close();
            return u;
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener datos de usuario", e);
        }
    }

    @Override
    public List<User> getByCity(City city) {
        return null;
    }

    @Override
    public User add(User value) {
        return null;
    }

    @Override
    public User update(User value) {
        return null;
    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public void remove(User value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
