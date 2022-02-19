package com.joaovitor.todo.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TaskNotValidException extends RuntimeException{

    BindingResult result;
    
    public TaskNotValidException(){}

    public TaskNotValidException(String message) {
        super(message);
    }

    public TaskNotValidException(BindingResult result) {
        this.result = result;
    }

    public List<FieldError> getBindingResults(){
        return this.result.getFieldErrors();
    }
}
