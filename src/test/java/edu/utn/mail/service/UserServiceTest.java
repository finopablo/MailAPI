package edu.utn.mail.service;


import edu.utn.mail.dao.UserDao;
import edu.utn.mail.domain.User;
import edu.utn.mail.exceptions.UserNotexistException;
import edu.utn.mail.exceptions.ValidationException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


public class UserServiceTest {

    UserService service;

    @Mock
    UserDao dao;

    @Before
    public void setUp() {
            initMocks(this);
            service = new UserService(dao);
    }

    @Test
    public void testLoginOk() throws UserNotexistException, ValidationException {
        User loggedUser = new User(1, "Nombre", "username", "", "Surname", null);
        when(dao.getByUsername("user","pwd")).thenReturn(loggedUser);
        User returnedUser = service.login("user","pwd");
        assertEquals(loggedUser.getUserId(), returnedUser.getUserId());
        assertEquals(loggedUser.getUsername(), returnedUser.getUsername());
        verify(dao, times(1)).getByUsername("user","pwd");
    }

    @Test(expected = UserNotexistException.class)
    public void testLoginUserNotFound() throws UserNotexistException {
        when(dao.getByUsername("user","pwd")).thenReturn(null);
        service.login("user", "pwd");
    }



}
