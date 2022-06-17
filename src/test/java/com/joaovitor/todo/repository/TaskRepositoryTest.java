package com.joaovitor.todo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.joaovitor.todo.model.Task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class TaskRepositoryTest {

    @Mock
    private TaskRepository repository;

    private String taskTitle = "My title";
    private String taskDescription = "My description";

    private Task createNewTask() {
        return new Task(
            taskTitle,
            taskDescription
        );
    }
   

    @Test // Finished
    void whenSaveThenShouldBeCreated() {

        
        List<Task> tasksList = new ArrayList<>() {{
            add(createNewTask());
            add(createNewTask());
            add(createNewTask());
            add(createNewTask());
        }};
            
        when(repository.saveAll(tasksList))
            .thenReturn(tasksList);
            
        List<Task> savedTasks = this.repository.saveAll(tasksList);

        savedTasks.stream().forEach( task -> {

            assertThat(task).isNotNull();

            assertThat(task).isEqualTo(task);

            assertThat(task.getTitle()).isEqualTo(taskTitle);

            assertThat(task.getDescription()).isEqualTo(taskDescription);

            assertThat(task.isFinished()).isFalse();

            assertThat(task.getCreatedAt()).isEqualTo(task.getCreatedAt());

            assertThat(task.getModifiedAt()).isNull();

            assertThat(task.getFinishedAt()).isNull();

        });

    }

    @Test
    void testFindByIsNotFinished() {

        when(repository.findByIsFinishedFalse())
            .thenReturn(
                new ArrayList<>() {
                    {
                        add(createNewTask());
                        add(createNewTask());
                        add(createNewTask());
                        add(createNewTask());
                    }
                });

        List<Task> notFinishedTasks = repository.findByIsFinishedFalse();

        notFinishedTasks.stream()
            .forEach( task -> 
                assertThat( task.isFinished() ).isFalse());
    }

    @Test
    void testFindByIsFinished() {

        List<Task> tasksList = new ArrayList<>(
            Arrays.asList(
                createNewTask(),
                createNewTask(), 
                createNewTask(),
                createNewTask()));
        
        tasksList.forEach(task -> task.finishTask());

        when(repository.findByIsFinishedTrue())
            .thenReturn(
                new ArrayList<>() {
                    {
                        addAll(tasksList);
                    }
                });

        List<Task> finishedTasks = repository.findByIsFinishedTrue();

        finishedTasks.stream()
            .forEach( task -> 
                assertThat( task.isFinished() ).isTrue());
    }

    @Test
    void testDeleteThenShouldBeDeleted() {

    }
}
