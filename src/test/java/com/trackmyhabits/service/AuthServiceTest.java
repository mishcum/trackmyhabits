package com.trackmyhabits.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.trackmyhabits.TestPostgres;
import com.trackmyhabits.dto.AuthRequest;
import com.trackmyhabits.dto.AuthResponse;
import com.trackmyhabits.dto.UserDTO;

@SpringBootTest
@ActiveProfiles("test")
public class AuthServiceTest extends TestPostgres {
    
    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;

    @Test
    public void loginReturnToken() {
        userService.register(new UserDTO("testmail", "testpassword"));
        AuthResponse authResponse = authService.login(
            new AuthRequest("testmail", "testpassword")
        );

        assertThat(authResponse.token()).isNotBlank();
    }

    @Test
    public void loginWithWrongPassword() {
        userService.register(new UserDTO("testmailw", "testpassword"));
        assertThatThrownBy(() ->
            authService.login(new AuthRequest("testmailw", "wrongpassword")))
            .isInstanceOf(IllegalArgumentException.class);
    }

}
