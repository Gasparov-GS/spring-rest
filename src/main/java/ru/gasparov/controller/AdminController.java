package ru.gasparov.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gasparov.model.User;
import ru.gasparov.service.UserService;
import ru.gasparov.service.UtilService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/api/users")
    public List<User> userList() {
        return userService.allUser();
    }

    @GetMapping(value = "/admin/users/{id}")
    public User getUser(@PathVariable int id) {
        return userService.findUserById(id).get();
    }

    @DeleteMapping(value = "/api/deleteUser/{id}")
    private void deleteUser(@PathVariable int id) {
        userService.removeUser(id);
        log.info("[REST.DELETE]/api/deleteUser with id: " + id);
    }


    @PostMapping(value = "/api/addUser")
    private ResponseEntity<User> addUser(@RequestBody User user) {
        userService.addUser(user);
        log.info("[REST.POST]/api/addUser " + user.toString());
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/api/editUser")
    public void updateUser(@RequestBody User user) {
        userService.addUser(user);
        log.info("[REST.PATCH]/api/editUser " + user.toString());
    }

}
