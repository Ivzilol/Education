package com.example.spotifyplaylistapp.model.entity.dto;

import com.example.spotifyplaylistapp.model.entity.enums.StyleName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class AddSongDTO {

    private Long id;

    @Size(min = 3, max = 20, message = "Performer name length must be between 3 and 20 characters")
    @NotNull
    private String performer;
    @Size(min = 3, max = 20, message = "Title name length must be between 2 and 20 characters")
    @NotNull
    private String title;

    @NotNull
    @PastOrPresent(message = "The Date cannot be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @Positive(message = "Duration must be positive")
    @NotNull
    private Integer duration;
    @NotNull(message = "You must select a style")
    private StyleName style;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public StyleName getStyle() {
        return style;
    }

    public void setStyle(StyleName style) {
        this.style = style;
    }
}
