package ru.gasparov.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gasparov.model.User;
import ru.gasparov.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String userPage(Principal principal, ModelMap model) {
        List<String> messages = new ArrayList<>();
        User user = userService.findUserByName(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
        messages.add("Name: " + user.getName());
        messages.add("ID: " + user.getId());
        messages.add("E-mail: " + user.getMail());
        model.addAttribute("messages", messages);
        return "hello";
    }
}