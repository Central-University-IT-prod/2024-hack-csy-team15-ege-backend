package com.example.school.service;

import com.example.school.models.tasks.Task;
import com.example.school.models.tasks.TaskRepository;
import com.example.school.models.user.User;
import com.example.school.models.user.UserNotFoundException;
import com.example.school.models.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public UserService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public boolean hasUserCompletedTask(Long userId, Long taskId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(userId);
        }
        User user = optionalUser.get();
        return (user.getSolvedTasks().contains(String.valueOf(taskId)));
    }

    public List<User> usersWithParams(Integer exp, Integer level) {
        if (exp == null && level == null) {
            return (List<User>) userRepository.findAll();
        }
        List<User> users = userRepository.findByExpAndLevel(exp, level);
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users with required params");
        }
        return users;
    }

    public boolean userCompleteTask(Long userId, Long taskId) {
        if (hasUserCompletedTask(userId, taskId)) {
            return false;
        }
        User user = userRepository.findById(userId).get();
        Task task = taskRepository.findById(taskId).get();
        user.getSolvedTasks().add(String.valueOf(taskId));
        user.setExp(user.getExp() + task.getExp());
        userRepository.save(user);
        taskRepository.save(task);
        return true;
    }
}
