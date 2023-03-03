package christmasPastryShop.entities.cocktails.interfaces;

import static christmasPastryShop.common.ExceptionMessages.*;

public abstract class BaseCocktail implements Cocktail{

    private String name;

    private int size;

    private double price;

    private String brand;

    public BaseCocktail(String name, int size, double price, String brand) {
        setName(name);
        setSize(size);
        setPrice(price);
        setBrand(brand);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME);
        } else {
            this.name = name;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException(INVALID_SIZE);
        } else {
            this.size = size;
        }
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException(INVALID_PRICE);
        } else {
            this.price = price;
        }
    }

    @Override
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_BRAND);
        } else {
            this.brand = brand;
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s - %d - %.2flv", name, brand, size, price);
    }
}
