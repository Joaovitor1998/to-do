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
    @DisplayName("Should return list of Task / findAll() ")
    void shouldReturnListWhenSuccessful() {
        // Given
        Task expectedTask = new Task();
        expectedTask.setTitle("My title");
        expectedTask.setDescription("My description");

        // When
        Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(expectedTask));

        // Then
        List<Task> retrievedList = service.findAll().getBody();

        Assertions.assertThat(retrievedList.get(0)).isEqualTo(expectedTask);

    }

    @Test
    @DisplayName("Should return list of done tasks / findByIsDone()")
    void shouldReturnListOfDoneTasks() {
        // Given
        Task expectedTask = new Task();
        expectedTask.setTitle("My title");
        expectedTask.setDescription("My description");
        expectedTask.toggleIsDone();

        // When
        Mockito.when(repository.findByIsDoneTrue()).thenReturn(Collections.singletonList(expectedTask));

        // Then
        List<Task> retrievedList = service.findByIsDone().getBody();

        Assertions.assertThat(retrievedList.get(0)).isEqualTo(expectedTask);
        Assertions.assertThat(retrievedList.get(0).getIsDone()).isTrue();
    }

    @Test
    @DisplayName("Should return list of not done tasks / findByNotDone()")
    void shouldReturnListOfNotDoneTasks() {
        // Given
        Task expectedTask = new Task();
        expectedTask.setTitle("My title");
        expectedTask.setDescription("My description");

        // When
        Mockito.when(repository.findByIsDoneFalse()).thenReturn(Collections.singletonList(expectedTask));

        // Then
        List<Task> retrievedList = service.findByNotDone().getBody();

        Assertions.assertThat(retrievedList.get(0)).isEqualTo(expectedTask);
        Assertions.assertThat(retrievedList.get(0).getIsDone()).isFalse();
    }

    @Test
    @DisplayName("Should save task when requested / save()")
    void shouldSaveTaskWhenRequested() {

        // Given
        Task expectedTask = new Task();
        expectedTask.setTitle("My title");
        expectedTask.setDescription("My description");

        // When
        when(repository.save(any(Task.class))).thenReturn(expectedTask);

        // Then
        Task savedTask = service.save(expectedTask).getBody();

        Assertions.assertThat(savedTask.getTitle()).isEqualTo(expectedTask.getTitle());
        Assertions.assertThat(savedTask.getDescription()).isEqualTo(expectedTask.getDescription());
    }

    @Test
    @DisplayName("Should update task when requested / update()")
    void shouldUpdateTaskWhenRequested() {
        // Given
        Task originalTask = new Task();
        originalTask.setTitle("My title");
        originalTask.setDescription("My description");

        Task expectedTask = new Task();
        expectedTask.setTitle("My title updated");
        expectedTask.setDescription("My description updated");
        expectedTask.setModifiedAt();

        // When
        when(repository.findById(any())).thenReturn(Optional.of(originalTask));

        when(repository.save(any(Task.class))).thenReturn(expectedTask);

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
        Task expectedTask = new Task();
        expectedTask.setTitle("My title updated");
        expectedTask.setDescription("My description updated");

        // When
        when(repository.findById(any())).thenReturn(Optional.of(expectedTask));

        doNothing().when(repository).delete(expectedTask);

        // Then
        service.delete(1);
        verify(repository, times(1)).findById(any());
        verify(repository, times(1)).delete(expectedTask);

    }

    @Test
    @DisplayName("Should toggle task completness status / setIsDone()")
    void shouldToggleTaskCompletnessWhenRequested() {
        // Given
        Task expectedTask = new Task();
        expectedTask.setTitle("My title updated");
        expectedTask.setDescription("My description updated");

        // When
        when(repository.findById(any())).thenReturn(Optional.of(expectedTask));
        when(repository.save(any())).thenReturn(any(Task.class));

        // Then
        service.setIsDone(expectedTask);

        verify(repository, times(1)).findById(any());
        verify(repository, times(1)).save(any(Task.class));
    }
}
