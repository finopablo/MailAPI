package edu.utn.mail.dao.mysql;

import com.mysql.cj.exceptions.MysqlErrorNumbers;
import edu.utn.mail.dao.UserDao;
import edu.utn.mail.domain.City;
import edu.utn.mail.domain.Country;
import edu.utn.mail.domain.User;
import edu.utn.mail.exceptions.UserAlreadyExistsException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static edu.utn.mail.dao.mysql.MySQLUtils.*;

public class UserMySQLDao implements UserDao {

    final Connection connection;

    public UserMySQLDao(DriverManagerDataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    @Override
    public User getByUsername(String username, String password) {
        try {
            PreparedStatement ps = connection.prepareStatement(GET_BY_USERNAME_USER_QUERY);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = createUser(rs);
            }
            rs.close();
            ps.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener datos de usuario", e);
        }
    }

    private User createUser(ResultSet rs) throws SQLException {
        User u = new User(rs.getInt("id_user"), rs.getString("name"), rs.getString("pwd"),
                rs.getString("surname"), rs.getString("username"), new City(rs.getInt("id_city"),
                rs.getString("city_name"), new Country(rs.getInt("id_country"), rs.getString("country_name"))));
        return u;
    }

    @Override
    public List<User> getByCity(City city) {
        return null;
    }

    @Override
    public User add(User value) throws UserAlreadyExistsException {
        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, value.getName());
            ps.setString(2, value.getSurname());
            ps.setString(3, value.getUsername());
            ps.setString(4, value.getPassword());
            ps.setInt(5, value.getCity().getCityId());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                value.setUserId(rs.getInt(1));
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == MysqlErrorNumbers.ER_DUP_ENTRY) { //Parametrizar
                throw new UserAlreadyExistsException();
            } else {
                throw new RuntimeException("Error al agregar el usuario", e);
            }
        }
        return value;
    }

    @Override
    public Integer update(User value) {

        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE_USER_QUERY);
            ps.setString(1, value.getName());
            ps.setString(2, value.getSurname());
            ps.setInt(3, value.getCity().getCityId());
            ps.setString(4, value.getPassword());
            Integer rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Error al modificar usuario", sqlException);
        }

    }

    @Override
    public Integer remove(Integer id) {

        try {
            PreparedStatement ps = connection.prepareStatement(REMOVE_USER_QUERY);
            ps.setInt(1, id);
            Integer rowsAffected = ps.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar usuario", e);
        }
    }

    @Override
    public Integer remove(User value) {
        return remove(value.getUserId());
    }

    @Override
    public User getById(Integer id) {
        User user = null;
        try {
            PreparedStatement ps = connection.prepareStatement(GET_BY_ID_USER_QUERY);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = createUser(rs);
            }
            rs.close();
            ps.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener datos de usuario", e);
        }

    }

    @Override
    public List<User> getAll() {

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(BASE_USER_QUERY);
            List<User> userList = new ArrayList<>();
            while (rs.next()) {
                userList.add(createUser(rs));
            }
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la lista de usuarios", e);
        }
    }
}
