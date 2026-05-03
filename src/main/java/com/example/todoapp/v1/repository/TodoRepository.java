package com.example.todoapp.v1.repository;

import com.example.todoapp.v1.enums.Priority;
import com.example.todoapp.v1.enums.Status;
import com.example.todoapp.v1.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserId(Long id);
    List<Todo> findByStatus(Status status);
    List<Todo> findByPriority(Priority priority);
    List<Todo> findByUserIdAndStatus(Long userId, Status status);
    List<Todo> findByTitleContaining(String keyword);
}
