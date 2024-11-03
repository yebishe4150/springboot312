package ru.itmentor.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}








/*
@Controller
public class AuthController {

    private final UserService userService;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("role") String role,
            RedirectAttributes redirectAttributes) {
        if (userService.findByUsername(username) != null) {
            redirectAttributes.addFlashAttribute("error", "Пользователь с таким именем уже существует");
            return "redirect:/login";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        Role role1 = roleRepository.findByName(role)
                .orElseThrow(() -> new RuntimeException("Роль не найдена: " + role));
        Set<Role> roles = new HashSet<>();
        roles.add(role1);
        user.setRoles(roles);

        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("success", "Пользователь успешно зарегистрирован");
        return "redirect:/login";
    }
}
*/


