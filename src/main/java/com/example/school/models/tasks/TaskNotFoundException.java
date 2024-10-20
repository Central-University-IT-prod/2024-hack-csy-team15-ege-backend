package com.example.school.models.tasks;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String message) {
        super(message);
    }
    public TaskNotFoundException(Long id) {
        super("No task with required id:" + id);
    }
}
