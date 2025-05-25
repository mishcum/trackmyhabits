package com.trackmyhabits.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.trackmyhabits.dto.HabitDTO;
import com.trackmyhabits.dto.HabitLogDTO;
import com.trackmyhabits.model.Habit;
import com.trackmyhabits.model.HabitLog;
import com.trackmyhabits.model.User;
import com.trackmyhabits.repository.HabitLogRepository;
import com.trackmyhabits.repository.HabitRepository;
import com.trackmyhabits.repository.UserRepository;
import com.trackmyhabits.service.HabitService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;
    private final HabitLogRepository habitLogRepository;
    private final UserRepository userRepository;

    public HabitServiceImpl(HabitRepository habitRepository, HabitLogRepository habitLogRepository, UserRepository userRepository) {
        this.habitRepository = habitRepository;
        this.habitLogRepository = habitLogRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Habit createHabit(Long userid, HabitDTO habitDTO) {
        User user = userRepository.getReferenceById(userid);
        Habit habit = new Habit();
        habit.setName(habitDTO.name());
        habit.setDescription(habitDTO.description());
        habit.setUser(user);
        habit.setFrequency(habitDTO.frequency());
        habit.setEvery_n(habitDTO.every_n() != null ? habitDTO.every_n() : 1);
        return habitRepository.save(habit);
    }

    @Override
    public List<Habit> getHabitsByUserId(Long userId) {
        return habitRepository.findByUserId(userId);
    }

    @Override
    public HabitLogDTO toggleCompletion(Long habitId, LocalDateTime date) {
        Habit habit = habitRepository.getReferenceById(habitId);
        HabitLog habitLog = habitLogRepository.findByHabitIdAndDate(habitId, date)
                .map(hl -> 
                {
                    hl.setCompleted(!hl.getCompleted());
                    return hl;
                }
                ).orElseGet(() -> new HabitLog(null, habit, date, true));
        habitLogRepository.save(habitLog);
        return new HabitLogDTO(date, habitLog.getCompleted());
    }
}
