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
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repository.findAll());
    }

    public ResponseEntity<List<Task>> findByIsDoneTrue() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repository.findByIsDoneTrue());
    }

    public ResponseEntity<List<Task>> findByIsDoneFalse() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repository.findByIsDoneFalse());
    }

    public ResponseEntity<Task> create(Task task) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.repository.save(task));
    }

    public ResponseEntity<Task> update(Task task) {
        Task taskFound = verifyById(task.getId());

        taskFound.setTitle(task.getTitle());
        taskFound.setDescription(task.getDescription());
        taskFound.setModifiedAt();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repository.save(taskFound));
    }

    public ResponseEntity<String> delete(long id) {
        Task taskFound = this.verifyById(id);
        repository.delete(taskFound);
        String bodyMessage = String.format("Task with id %d deleted successfully.", id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bodyMessage);
    }

    public ResponseEntity<String> toggleIsDoneStatus(Task task) {
        Task taskFound = this.verifyById(task.getId());
        taskFound.toggleIsDone();
        repository.save(taskFound);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Task was successfully marked as completed.");
    }
}
