package com.example.todoapp.v1.controller;

import com.example.todoapp.v1.config.ApiPrefix;
import com.example.todoapp.v1.entity.User;
import com.example.todoapp.v1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPrefix.API_V1 + "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User find(@PathVariable Long id) {
        return userService.find(id);
    }
}
