package edu.utn.mail.controller.web;


import edu.utn.mail.controller.UserController;
import edu.utn.mail.domain.User;
import edu.utn.mail.dto.ErrorResponseDto;
import edu.utn.mail.dto.LoginRequestDto;
import edu.utn.mail.exceptions.UserNotexistException;
import edu.utn.mail.exceptions.ValidationException;
import edu.utn.mail.session.SessionManager;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LoginController {

    UserController userController;
    SessionManager sessionManager;

    @Autowired
    public LoginController(UserController userController, SessionManager sessionManager) {
        this.userController = userController;
        this.sessionManager = sessionManager;
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto) {
        ResponseEntity response;
        try {
            User u = userController.login(loginRequestDto.getUsername(), loginRequestDto.getPassword());
            String token = sessionManager.createSession(u);
            response = ResponseEntity.ok().headers(createHeaders(token)).build();
        } catch (UserNotexistException e) {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDto(1, "User does not exists"));
        } catch (ValidationException validationException) {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(1, "User or password cannot be null"));
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponseDto(1, "Unknown error"));
        }
        return response;
    }


    @PostMapping("/logout")
    public ResponseEntity logout(@RequestHeader("Authorization") String token) {
        sessionManager.removeSession(token);
        return ResponseEntity.ok().build();
    }

    private HttpHeaders createHeaders(String token) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", token);
        return responseHeaders;
    }


}
