package com.example.ShoppingList.model.dto;

import com.example.ShoppingList.model.enums.Name;
import com.example.ShoppingList.validation.annotation.UniqueProductName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AddProductDTO {

    private Long id;
    @UniqueProductName
    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters")
    @NotNull
    private String name;
    @Size(min = 5, message = "Description min length must be minimum 5 characters")
    private String description;
    @Future(message = "Date and Time, that cannot be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull
    private LocalDateTime neededBefore;
    @Positive(message = "The price must be a positive number")
    @NotNull
    private BigDecimal price;

    @NotNull(message = "You must select style")
    private Name category;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public void setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Name getCategory() {
        return category;
    }

    public void setCategory(Name category) {
        this.category = category;
    }
}

