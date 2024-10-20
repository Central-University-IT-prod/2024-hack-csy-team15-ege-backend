package com.example.school.controllers;


import com.example.school.models.tasks.Task;
import com.example.school.models.tasks.TaskNotFoundException;
import com.example.school.models.tasks.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController (TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks/{id}")
    ResponseEntity<Optional<Task>> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            throw new TaskNotFoundException(id);
        }
        return ResponseEntity.ok(task);
    }

    @GetMapping("/tasks")
    ResponseEntity<List<Task>> getTaskByParams(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String level,
            @RequestParam(required = false) String subject) {
        if (type == null && level == null && subject == null) {
            return ResponseEntity.ok((List<Task>) taskRepository.findAll());
        }
        List<Task>tasks = taskRepository.findByTypeAndLevelAndSubject(type, level, subject);
        System.out.println(tasks);
        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("No task with required params");
        }
        return ResponseEntity.ok(tasks);
    }
}
