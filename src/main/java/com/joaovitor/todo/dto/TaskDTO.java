package com.joaovitor.todo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class TaskDTO {

    @NotBlank(message = "Title must not be blank.")
    @NotNull(message = "Title must not be null.")
    @Length(max = 30, message = "Title cannot be over 30 characters.")
    private String title;

    private String description;

    public TaskDTO() {
    }

    public TaskDTO(String title) {
        this.title = title;
    }

    public TaskDTO(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
