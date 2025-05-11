package com.example.vermillion.Service;

import com.example.vermillion.Model.User;
import com.example.vermillion.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(String username, String rawPassword) {
        User u = new User();
        u.setUsername(username);
        u.setPassword(passwordEncoder.encode(rawPassword));
        u.setRole("ROLE_USER");
        return userRepository.save(u);
    }
    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}