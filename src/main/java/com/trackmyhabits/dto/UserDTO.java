package com.trackmyhabits.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDTO(
    @Email @NotBlank String email,
    @NotBlank @Size(min = 8, max = 128) String password) {
    
}
