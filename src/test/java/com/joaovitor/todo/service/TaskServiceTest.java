package com.joaovitor.todo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.joaovitor.todo.model.Task;
import com.joaovitor.todo.repository.TaskRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class TaskServiceTest {

    @InjectMocks
    private TaskService service;

    @Mock
    private TaskRepository repository;

    @Test
    @DisplayName("Should return a list of Tasks / findAll() ")
    void shouldReturnListWhenSuccessful() {
        // Given
        Task expectedTask = createNewTask();

        // When
        Mockito.when(repository.findAll())
            .thenReturn(
                    Collections.singletonList(expectedTask));

        // Then
        List<Task> retrievedList = service.findAll().getBody();

        Assertions.assertThat(retrievedList.get(0))
            .isEqualTo(expectedTask);

    }

    @Test
    @DisplayName("Should return a list of finished tasks / findByIsFinished()")
    void shouldReturnListOfFinishedTasks() {
        // Given
        Task expectedTask = createNewTask();
        expectedTask.finishTask();

        // When
        Mockito.when(repository.findByIsFinishedTrue())
            .thenReturn(
                Collections.singletonList(expectedTask));

        // Then
        List<Task> retrievedList = service.findByIsFinished().getBody();

        Assertions.assertThat(retrievedList.get(0))
            .isEqualTo(expectedTask);
        
        Assertions.assertThat(retrievedList.get(0)
            .isFinished())
            .isTrue();
    }

    @Test
    @DisplayName("Should return a list of unfinished tasks / findByIsNotFinished())")
    void shouldReturnListOfNotFinishedTasks() {
        // Given
        Task expectedTask = createNewTask();

        // When
        Mockito.when(repository.findByIsFinishedFalse())
            .thenReturn(
                Collections.singletonList(expectedTask));

        // Then
        List<Task> retrievedList = service.findByIsNotFinished().getBody();

        Assertions.assertThat(retrievedList.get(0))
            .isEqualTo(expectedTask);
        
        Assertions.assertThat(retrievedList.get(0)
            .isFinished())
            .isFalse();
    }

    @Test
    @DisplayName("Should save task when requested / save()")
    void shouldSaveTaskWhenRequested() {

        // Given
        Task expectedTask = createNewTask();

        // When
        when(
            repository.save(
                any(Task.class))
            ).thenReturn(expectedTask);

        // Then
        Task savedTask = service.create(expectedTask).getBody();

        Assertions.assertThat(savedTask.getTitle())
            .isEqualTo(expectedTask.getTitle());
        
        Assertions.assertThat(savedTask.getDescription())
            .isEqualTo(expectedTask.getDescription());
    }

    @Test
    @DisplayName("Should update task when requested / update()")
    void shouldUpdateTaskWhenRequested() {
        // Given
        Task originalTask = createNewTask();

        Task expectedTask = new Task(
            "My updated title",
            "My updated description"
        );
        expectedTask.setModifiedAt();

        // When
        when(
            repository.findById(any())
            ).thenReturn(Optional.of(originalTask));

        when(
            repository.save(any(Task.class))
            ).thenReturn(expectedTask);

        // Then
        Task updatedTask = service.update(expectedTask).getBody();

        assertThat(updatedTask.getTitle()).isEqualTo(expectedTask.getTitle());

        assertThat(updatedTask.getDescription()).isEqualTo(expectedTask.getDescription());

        assertThat(updatedTask.getModifiedAt()).isEqualTo(expectedTask.getModifiedAt());

        assertThat(updatedTask.getCreatedAt()).isEqualTo(expectedTask.getCreatedAt());

    }

    @Test
    @DisplayName("Should delete task when requested / delete()")
    void shouldDeleteTaskWhenRequested() {
        // Given
        Task task = createNewTask();

        // When
        when(
            repository.findById(any())
            ).thenReturn(Optional.of(task));

        doNothing().when(repository).delete(task);

        // Then
        service.delete(1L);
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).delete(task);

    }

    @Test
    @DisplayName("Should mark task as finished / finishTask()")
    void shouldMarkTaskAsFinishedWhenRequested() {
        // Given
        Task task = createNewTask();

        // When
        when(
            repository.findById(1L)
            ).thenReturn(Optional.of(task));
        
        when(
            repository.save(any())
            ).thenReturn(any(Task.class));

        // Then
        service.finishTask(1L);

        verify(repository, times(1))
            .findById(1L);

        verify(repository, times(1))
            .save(any(Task.class));
    }

    private Task createNewTask(){
        return new Task(
            "My title",
            "My description");
    }
}
