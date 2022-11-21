package com.example.pathfinder.model.views;

public class RouteIndexView {

    private Long id;
    private String name;
    private String description;
    private String thumbnailUrl;

    public RouteIndexView(Long id, String name, String description, String thumbnailUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
    }

    public Long getId() {
        return id;
    }

    public RouteIndexView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteIndexView setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteIndexView setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public RouteIndexView setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
        return this;
    }
}
