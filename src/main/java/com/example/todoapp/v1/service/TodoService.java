package com.example.todoapp.v1.service;

import com.example.todoapp.v1.entity.Todo;
import com.example.todoapp.v1.entity.User;
import com.example.todoapp.v1.repository.TodoRepository;
import com.example.todoapp.v1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Todo> findAll(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return todoRepository.findByUserId(user.getId());
    }

    public Todo create(Todo todo, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    public Todo update(Long id, Todo request, String email) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found"));

        verifyOwnership(todo, email);

        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setStatus(request.getStatus());
        todo.setPriority(request.getPriority());
        todo.setDueDate(request.getDueDate());

        return todoRepository.save(todo);
    }

    public void delete(Long id, String email) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found"));
        verifyOwnership(todo, email);
        todoRepository.delete(todo);
    }

    public void verifyOwnership(Todo todo, String email) {
        boolean isBelongToCurrentUser = todo.getUser().getEmail().equals(email);
        if (!isBelongToCurrentUser) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "ACCESS DENIED");
        }
    }
}
