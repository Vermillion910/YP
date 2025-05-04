package com.example.vermillion.Service;

import com.example.vermillion.Model.User;
import com.example.vermillion.Model.UserDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService {
    void save(UserDTO userDTO);
    List<User> findAll();
}
