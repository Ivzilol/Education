package zoo.entities.animals;

import java.util.ArrayList;
import java.util.Collection;

import static zoo.common.ExceptionMessages.*;

public abstract class BaseAnimal implements Animal{

    private String name;

    private String kind;

    private double kg;

    private double price;

    public BaseAnimal(String name, String kind, double price) {
       this.setName(name);
       this.setKind(kind);
       this.setPrice(price);
    }

    private void setName(String name) {
        if (ifNullOrEmptyString(name)) {
            throw new NullPointerException(ANIMAL_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    private void setKind(String kind) {
        if (ifNullOrEmptyString(kind)) {
            throw new NullPointerException(ANIMAL_KIND_NULL_OR_EMPTY);
        }
        this.kind = kind;
    }

    public void setKg(double kg) {
        this.kg = kg;
    }

    private void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException(ANIMAL_PRICE_BELOW_OR_EQUAL_ZERO);
        }

        this.price = price;
    }

    private boolean ifNullOrEmptyString(String name) {
        return name == null || name.trim().isEmpty();
    }

    @Override
    public String getName() {
        return name;
    }

    public String getKind() {
        return kind;
    }

    @Override
    public double getKg() {
        return kg;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void eat() {

    }
}
