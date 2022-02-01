package com.joaovitor.todo.dto;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("TaskDTO validation tests")
public class TaskDTOTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private String blankMessage = "must not be blank";
    private String nullMessage = "must not be null";
    private String lenghtyTitleMessage = "Title cannot be over 30 characters";

    private void checkExceptionMessage(Set<ConstraintViolation<TaskDTO>> violations, String... messages) {

        Iterator<ConstraintViolation<TaskDTO>> iterator = violations.iterator();

        while (iterator.hasNext()) {
            ConstraintViolation<TaskDTO> next = iterator.next();
            next.getMessage();
            assertThat(next.getMessage()).containsAnyOf(messages);
        }
    }

    @Test
    @DisplayName("Title should not be null")
    void shouldNotBeValidWhenTitleIsNull() {
        TaskDTO dto = new TaskDTO(null, "My description");

        Set<ConstraintViolation<TaskDTO>> violations = validator.validate(dto);

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(2);

        checkExceptionMessage(violations, blankMessage, nullMessage);

    }

    @Test
    @DisplayName("Title should not be blank")
    void shouldNotBeValidWhenTitleIsBlank() {
        TaskDTO dto = new TaskDTO("", "My description");

        Set<ConstraintViolation<TaskDTO>> violations = validator.validate(dto);

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(1);

        checkExceptionMessage(violations, blankMessage);

    }

    @Test
    @DisplayName("Title should not be over 30 characters")
    void shouldNotBeValidWhenTitleExceedMaxLength() {
        TaskDTO dto = new TaskDTO("This TaskDTO should be over 30 characters", "My description");

        Set<ConstraintViolation<TaskDTO>> violations = validator.validate(dto);

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(1);

        checkExceptionMessage(violations, lenghtyTitleMessage);
    }

    @Test
    @DisplayName("Description should not be null")
    void shouldNotBeValidWhenDescriptionIsNull() {
        TaskDTO dto = new TaskDTO("My title", null);

        Set<ConstraintViolation<TaskDTO>> violations = validator.validate(dto);

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(2);

        checkExceptionMessage(violations, blankMessage, nullMessage);
    }

    @Test
    @DisplayName("Description should not be blank")
    void shouldNotBeValidWhenDescriptionIsBlank() {
        TaskDTO dto = new TaskDTO("My title", "");

        Set<ConstraintViolation<TaskDTO>> violations = validator.validate(dto);

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(1);

        checkExceptionMessage(violations, blankMessage);
    }

    @Test
    @DisplayName("TaskDTO should be successfully created")
    void shouldSuccessWhenCreatedCorrectly() {
        TaskDTO dto = new TaskDTO("My title", "My description");

        Set<ConstraintViolation<TaskDTO>> violations = validator.validate(dto);

        assertThat(violations.isEmpty()).isTrue();
    }
}
