package com.example.BattleShips.model.dto;

import com.example.BattleShips.model.enums.Name;
import com.example.BattleShips.validation.annotation.UniqueShipName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class AddShipDTO {

    @UniqueShipName
    @Size(min = 2, max = 10, message = "Ship Name must be between 2 and 10 characters")
    @NotNull
    private String name;
    @Positive(message = "Power must be positive number")
    @NotNull
    private Integer power;
    @Positive(message = "Health must be positive number")
    @NotNull
    private Integer health;
    @PastOrPresent(message = "Date can not be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate created;
    @NotNull(message = "You must select category")
    private Name category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Name getCategory() {
        return category;
    }

    public void setCategory(Name category) {
        this.category = category;
    }
}
