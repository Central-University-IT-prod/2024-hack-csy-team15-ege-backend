package com.example.school.models.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {super("No task with required id:" + id);}
    public UserNotFoundException(String message) {
        super(message);
    }
}
