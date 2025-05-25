package com.trackmyhabits.dto;

import com.trackmyhabits.model.Habit.Frequency;

import jakarta.validation.constraints.NotBlank;

public record HabitDTO(@NotBlank String name,
                       String description,
                       Frequency frequency,
                       Integer every_n) {}
