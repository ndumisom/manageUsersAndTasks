package com.ndumiso.manageusersandtask.controller;

import com.ndumiso.manageusersandtask.exception.ResourceNotFoundException;
import com.ndumiso.manageusersandtask.model.User;
import com.ndumiso.manageusersandtask.model.UserTask;
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


    @GetMapping("/{id}/name")
    public List<UserTask> getAllUserTask() {
        return userTaskRepository.findAll();
    }

    @PostMapping("/{id}/name")
    public UserTask createUserTask(@RequestBody @Valid UserTask userTask,@PathVariable(value = "id") Long userTaskId) {
        userTask.setUserId(userTaskId);
        return userTaskRepository.save(userTask);
    }

    @GetMapping("/{id}/name/{id}")
    public UserTask getUserTaskById(@PathVariable(value = "id") Long userTaskId) {
        return userTaskRepository.findById(userTaskId).orElseThrow(() -> new ResourceNotFoundException("UserTask", "id", userTaskId));
    }

    @PutMapping("/{id}/name/{id}")
    public UserTask updateUserTask(@PathVariable(value = "id") Long userTaskId,
                                   @RequestBody @Valid User userDetails) {

        UserTask userTask = userTaskRepository.findById(userTaskId).orElseThrow(() -> new ResourceNotFoundException("UserTask", "id", userTaskId));
        userTask.setName(userTask.getName());

        UserTask updatedUserTask = userTaskRepository.save(userTask);
        return updatedUserTask;
    }

    @DeleteMapping("/{id}/name/{id}")
    public ResponseEntity<?> deleteUserTask(@PathVariable(value = "id") Long userTaskId) {
        UserTask userTask = userTaskRepository.findById(userTaskId).orElseThrow(() -> new ResourceNotFoundException("UserTask", "id", userTaskId));

        userTaskRepository.delete(userTask);

        return ResponseEntity.ok().build();
    }


}
