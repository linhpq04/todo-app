package com.example.todoapp.v1.controller;

import com.example.todoapp.v1.entity.Todo;
import com.example.todoapp.v1.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.todoapp.v1.config.ApiPrefix;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(ApiPrefix.API_V1 + "/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> findAll(Principal principal) {
        return todoService.findAll(principal.getName());
    }

    @PostMapping
    public Todo create(@RequestBody Todo todo, Principal principal) {
        return todoService.create(todo, principal.getName());
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable Long id, @RequestBody Todo todo, Principal principal) {
        return todoService.update(id, todo, principal.getName());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, Principal principal) {
        todoService.delete(id, principal.getName());
    }
}
