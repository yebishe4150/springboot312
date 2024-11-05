package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String userPage(Model model, Authentication authentication) {
        // Получаем текущего пользователя
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        // Добавляем пользователя в модель
        model.addAttribute("user", user);
        return "user"; // Возвращаем имя HTML-шаблона
    }

    @GetMapping("/user/edit")
    public String editUserPage(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        // Добавляем пользователя в модель
        model.addAttribute("user", user);
        return "edit-user"; // Имя HTML-шаблона для редактирования
    }

    @PostMapping("/user/edit")
    public String editUser(@ModelAttribute User user) {
        // Преобразование Set<Role> в Set<String> для ролей
        Set<String> roleNames = user.getRoles().stream()
                .map(Role::getName) // Предполагается, что у класса Role есть метод getName()
                .collect(Collectors.toSet());
        userService.updateUser(user.getId(), user.getUsername(), roleNames); // Обновляем данные
        return "redirect:/user"; // Перенаправление на страницу пользователя после редактирования
    }
}



