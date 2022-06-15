package com.joaovitor.todo.repository;

import java.util.List;

import com.joaovitor.todo.model.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByIsFinishedTrue();

    List<Task> findByIsFinishedFalse();
}
