package ru.gasparov.controller;

import lombok.extern.slf4j.Slf4j;
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
@Controller
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public String userList(Model model) {
        List<User> userList = userService.allUser();
        model.addAttribute("forms", userList);
        return "index";
    }

    @PostMapping("/admin/delete")
    private String deleteUser(@RequestParam("id") int id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/addUser")
    private String userForm(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @PostMapping("/admin/addUser")
    private String addUser(@ModelAttribute User user, @RequestParam("a") String[] values) {
        user.setRoles(UtilService.valuesToSetRole(values));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/update/{id}")
    public void updateForm(@PathVariable("id") int id, Model model) {
        User user = userService.findUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        log.info("work update get");
    }

    @PostMapping("/admin/update/{id}")
    public String updateUser(@Valid @ModelAttribute("user")User user,
                             @RequestParam("name") String name,
                             @RequestParam("lastName") String lastName,
                             @RequestParam("age") int age,
                             @RequestParam("mail") String mail,
                             @RequestParam("id") int id,
                             @RequestParam("pass") String pass,
                             @RequestParam("a") String[] values) {
        user.setId(id);
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        user.setMail(mail);
        user.setPassword(new BCryptPasswordEncoder().encode(pass));
        log.info(Arrays.toString(values));
        user.setRoles(UtilService.valuesToSetRole(values));
        userService.addUser(user);
        log.info("work update controller");
        return "redirect:/admin";
    }

    @GetMapping("/admin/addRole/{id}")
    public String updateFormForRole(@PathVariable("id") int id, Model model) {
        User user = userService.findUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "updateUser";
    }
}
