package com.trackmyhabits.dto;

import java.time.LocalDateTime;

public record HabitLogDTO(LocalDateTime date, Boolean completed) {}
