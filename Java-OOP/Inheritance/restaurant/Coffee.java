package Exercises_03.restaurant;

import java.math.BigDecimal;

public class Coffee extends HotBeverage{

    private double caffeine;
    private static final double COFFEE_MILLILITERS = 50;
    private static final BigDecimal COFFEE_PRICE = new BigDecimal(3.50);

    public Coffee(String name, double caffeine) {
        super(name, COFFEE_PRICE, COFFEE_MILLILITERS);
        this.caffeine = caffeine;
    }

    public double getCaffeine() {
        return caffeine;
    }

    public void setCaffeine(double caffeine) {
        this.caffeine = caffeine;
    }
}
