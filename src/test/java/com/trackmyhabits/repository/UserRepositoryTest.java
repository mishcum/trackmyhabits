package com.trackmyhabits.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.trackmyhabits.TestPostgres;
import com.trackmyhabits.model.User;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest extends TestPostgres {
    @Autowired
    UserRepository userRepository;

    @Test
    public void savAndFindByEmail() {
        User user = new User("testmail", "testpassword");
        userRepository.save(user);

        assertThat(userRepository.existsByEmail("testmail")).isTrue();
        assertThat(userRepository.findByEmail("testmail")).isPresent();
    }

}
