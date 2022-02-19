package com.joaovitor.todo.exceptions;


import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> taskNotFoundException(TaskNotFoundException exception){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode errorBody = objectMapper.createObjectNode();
        errorBody.put("message", "Task could not be found");
        String body = errorBody.toString();

        return new ResponseEntity<String>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskNotValidException.class)
    public ResponseEntity<String> taskNotFoundException(TaskNotValidException exception){
        List<FieldError> results = exception.getBindingResults();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode errorBody = objectMapper.createObjectNode();
        errorBody.put("message", "Task could not be created");
        errorBody.put("advice", "Check if the fields are correct");

        ArrayNode arrayNode = errorBody.arrayNode();
        ObjectNode addObject = arrayNode.addObject();
        for(FieldError error : results){
            addObject.put(error.getField(), error.getDefaultMessage());
        }
        errorBody.set("errors", arrayNode);

        String body = errorBody.toString();

        return new ResponseEntity<String>(body, HttpStatus.BAD_REQUEST);
    }
}
