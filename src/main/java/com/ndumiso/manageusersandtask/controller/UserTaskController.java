package com.ndumiso.manageusersandtask.controller;

import com.ndumiso.manageusersandtask.exception.ResourceNotFoundException;
import com.ndumiso.manageusersandtask.model.User;
import com.ndumiso.manageusersandtask.model.UserTask;
import com.ndumiso.manageusersandtask.repository.UserRepository;
import com.ndumiso.manageusersandtask.repository.UserTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Ndumiso on 14/06/18.
 */
@RestController
@RequestMapping("/api/user")
public class UserTaskController {

    @Autowired
    UserTaskRepository userTaskRepository;


    @GetMapping("/task")
    public List<UserTask> getAllUserTask() {
        return userTaskRepository.findAll();
    }

    @PostMapping("/task")
    public UserTask createUserTask(@RequestBody @Valid UserTask userTask) {
        return userTaskRepository.save(userTask);
    }

    @GetMapping("/task/{id}")
    public UserTask getUserTaskById(@PathVariable(value = "id") Long userTaskId) {
        return userTaskRepository.findById(userTaskId).orElseThrow(() -> new ResourceNotFoundException("UserTask", "id", userTaskId));
    }

    @PutMapping("/task/{id}")
    public UserTask updateUserTask(@PathVariable(value = "id") Long userTaskId,
                                   @RequestBody @Valid User userDetails) {

        UserTask userTask = userTaskRepository.findById(userTaskId).orElseThrow(() -> new ResourceNotFoundException("UserTask", "id", userTaskId));
        userTask.setTask(userTask.getTask());

        UserTask updatedUserTask = userTaskRepository.save(userTask);
        return updatedUserTask;
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteUserTask(@PathVariable(value = "id") Long userTaskId) {
        UserTask userTask = userTaskRepository.findById(userTaskId).orElseThrow(() -> new ResourceNotFoundException("UserTask", "id", userTaskId));

        userTaskRepository.delete(userTask);

        return ResponseEntity.ok().build();
    }

}
