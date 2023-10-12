package com.likebookapp.model.dto;

import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.enums.MoodName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddPostDTO {

    private Long id;

    @Size(min = 2, max = 150, message = "Content length must be between 2 and 150 characters")
    @NotNull
    private String content;
    @NotNull(message = "You must select mood")
    private MoodName mood;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MoodName getMood() {
        return mood;
    }

    public void setMood(MoodName mood) {
        this.mood = mood;
    }
}
