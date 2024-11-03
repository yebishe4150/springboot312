package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.net.Authenticator;
import java.util.List;


/*
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List <User> getAllUsers(){
        return userService.getAllUsers();
    }
*/
    /*
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public User createUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.saveUser(user);
    }

     */

/*
    public User userPage (@AuthenticationPrincipal User user){
        return user;
    }*/

@Controller
public class UserController {

    @GetMapping("/user")
    public String userPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Получаем данные пользователя (например, роли) для отображения на странице
        model.addAttribute("user", authentication.getPrincipal());

        return "user";
    }
}



