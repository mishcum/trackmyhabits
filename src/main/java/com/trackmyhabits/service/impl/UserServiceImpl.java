package com.trackmyhabits.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trackmyhabits.dto.UserDTO;
import com.trackmyhabits.model.User;
import com.trackmyhabits.repository.UserRepository;
import com.trackmyhabits.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.email())) {throw new IllegalArgumentException("This email is already registered");}
        User user = new User(userDTO.email(), passwordEncoder.encode(userDTO.password()));
        return userRepository.save(user);
    }
    
}
