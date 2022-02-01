package com.joaovitor.todo;

import com.joaovitor.todo.dto.TaskDTO;
import com.joaovitor.todo.repository.TaskRepository;
import com.joaovitor.todo.utils.TaskUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DummyData implements CommandLineRunner {

    @Autowired
    private TaskRepository repository;

    @Override
    public void run(String... args) {
        TaskDTO task1 = new TaskDTO();
        task1.setTitle("Título task 1");
        task1.setDescription("Minha descrição task 1");
        TaskDTO task2 = new TaskDTO();
        task2.setTitle("Título task 2");
        task2.setDescription("Minha descrição task 2");
        TaskDTO task3 = new TaskDTO();
        task3.setTitle("Título task 3");
        task3.setDescription("Minha descrição task 3");

        repository.save(TaskUtil.toEntity(task1));
        repository.save(TaskUtil.toEntity(task2));
        repository.save(TaskUtil.toEntity(task3));
    }

}
