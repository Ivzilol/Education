package zoo.entities.areas;

import zoo.common.ExceptionMessages;
import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static zoo.common.ExceptionMessages.AREA_NAME_NULL_OR_EMPTY;
import static zoo.common.ExceptionMessages.NOT_ENOUGH_CAPACITY;

public abstract class BaseArea implements Area {


    private String name;

    private int capacity;

    private Collection<Food> foods;

    private Collection<Animal> animals;

    public BaseArea(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.foods = new ArrayList<>();
        this.animals = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(AREA_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<Animal> getAnimals() {
        return this.animals;
    }

    @Override
    public Collection<Food> getFoods() {
        return this.foods;
    }

    @Override
    public int sumCalories() {
        int sumCalories = 0;
        for (Food food : this.foods) {
            sumCalories += food.getCalories();
        }
        return sumCalories;
    }

    @Override
    public void addAnimal(Animal animal) {
        if (animal.getKg() < capacity) {
            this.animals.add(animal);
        } else {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }

    }

    @Override
    public void removeAnimal(Animal animal) {
        this.animals.remove(animal);
    }

    @Override
    public void addFood(Food food) {
        this.foods.add(food);
    }

    @Override
    public void feed() {
        for (Animal animal : this.animals) {
            animal.eat();
        }
    }

    @Override
    public String getInfo() {
        return toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s (%s):", this.name, this.getClass().getSimpleName()));
        sb.append(System.lineSeparator());
        sb.append("Animals: ");
        if (this.animals.isEmpty()) {
            sb.append("none");
        } else {
            sb.append(this.animals
                    .stream()
                    .map(Animal::getName)
                    .collect(Collectors.joining(" ")));
        }
        sb.append(System.lineSeparator());
        sb.append("Foods: ");
        sb.append(this.foods.size());
        sb.append(System.lineSeparator());
        sb.append("Calories: ");
        sb.append(this.sumCalories());
        return sb.toString().trim();
    }
}
