package com.example.football.models.dto;

import com.example.football.models.entity.Position;

public class ExportBestPlayersDto {

    private String firstName;

    private String lastName;

    private Enum<Position> position;

    private String townName;

    private String stadiumName;

    public ExportBestPlayersDto(String firstName, String lastName, Enum<Position> position, String townName, String stadiumName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.townName = townName;
        this.stadiumName = stadiumName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public Enum<Position> getPosition() {
        return position;
    }

    public void setPosition(Enum<Position> position) {
        this.position = position;
    }
}
