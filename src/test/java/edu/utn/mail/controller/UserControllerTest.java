package edu.utn.mail.controller;

import edu.utn.mail.domain.City;
import edu.utn.mail.domain.Country;
import edu.utn.mail.domain.User;
import edu.utn.mail.exceptions.UserNotexistException;
import edu.utn.mail.exceptions.ValidationException;
import edu.utn.mail.service.UserService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;


public class UserControllerTest {

    UserService userService;
    UserController userController;

    @Test
    public void testLoginOk() {
        try {
            userService = mock(UserService.class);
            userController = new UserController(userService);
            when(userService.login("username", "password")).thenReturn(new User(1, "nombre", "username", "passord", "surname", new City(1, "Buenos Aires", new Country(1, "Argentina"))));
            User user = userController.login("username", "password");
            assertEquals(user.getUserId(), Integer.valueOf(1));
            assertEquals(user.getUsername(), "username");
            verify(userService, times(1)).login("username", "password");
        } catch (ValidationException e) {
            fail();
        } catch (UserNotexistException e) {
            fail();
        }
    }

    @Test(expected = UserNotexistException.class)
    public void testLoginUserNotFound() throws ValidationException, UserNotexistException {
        userService = mock(UserService.class);
        userController = new UserController(userService);
        when(userService.login("username", "password")).thenThrow(new UserNotexistException());
        User user = userController.login("username", "password");
    }

    @Test(expected = ValidationException.class)
    public void testValidationException() throws ValidationException, UserNotexistException {
        userController = new UserController(userService);
        User user = userController.login(null, null);
    }
}
