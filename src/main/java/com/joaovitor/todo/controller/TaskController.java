package com.joaovitor.todo.controller;

import java.util.List;

import javax.validation.Valid;

import com.joaovitor.todo.dto.TaskDTO;
import com.joaovitor.todo.exceptions.TaskNotValidException;
import com.joaovitor.todo.model.Task;
import com.joaovitor.todo.service.TaskService;
import com.joaovitor.todo.utils.TaskUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${tasks.uri}")
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

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable long id){
        return service.findById(id);
    } 

    @PostMapping()
    public ResponseEntity<Task> create(@Valid @RequestBody TaskDTO task, BindingResult result) {
        if(result.hasErrors()){
            throw new TaskNotValidException(result);
        }
        return service.create(TaskUtil.toEntity(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return service.delete(id);
    }

    @GetMapping("/finished")
    public ResponseEntity<List<Task>> findByIsFinished() {
        return service.findByIsFinished();
    }
    
    @GetMapping("/not-finished")
    public ResponseEntity<List<Task>> findByIsNotFinished() {
        return service.findByIsNotFinished();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Task> update(@RequestBody Task task) {
        return service.update(task);
    }

    @PatchMapping("/set-as-finished/{id}")
    private ResponseEntity<String> finishTask(@RequestBody Long id) {
        return service.finishTask(id);
    }

}
