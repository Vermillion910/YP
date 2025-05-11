package com.example.vermillion.Controller;

import com.example.vermillion.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ) {
        if (userService.userExists(username)) {
            model.addAttribute("error", "Пользователь уже существует");
            return "auth/register";
        }
        userService.register(username, password);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(required=false) String error,
                                @RequestParam(required=false) String logout,
                                Model model) {
        if (error!=null) model.addAttribute("error","Неверные данные");
        if (logout!=null) model.addAttribute("msg","Вы вышли");
        return "auth/login";
    }
}
