package com.trackmyhabits.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.trackmyhabits.TestPostgres;
import com.trackmyhabits.dto.UserDTO;
import com.trackmyhabits.model.User;
import com.trackmyhabits.repository.UserRepository;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest extends TestPostgres {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    public void TestRegisterUserAndPasswordHash() {
        User user = userService.register(new UserDTO("myemail@mail.ru", "password123"));
        assertThat(userRepository.existsByEmail(user.getEmail())).isTrue();
        assertThat(user.getPassword().startsWith("$2"));
    }
}
