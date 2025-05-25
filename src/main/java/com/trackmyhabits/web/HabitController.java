package com.trackmyhabits.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trackmyhabits.dto.HabitDTO;
import com.trackmyhabits.dto.HabitLogDTO;
import com.trackmyhabits.model.Habit;
import com.trackmyhabits.service.HabitService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/habits")
public class HabitController {

    private final HabitService habitService;
    
    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }


    // переделать попозжа (айди из жвт брать)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Habit create(@RequestParam Long userId, @RequestBody @Valid HabitDTO habitDTO) {
        return habitService.createHabit(userId, habitDTO);
    }

    @GetMapping
    public List<Habit> HabitsList(@RequestParam Long userId) {
        return habitService.getHabitsByUserId(userId);
    }

    @PostMapping("/{id}/log")
    public HabitLogDTO toggle(@RequestParam Long id, @RequestBody HabitLogDTO habitLogDTO) {
        LocalDateTime date = habitLogDTO.date() == null ? LocalDateTime.now() : habitLogDTO.date();
        return habitService.toggleCompletion(id, date);
    }
    
}
