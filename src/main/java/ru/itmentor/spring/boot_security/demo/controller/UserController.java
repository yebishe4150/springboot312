package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.Authenticator;

@Controller
public class UserController {
    @GetMapping("user")
    public String userPage(Authenticator authenticator){
        return "user";
    }
}
