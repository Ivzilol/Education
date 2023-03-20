package farmville;

import java.util.ArrayList;
import java.util.Collection;

public class Farm {
    private static final String INVALID_FARM_NAME = "Invalid farm name!";
    private static final String INVALID_CAPACITY = "Invalid capacity!";
    private static final String FARM_IS_FULL = "Farm is full!";
    private static final String ANIMAL_EXIST = "Animal %s is already in!";
    private static final int ZERO_CAPACITY = 0;

    private int capacity;
    private String name;
    private Collection<Animal> animals;

    public Farm(String name, int capacity) {
        this.setName(name);
        this.setCapacity(capacity);
        this.animals = new ArrayList<>();
    }

    public int getCount() {
        return this.animals.size();
    }

    public String getName() {
        return this.name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void add(Animal animal) {
        if (animals.size() == this.getCapacity()) {
            throw new IllegalArgumentException(FARM_IS_FULL);
        }

        boolean animalExists = this.animals
                .stream()
                .anyMatch(a -> a.getType().equals(animal.getType()));

        if (animalExists) {
            throw new IllegalArgumentException(String.format(ANIMAL_EXIST, animal.getType()));
        }

        this.animals.add(animal);
    }

    public boolean remove(String animalType) {
        Animal animal = this.animals
                .stream()
                .filter(a -> a.getType().equals(animalType))
                .findFirst()
                .orElse(null);

        boolean isRemove = this.animals.remove(animal);
        return isRemove;
    }

    private void setCapacity(int capacity) {
        if (capacity < ZERO_CAPACITY) {
            throw new IllegalArgumentException(INVALID_CAPACITY);
        }

        this.capacity = capacity;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(INVALID_FARM_NAME);
        }

        this.name = name;
    }
}
