package christmasPastryShop.entities.delicacies.interfaces;

import static christmasPastryShop.common.ExceptionMessages.*;

public abstract class BaseDelicacy implements Delicacy {

    private String name;

    private double portion;

    private double price;

    public BaseDelicacy(String name, double portion, double price) {
        setName(name);
        setPortion(portion);
        setPrice(price);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPortion() {
        return portion;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME);
        } else {
            this.name = name;
        }
    }

    public void setPortion(double portion) {
        if (portion <= 0) {
            throw new IllegalArgumentException(INVALID_PORTION);
        } else {
            this.portion = portion;
        }
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException(INVALID_PRICE);
        } else {
            this.price = price;
        }
    }

    @Override
    public String toString() {
        return String.format("%s: %.2fg - %.2f", name, portion, price);
    }
}
