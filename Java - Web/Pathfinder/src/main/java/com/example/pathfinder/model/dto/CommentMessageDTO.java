package com.example.pathfinder.model.dto;

public class CommentMessageDTO {
    private String message;

    public CommentMessageDTO() {
    }

    public CommentMessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public CommentMessageDTO setMessage(String message) {
        this.message = message;
        return this;
    }
}
