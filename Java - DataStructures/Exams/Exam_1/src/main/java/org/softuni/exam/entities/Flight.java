package org.softuni.exam.entities;

public class Flight {
    private String id;

    private String number;

    private String origin;

    private String destination;

    private boolean isCompleted;

    public Flight(String id, String number, String origin, String destination, boolean isCompleted) {
        this.id = id;
        this.number = number;
        this.origin = origin;
        this.destination = destination;
        this.isCompleted = isCompleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
