package zoo.entities.animals;

import static zoo.common.ExceptionMessages.*;

public abstract class BaseAnimal implements Animal {

    private String name;

    private String kind;

    private double kg;

    private double price;

    public BaseAnimal(String name, String kind, double kg, double price) {
        setName(name);
        setKind(kind);
        this.kg = kg;
        setPrice(price);
    }

    public void setKg(double kg) {
        this.kg = kg;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getKg() {
        return this.kg;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    public String getKind() {
        return kind;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ANIMAL_NAME_NULL_OR_EMPTY);
        } else {
            this.name = name;
        }
    }

    public void setKind(String kind) {
        if (kind == null || kind.trim().isEmpty()) {
            throw new NullPointerException(ANIMAL_KIND_NULL_OR_EMPTY);
        } else {
            this.kind = kind;
        }
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalStateException(ANIMAL_PRICE_BELOW_OR_EQUAL_ZERO);
        } else {
            this.price = price;
        }
    }

    @Override
    public void eat() {

    }
}
