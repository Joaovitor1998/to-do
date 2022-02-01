package com.joaovitor.todo.controller;

import java.util.List;

import com.joaovitor.todo.dto.TaskDTO;
import com.joaovitor.todo.model.Task;
import com.joaovitor.todo.service.TaskService;
import com.joaovitor.todo.utils.TaskUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    private TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Task>> findAll() {
        return service.findAll();
    }

    @GetMapping("/done")
    public ResponseEntity<List<Task>> findByIdDoneTrue() {
        return service.findByIsDoneTrue();
    }

    @GetMapping("/not-done")
    public ResponseEntity<List<Task>> findByIsDoneFalse() {
        return service.findByIsDoneFalse();
    }

    @PostMapping("/new")
    public ResponseEntity<Task> create(@RequestBody TaskDTO task) {
        return service.create(TaskUtil.toEntity(task));
    }

    @PatchMapping("/edit")
    public ResponseEntity<Task> update(@RequestBody Task task) {
        return service.update(task);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return service.delete(id);
    }

    @PatchMapping("/setIsDone")
    private ResponseEntity<String> toggleIsDoneStatus(@RequestBody Task task) {
        service.toggleIsDoneStatus(task);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Deleted successfully!");
    }

}
