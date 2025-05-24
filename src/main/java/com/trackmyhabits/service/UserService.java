package com.trackmyhabits.service;

import com.trackmyhabits.dto.UserDTO;
import com.trackmyhabits.model.User;

public interface UserService {
    User register(UserDTO userDTO);
}
