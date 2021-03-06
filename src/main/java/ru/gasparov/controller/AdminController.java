package ru.gasparov.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gasparov.model.User;
import ru.gasparov.service.UserService;

import java.util.List;

@Slf4j
@RestController
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/api/usersi")
    public List<User> userListi() {
        List<User> users = userService.allUser();
        log.info("[REST.GET2]" + users.toString());
        return users;
    }

    @GetMapping(value = "/api/users")
    public List<User> userList() {
        List<User> users = userService.allUser();
       log.info("[REST.GET]" + users.toString());
       return users;
    }

    @GetMapping(value = "/api/findUser/{id}")
    public User findUser(@PathVariable int id) {
        log.info("[REST.GET]/api/findUser/" + id);
        return userService.findUserById(id).get();
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
