package com.plannerapp.model.entity.entity;

import com.plannerapp.model.entity.enums.PriorityName;

import javax.persistence.*;

@Entity
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PriorityName priorityName;

    @Column(nullable = false)
    private String description;

    public Priority() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PriorityName getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(PriorityName priorityName) {
        this.priorityName = priorityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
