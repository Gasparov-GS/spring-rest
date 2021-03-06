package ru.gasparov.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
public class UserController {

    @GetMapping("/user")
    public String userPage() {
        return "index";
    }

    @GetMapping(value = "/admin")
    public String userList() {
        return "index";
    }

    @GetMapping("/admin/addUser")
    private String userForm() {
        return "add";
    }
}