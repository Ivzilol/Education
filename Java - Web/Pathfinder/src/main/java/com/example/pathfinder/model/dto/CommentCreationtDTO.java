package com.example.pathfinder.model.dto;

public class CommentCreationtDTO {

     private String userName;
     private Long routeId;
     private String message;

    public CommentCreationtDTO(String userName, Long routeId, String message) {
        this.userName = userName;
        this.routeId = routeId;
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public CommentCreationtDTO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public Long getRouteId() {
        return routeId;
    }

    public CommentCreationtDTO setRouteId(Long routeId) {
        this.routeId = routeId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentCreationtDTO setMessage(String message) {
        this.message = message;
        return this;
    }
}
