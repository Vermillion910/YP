package com.example.vermillion.Impl;

import com.example.vermillion.Model.User;
import com.example.vermillion.Model.UserDTO;
import com.example.vermillion.Repository.UserRepository;
import com.example.vermillion.Service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;

    public UserServiceImpl(BCryptPasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @Override
    public void save(UserDTO userDto) {
        User user = new User();
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setUsername(userDto.getUsername());
        user.setRole("USER");
        userRepository.save(user);

    }

    @Override
    public List<User> findAll() {
        return List.of();
    }
}