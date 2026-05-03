package com.example.todoapp.v1.controller;

import com.example.todoapp.v1.config.ApiPrefix;
import com.example.todoapp.v1.entity.User;
import com.example.todoapp.v1.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(ApiPrefix.API_V1 + "/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> register(@RequestBody User user) {
        authService.register(user);
        return Map.of("message", "success");
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {
        String token = authService.login(body.get("email"), body.get("password"));
        return Map.of("token", token);
    }
}
