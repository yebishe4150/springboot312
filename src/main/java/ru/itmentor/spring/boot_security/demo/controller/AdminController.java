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

    /*
    @PostMapping("/admin/edit/")
    public String editUser(
            @RequestParam("id") Long id,
            @RequestParam("username") String username,
            @RequestParam("role")Set<String> roles){

        userService.updateUser(id, username, roles);
        return "redirect:/admin";
    }

     */

    @PostMapping("/admin/edit/")
    public String editUser(
            @RequestParam("id") Long id,
            @RequestParam("username") String username,
            @RequestParam("role") Set<String> roleNames) {
        Set<Role> roles = roleNames.stream()
                .map(roleRepository::findByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        Set<String> roleNamesSet = roles.stream()
                .map(Role::getName) // Преобразуем обратно в Set<String>
                .collect(Collectors.toSet());

        userService.updateUser(id, username, roleNamesSet);
        return "redirect:/admin";
    }




}
