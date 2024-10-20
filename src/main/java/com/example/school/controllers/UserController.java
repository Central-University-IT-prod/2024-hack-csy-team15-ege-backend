package com.example.school.controllers;


import com.example.school.models.tasks.Task;
import com.example.school.models.user.User;
import com.example.school.models.user.UserNotFoundException;
import com.example.school.models.user.UserRepository;
import com.example.school.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("/users")
    ResponseEntity<List<User>> getUserByParams(@RequestParam(required = false) Integer exp ,
                                               @RequestParam(required = false) Integer level) {
        return ResponseEntity.ok(userService.usersWithParams(exp, level));
    }

    @GetMapping("/users/{id}/completed")
    ResponseEntity<Boolean> hasUserCompletedTask(@PathVariable Long id, @RequestParam Long taskId) {
        return ResponseEntity.ok(userService.hasUserCompletedTask(id, taskId));
    }

    @PutMapping("/users/{id}/completed")
    ResponseEntity<Boolean> userCompleteTask(@PathVariable Long id, @RequestBody Long taskId) {
        return ResponseEntity.ok(userService.userCompleteTask(id, taskId));
    }
}
