package ru.itmentor.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.Set;

@RestController
@RequestMapping("/manage")
public class UserManagerController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public User addUser (@RequestParam String username,
                         @RequestParam String password,
                         @RequestParam Set<String> roles
                         ){
        return userService.saveUser(username,password,roles);
    }
}
