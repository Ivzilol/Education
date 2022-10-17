package Exercises_03.restaurant;

import java.math.BigDecimal;

public class Dessert extends Food{

    private double calories;

    public Dessert(String name, BigDecimal price, double grams, double calories) {
        super(name, price, grams);
        this.calories = calories;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }
}
