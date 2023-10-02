package com.likebookapp.model.entity.entity;

import com.likebookapp.model.entity.enums.MoodsEnum;

import javax.persistence.*;

@Entity
public class Mood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MoodsEnum moodsName;

    private String description;

    public Mood() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MoodsEnum getMoodsName() {
        return moodsName;
    }

    public void setMoodsName(MoodsEnum moodsEnum) {
        this.moodsName = moodsEnum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
