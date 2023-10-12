package com.likebookapp.model.dto;

import com.likebookapp.model.entity.Mood;

public class PostOtherUsersDTO {

    private Long id;

    private String username;

    private String content;

    private Mood mood;

    private Integer numberLikes;

    public PostOtherUsersDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public Integer getNumberLikes() {
        return numberLikes;
    }

    public void setNumberLikes(Integer numberLikes) {
        this.numberLikes = numberLikes;
    }
}
