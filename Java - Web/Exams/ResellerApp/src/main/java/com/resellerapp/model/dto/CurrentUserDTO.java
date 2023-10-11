package com.resellerapp.model.dto;

public class CurrentUserDTO {

    private String username;

    public CurrentUserDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
