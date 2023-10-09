package com.plannerapp.model.entity.dto;

import com.plannerapp.model.entity.entity.Priority;

import java.time.LocalDate;

public class UserTasksDTO {

    private Long id;

    private Priority priority;

    private LocalDate dueDate;

    private String description;

    public UserTasksDTO() {
    }

    public UserTasksDTO(Long id, Priority priority, LocalDate dueDate, String description) {
        this.id = id;
        this.priority = priority;
        this.dueDate = dueDate;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
