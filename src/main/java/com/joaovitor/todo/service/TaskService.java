package com.joaovitor.todo.service;

import java.util.List;
import java.util.Optional;

import com.joaovitor.todo.exceptions.TaskNotFoundException;
import com.joaovitor.todo.model.Task;
import com.joaovitor.todo.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private TaskRepository repository;

    @Autowired
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task verifyById(long id) {
      
        Optional<Task> taskFound = repository.findById(id);
      
        if (taskFound.isEmpty()) {
            String errorMessage = String.format("Task with id %d not found.", id);
            throw new TaskNotFoundException(errorMessage);
        }
      
        return taskFound.get();
    }

    public ResponseEntity<Task> findById(long id){
        
        Task taskFound = this.verifyById(id);
        
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(taskFound);
    }

    public ResponseEntity<List<Task>> findAll() {

        List<Task> tasksFound = this.repository.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tasksFound);
    }

    public ResponseEntity<List<Task>> findByIsFinished() {

        List<Task> tasksFound = this.repository.findByIsFinishedTrue();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tasksFound);
    }

    public ResponseEntity<List<Task>> findByIsNotFinished() {

        List<Task> tasksFound = this.repository.findByIsFinishedFalse();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tasksFound);
    }

    public ResponseEntity<Task> create(Task task) {

        Task savedTask = this.repository.save(task);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedTask);
    }

    public ResponseEntity<Task> update(Task task) {
        
        Task taskFound = verifyById(task.getId());

        taskFound.setTitle(task.getTitle());
        taskFound.setDescription(task.getDescription());
        taskFound.setModifiedAt();

        Task taskSaved = this.repository.save(taskFound);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskSaved);
    }

    public ResponseEntity<String> delete(long id) {

        Task taskFound = this.verifyById(id);

        repository.delete(taskFound);

        String bodyMessage = String.format("Task with id %d deleted successfully.", id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bodyMessage);
    }

    public ResponseEntity<String> finishTask(Long id) {

        Task taskFound = this.verifyById(id);
        
        taskFound.finishTask();

        repository.save(taskFound);

        String bodyMessage = "Task was successfully marked as completed.";

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(bodyMessage);
    }
}
