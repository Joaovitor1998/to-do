package com.joaovitor.todo.service;

import java.util.List;
import java.util.Optional;

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

    private Task findById(long id) {
        Optional<Task> taskFound = repository.findById(id);
        if (taskFound.isEmpty()) {
            return null;
        }
        return taskFound.get();
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
        Task taskFound = findById(task.getId());

        taskFound.setTitle(task.getTitle());
        taskFound.setDescription(task.getDescription());
        taskFound.setModifiedAt();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repository.save(taskFound));
    }

    public ResponseEntity<String> delete(long id) {
        Task taskFound = this.findById(id);
        repository.delete(taskFound);
        String headerValue = "Task with id " + id + " deleted successfully.";
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Task-Deleted", headerValue)
                .body("Task deleted successfully.");
    }

    public void toggleIsDoneStatus(Task task) {
        Task taskFound = this.findById(task.getId());
        taskFound.toggleIsDone();
        repository.save(taskFound);
    }
}
