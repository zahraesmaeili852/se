package com.universitylibrary.services;

import com.universitylibrary.exceptions.AuthenticationException;
import com.universitylibrary.exceptions.UserAlreadyExistsException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest {

    @Test
    void registerAndLoginStudentSuccessfully() {
        AuthService auth = new AuthService();

        auth.registerStudent("zahra", "1234");

        assertNotNull(auth.loginStudent("zahra", "1234"));
    }

    @Test
    void registerDuplicateStudentShouldThrowException() {
        AuthService auth = new AuthService();

        auth.registerStudent("zahra", "1234");

        assertThrows(UserAlreadyExistsException.class,
                () -> auth.registerStudent("zahra", "abcd"));
    }

    @Test
    void loginWithWrongPasswordShouldThrowException() {
        AuthService auth = new AuthService();

        auth.registerStudent("zahra", "1234");

        assertThrows(AuthenticationException.class,
                () -> auth.loginStudent("zahra", "wrong"));
    }
}