package com.example.BattleShips.model.dto;

public class ShipsDTO {

    private Long id;

    private String name;

    private Integer health;

    private Integer power;

    public ShipsDTO(Long id, String name, Integer health, Integer power) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.power = power;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }
}
