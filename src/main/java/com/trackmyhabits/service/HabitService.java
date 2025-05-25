package com.trackmyhabits.service;

import java.time.LocalDateTime;
import java.util.List;

import com.trackmyhabits.dto.HabitDTO;
import com.trackmyhabits.dto.HabitLogDTO;
import com.trackmyhabits.model.Habit;

public interface HabitService {
    Habit createHabit(Long userId, HabitDTO habitDTO);
    List<Habit> getHabitsByUserId(Long userId);
    HabitLogDTO toggleCompletion(Long habitId, LocalDateTime date);
}
