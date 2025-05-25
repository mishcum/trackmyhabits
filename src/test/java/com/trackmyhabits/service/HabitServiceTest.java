package com.trackmyhabits.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.trackmyhabits.TestPostgres;
import com.trackmyhabits.dto.HabitDTO;
import com.trackmyhabits.dto.HabitLogDTO;
import com.trackmyhabits.dto.UserDTO;
import com.trackmyhabits.model.Habit;
import com.trackmyhabits.model.Habit.Frequency;

@SpringBootTest
@ActiveProfiles("test")
public class HabitServiceTest extends TestPostgres {
    
    @Autowired
    UserService userService;
    @Autowired
    HabitService habitService;

    @Test
    void createListToggleFlow() {
        Long userId = userService.register(new UserDTO("testmailh", "testpasswordh")).getId();

        HabitDTO dto = new HabitDTO("testnameh", "testdescriptionh", Frequency.DAILY, 1);
        Habit h = habitService.createHabit(userId, dto);
        assertThat(h.getId()).isNotNull();

        List<Habit> list = habitService.getHabitsByUserId(userId);
        assertThat(list).hasSize(1);

        LocalDateTime today = LocalDateTime.now().withHour(0).withMinute(0);
        HabitLogDTO log = habitService.toggleCompletion(h.getId(), today);
        assertThat(log.completed()).isTrue();

        HabitLogDTO second = habitService.toggleCompletion(h.getId(), today);
        assertThat(second.completed()).isFalse();
    }
}
