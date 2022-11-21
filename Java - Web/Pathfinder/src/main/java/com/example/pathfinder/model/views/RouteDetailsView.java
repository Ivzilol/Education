package com.example.pathfinder.model.views;

import java.util.List;
import java.util.Set;

public class RouteDetailsView {

    private Long id;
    private String jpxCoordinates;
    private String level;
    private String name;
    private String description;
    private String authorName;
    private String videoUrl;
    private List<String> picturesUrls;

    public RouteDetailsView(Long id, String jpxCoordinates, String level, String name, String description,
                            String authorName, String videoUrl, List<String> picturesUrls) {
        this.id = id;
        this.jpxCoordinates = jpxCoordinates;
        this.level = level;
        this.name = name;
        this.description = description;
        this.authorName = authorName;
        this.videoUrl = videoUrl;
        this.picturesUrls = picturesUrls;
    }

    public Long getId() {
        return id;
    }

    public RouteDetailsView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getJpxCoordinates() {
        return jpxCoordinates;
    }

    public RouteDetailsView setJpxCoordinates(String jpxCoordinates) {
        this.jpxCoordinates = jpxCoordinates;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public RouteDetailsView setLevel(String level) {
        this.level = level;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteDetailsView setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteDetailsView setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public RouteDetailsView setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public RouteDetailsView setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public List<String> getPicturesUrls() {
        return picturesUrls;
    }

    public RouteDetailsView setPicturesUrls(List<String> picturesUrls) {
        this.picturesUrls = picturesUrls;
        return this;
    }
}
