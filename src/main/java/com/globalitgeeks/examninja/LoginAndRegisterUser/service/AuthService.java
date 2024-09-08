package com.globalitgeeks.examninja.LoginAndRegisterUser.service;

import com.globalitgeeks.examninja.LoginAndRegisterUser.dto.UserDTO;
import com.globalitgeeks.examninja.LoginAndRegisterUser.entity.User;
import com.globalitgeeks.examninja.LoginAndRegisterUser.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    // Login logic
    public boolean loginUser(UserDTO request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        return user.isPresent() && (request.getPassword().equals(user.get().getPassword()));
    }

    // Registration logic
    public void registerUser(UserDTO request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // Encode password
        user.setRole("USER");
        userRepository.save(user);
    }

    // Update password (forgot password)
    public void updatePassword(UserDTO request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setPassword(request.getPassword()); // Update password
            userRepository.save(existingUser);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

}
