package catHouse.entities.houses;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.toys.Toy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseHouse implements House {

    private String name;
    private int capacity;
    private Collection<Toy> toys;
    private Collection<Cat> cats;

    public BaseHouse(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.toys = new ArrayList<>();
        this.cats = new ArrayList<>();
    }

    @Override
    public int sumSoftness() {
        int sum = 0;
        for (Toy toy : this.getToys()) {
            sum += toy.getSoftness();
        }
        return sum;
    }

    @Override
    public void addCat(Cat cat) {
        if (this.getCats().size() >= this.capacity) {
            throw new IllegalArgumentException(ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_CAT);
        } else {
            this.getCats().add(cat);
        }
    }

    @Override
    public void removeCat(Cat cat) {
        this.getCats().remove(cat);
    }

    @Override
    public void buyToy(Toy toy) {
        this.getToys().add(toy);
    }

    @Override
    public void feeding() {
        for (Cat cat : this.getCats()) {
            cat.eating();
        }
    }

    @Override
    public String getStatistics() {
        StringBuilder massage = new StringBuilder();
        massage.append(String.format("%s %s:%n", this.getName(), this.getClass().getSimpleName()));
        massage.append("Cats: ");
        if (this.getCats().isEmpty()) {
            massage.append("none");
            massage.append(System.lineSeparator());
        } else {
            massage.append(this.getCats().stream().map(Cat::getName)
                    .collect(Collectors.joining(" ")).trim());
            massage.append(System.lineSeparator());
        }
        massage.append(String.format("Toys: %d Softness: %d\n", this.getToys().size(), this.sumSoftness()));
        return massage.toString();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.HOUSE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        } else {
            this.name = name;
        }
    }

    @Override
    public Collection<Cat> getCats() {
        return this.cats;
    }

    @Override
    public Collection<Toy> getToys() {
        return this.toys;
    }

    public int getCapacity() {
        return this.cats.size();
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
