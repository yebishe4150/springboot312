package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class AdminController {
    private final UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String adminPage(Model model){
        List <User> users = userService.getAllUsers();
        model.addAttribute("users", users);

        List <Role> roles = roleRepository.findAll();
        model.addAttribute("allRoles", roles);
        return "admin";
    }

    @PostMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUserPage(@PathVariable Long id, Model model) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
            return "edit-user"; // Имя HTML файла для редактирования
        }
        return "redirect:/admin"; // Если пользователь не найден, перенаправить на страницу админа
    }


    @PostMapping("/admin/edit")
    public String editUser(@ModelAttribute User user) {
        Set<String> roleNames = user.getRoles().stream()
                .map(Role::getName) // Получаем имена ролей
                .collect(Collectors.toSet());

        userService.updateUser(user.getId(), user.getUsername(), roleNames);
        return "redirect:/admin";
    }

}
