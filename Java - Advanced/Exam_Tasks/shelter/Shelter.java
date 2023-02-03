package ExamPreparetion_01.shelter;

import java.util.ArrayList;
import java.util.List;

public class Shelter {

    private final List<Animal> data;
    private final int capacity;

    public Shelter(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add(Animal animal) {
        if (this.data.size() < this.capacity) {
            this.data.add(animal);
        }
    }

    public boolean remove(String name) {
        for (Animal animal : this.data) {
            if (animal.getName().equals(name)) {
                return this.data.remove(animal);
            }
        }
        return false;
    }

    public Animal getAnimal(String name, String caretaker) {
        for (Animal animal : this.data) {
            if (animal.getName().equals(name) && animal.getCaretaker().equals(caretaker)) {
                return animal;
            }
        }
        return null;
    }

    public Animal getOldestAnimal() {
        Animal animal = null;
        int oldestAnimal = Integer.MIN_VALUE;
        for (Animal currentAnimal : this.data) {
            if (currentAnimal.getAge() > oldestAnimal) {
                oldestAnimal = currentAnimal.getAge();
                animal = currentAnimal;
            }
        }
        return animal;
    }

    public int getCount() {
        return this.data.size();
    }

    public String getStatistics() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The ExamPreparetion_01.shelter has the following animals:\n");
        for (Animal animal : this.data) {
            stringBuilder.append(animal.getName())
                    .append(" ")
                    .append(animal.getCaretaker())
                    .append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }
}
