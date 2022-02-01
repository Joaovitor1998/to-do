package com.joaovitor.todo.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaovitor.todo.model.Task;
import com.joaovitor.todo.service.TaskService;

@WebMvcTest
@Disabled
public class TaskControllerTest {

        // @Autowired
        // MockMvc mockMvc;

        // @MockBean
        // private TaskService service;

        // private final static String DEFAULT_URL = "/api/v1/tasks";
        // private final static String DONE_LIST = "/done";
        // private final static String NOT_DONE_LIST = "/not-done";
        // private final static String SAVE = "/new";

        // @Test
        // @Disabled
        // void testCreateNewTask() throws Exception {
        // // Given
        // Task expectedTask = new Task();
        // expectedTask.setTitle("My title");
        // expectedTask.setDescription("My description");

        // // When
        // when(service.save(expectedTask)).thenReturn(expectedTask);

        // // Then
        // mockMvc.perform(
        // post(DEFAULT_URL + SAVE)
        // .contentType(MediaType.APPLICATION_JSON)
        // .content(new ObjectMapper().writeValueAsString(expectedTask)))
        // .andExpect(
        // status().isOk());
        // }

        // @Test
        // @DisplayName("Should return a list of tasks")
        // void shouldReturnListOfTasks() throws Exception {
        // // Given
        // Task expectedTask = new Task();
        // expectedTask.setTitle("My title");
        // expectedTask.setDescription("My description");

        // // When
        // when(service.findAll()).thenReturn(Collections.singletonList(expectedTask));

        // // Then
        // mockMvc.perform(
        // get(DEFAULT_URL))
        // .andExpect(
        // status().isOk())
        // .andExpect(

        // jsonPath("$[0].title").value("My title"))
        // .andExpect(
        // jsonPath("$[0].description").value("My description"));
        // }

        // @Test
        // @DisplayName("Should return a list of done tasks")
        // void shouldReturnListOfDoneTasks() throws Exception {
        // // Given
        // Task expectedTask = new Task();
        // expectedTask.toggleIsDone();
        // // When
        // when(service.findByIsDone()).thenReturn(Collections.singletonList(expectedTask));
        // // Then
        // mockMvc.perform(
        // get(DEFAULT_URL + DONE_LIST)).andExpect(
        // status().isOk())
        // .andExpect(
        // jsonPath("$[0].isDone").value(true));
        // }

        // @Test
        // @DisplayName("Should return a list of not done tasks")
        // void shouldReturnListOfNotDoneTasks() throws Exception {
        // // Given
        // Task expectedTask = new Task();
        // // When
        // when(service.findByNotDone()).thenReturn(Collections.singletonList(expectedTask));
        // // Then
        // mockMvc.perform(
        // get(DEFAULT_URL + NOT_DONE_LIST)).andExpect(
        // status().isOk())
        // .andExpect(
        // jsonPath("$[0].isDone").value(false));
        // }
}
