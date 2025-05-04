package com.example.vermillion.Controller;

import com.example.vermillion.Model.UserDTO;
import com.example.vermillion.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    UserDTO createUserDTO() {
        return new UserDTO();
    }

    @GetMapping("/register")
    String register() {
        return "register";
    }

    @PostMapping("/register")
    String register(@ModelAttribute("user") UserDTO userDTO) {
        userService.save(userDTO);
        return "redirect:/login";
    }

}