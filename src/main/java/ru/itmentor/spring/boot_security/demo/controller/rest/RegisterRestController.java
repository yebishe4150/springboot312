package ru.itmentor.spring.boot_security.demo.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class RegisterRestController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public RegisterRestController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/register")  // Путь для регистрации пользователя
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Проверка существования пользователя
        if (userService.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Пользователь с таким именем уже существует");
        }
        user.setPassword(user.getPassword());

        // Присвоение ролей (по умолчанию ROLE_USER, можно изменить по необходимости)
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Роль ROLE_USER не найдена")));

        // Добавление роли ROLE_ADMIN, если это необходимо
        if (user.getRoles().contains("ROLE_ADMIN")) {
            roles.add(roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("Роль ROLE_ADMIN не найдена")));
        }

        user.setRoles(roles);
        userService.saveUser(user);  // Сохранение пользователя в базе данных
        return ResponseEntity.ok("Пользователь успешно зарегистрирован через API");
    }

    @GetMapping("/register")
    public ResponseEntity<String> getLoginPage() {
        return ResponseEntity.ok("get register page");
    }
}
