package com.trackmyhabits.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.trackmyhabits.dto.UserDTO;
import com.trackmyhabits.model.User;
import com.trackmyhabits.repository.UserRepository;


@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Container
    @org.springframework.boot.testcontainers.service.connection.ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.2");

    @Test
    public void TestRegisterUserAndPasswordHash() {
        User user = userService.register(new UserDTO("myemail@mail.ru", "password123"));
        Assert.assertTrue(userRepository.existsByEmail(user.getEmail()));
        Assert.assertTrue(user.getPassword().startsWith("$2"));
    }
}
