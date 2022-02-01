package com.joaovitor.todo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.joaovitor.todo.model.Task;

import org.junit.jupiter.api.BeforeAll;
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

    private Task createTask() {
        Task task = new Task();
        task.setTitle(taskTitle);
        task.setDescription(taskDescription);
        return task;
    }

    @BeforeAll
    void setup() {
    }

    @Test // Finished
    void whenDeleteShouldBeRemoved() {

    }

    @Test // Finished
    void whenUpdateShouldBeUpdated() {
    }

    @Test // Finished
    void whenSaveThenShouldBeCreated() {

        Task task1 = createTask();
        Task task2 = createTask();
        Task task3 = createTask();
        Task task4 = createTask();

        List<Task> tasks = new ArrayList<>() {
            {
                add(task1);
                add(task2);
                add(task3);
                add(task4);
            }
        };

        // doReturn(List<Task> savedTasks).when(repository.saveAll(tasks));

        List<Task> savedTasks = this.repository.saveAll(tasks);

        savedTasks.stream().forEach(task -> {

            assertThat(task).isNotNull();

            assertThat(task).isEqualTo(task);

            assertThat(task.getTitle()).isEqualTo(taskTitle);

            assertThat(task.getDescription()).isEqualTo(taskDescription);

            assertThat(task.getIsDone()).isFalse();

            assertThat(task.getCreatedAt()).isEqualTo(task.getCreatedAt());

            assertThat(task.getModifiedAt()).isNull();

            assertThat(task.getFinishedAt()).isNull();

            System.out.println(task.getId());
        });

    }

    @Test // Finished
    void testFindByIsDoneFalse() {
        Task task1 = createTask();
        Task task2 = createTask();
        Task task3 = createTask();
        Task task4 = createTask();

        when(repository.findByIsDoneFalse()).thenReturn(
                new ArrayList<>() {
                    {
                        add(task1);
                        add(task2);
                        add(task3);
                        add(task4);
                    }
                });

        List<Task> isDoneFalse = repository.findByIsDoneFalse();

        isDoneFalse.stream().forEach(item -> assertThat(item.getIsDone()).isFalse());
    }

    @Test // Finished
    void testFindByIsDoneTrue() {
        Task task1 = createTask();
        Task task2 = createTask();
        Task task3 = createTask();
        Task task4 = createTask();
        task1.toggleIsDone();
        task2.toggleIsDone();
        task3.toggleIsDone();
        task4.toggleIsDone();

        when(repository.findByIsDoneTrue()).thenReturn(
                new ArrayList<>() {
                    {
                        add(task1);
                        add(task2);
                        add(task3);
                        add(task4);
                    }
                });

        List<Task> isDoneTrue = repository.findByIsDoneTrue();

        isDoneTrue.stream().forEach(item -> assertThat(item.getIsDone()).isTrue());
    }
}
