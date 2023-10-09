package com.plannerapp.model.entity.dto;

import com.plannerapp.model.entity.entity.Priority;
import com.plannerapp.model.entity.enums.PriorityName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class AddTaskDTO {

    private Long id;

    @Size(min = 3, max = 20, message = "Description length must be between 2 and 20 characters")
    @NotNull
    private String description;

    @Future(message = "The dueDate must be a positive in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "The dueDate must not be null")
    private LocalDate dueDate;

    @NotNull(message = "You must select a priority")
    private PriorityName priority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public PriorityName getPriority() {
        return priority;
    }

    public void setPriority(PriorityName priority) {
        this.priority = priority;
    }
}
