package com.joaovitor.todo.utils;

import com.joaovitor.todo.dto.TaskDTO;
import com.joaovitor.todo.model.Task;

public class TaskUtil {

    public static Task toEntity(TaskDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        return task;
    }

    public static TaskDTO toDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        return dto;
    }

}
