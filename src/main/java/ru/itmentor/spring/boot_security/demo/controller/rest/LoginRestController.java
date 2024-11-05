package ru.itmentor.spring.boot_security.demo.controller.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;

@RestController
@RequestMapping("/api")
public class LoginRestController {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginRestController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
            );
            return ResponseEntity.ok("vse norm "+authentication.getName());
        }catch (Exception e){
            return ResponseEntity.status(401).body("error ))))) "+e.getMessage());
        }
    }

    @GetMapping("/login")
    public ResponseEntity<String> getLoginPage(){
        return ResponseEntity.ok("get login page");
    }
}
