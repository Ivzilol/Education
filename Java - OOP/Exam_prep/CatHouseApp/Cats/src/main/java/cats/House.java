package cats;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class House {
    private String name;
    private int capacity;
    private Collection<Cat> cat;

    public House(String name, int capacity){
        this.setCapacity(capacity);
        this.setName(name);
        this.cat = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException("Invalid name!");
        }
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    private void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Invalid capacity!");
        }
        this.capacity = capacity;
    }

    public int getCount() {
        return this.cat.size();
    }

    public void addCat(Cat cat){
        if (this.cat.size() == this.capacity){
            throw new IllegalArgumentException("House is full!");
        }
        this.cat.add(cat);
    }

    public void removeCat(String name) {
        Cat catToRemove = this.cat.stream()
                .filter(f -> name.equals(f.getName()))
                .findAny()
                .orElse(null);
        if (catToRemove == null){
            throw new IllegalArgumentException(String.format("Cat with name %s doesn't exist!", name));
        }
        this.cat.remove(catToRemove);
    }

    public Cat catForSale(String name){
        Cat cat = this.cat.stream()
                .filter(f -> name.equals(f.getName()))
                .findAny()
                .orElse(null);
        if (cat == null){
            throw new IllegalArgumentException(String.format("Cat with name %s doesn't exist!", name));
        }
        cat.setHungry(false);

        return cat;
    }

    public String statistics(){
        String names = this.cat.stream().map(Cat::getName).collect(Collectors.joining(", "));
        return String.format("The cat %s is in the house %s!", names, this.name);
    }

}
