package edu.utn.mail.dao.mysql;

import edu.utn.mail.domain.City;
import edu.utn.mail.domain.Country;
import edu.utn.mail.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static edu.utn.mail.dao.mysql.MySQLUtils.GET_BY_USERNAME_USER_QUERY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserMySqlDaoTest {

    UserMySQLDao dao;

    @Mock
    Connection connection;

    @Mock
    PreparedStatement ps;

    @Mock
    ResultSet rs;

    @Before
    public void setUp() {
        initMocks(this);
        dao = new UserMySQLDao(connection);
    }

    @Test
    public void testGetByUsernameOk() throws SQLException {
        when(connection.prepareStatement(GET_BY_USERNAME_USER_QUERY)).thenReturn(ps);

        doNothing().when(ps).setString(1, "user");
        doNothing().when(ps).setString(2, "pwd");

        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getInt("id_user")).thenReturn(1);
        when(rs.getInt("id_country")).thenReturn(2);
        when(rs.getInt("id_city")).thenReturn(3);
        when(rs.getString(any())).thenReturn("StringValue");
        doNothing().when(rs).close();
        doNothing().when(ps).close();

        User u = dao.getByUsername("user", "pwd");

        //Asserts
        assertEquals("StringValue", u.getUsername());
        assertEquals(Integer.valueOf(1), u.getUserId());
        assertEquals(Integer.valueOf(3), u.getCity().getCityId());
        assertEquals(Integer.valueOf(2), u.getCity().getCountry().getCountryId());

        verify(ps, times(2)).setString(anyInt(), anyString());

    }

    @Test
    public void testGetByUsernameNotFound() throws SQLException {
        when(connection.prepareStatement(GET_BY_USERNAME_USER_QUERY)).thenReturn(ps);

        doNothing().when(ps).setString(1, "user");
        doNothing().when(ps).setString(2, "pwd");

        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false);
        doNothing().when(rs).close();
        doNothing().when(ps).close();

        User u = dao.getByUsername("user", "pwd");

        //Asserts
        assertNull(u);
    }

    @Test(expected = RuntimeException.class)
    public void testGetByUsernameError() throws SQLException {
        when(connection.prepareStatement(GET_BY_USERNAME_USER_QUERY)).thenThrow(new SQLException());
        User u = dao.getByUsername("user", "pwd");
    }

}
