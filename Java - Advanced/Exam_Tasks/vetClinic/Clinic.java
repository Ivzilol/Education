package ExamPreparetion_01.vetClinic;

import java.util.ArrayList;
import java.util.Collection;

public class Clinic {

    private final Collection<Pet> data;
    private final int capacity;

    public Clinic(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add(Pet pet) {
        if (this.data.size() < capacity) {
            this.data.add(pet);
        }
    }

    public boolean remove(String name) {
        for (Pet pet : this.data) {
            if (pet.getName().equals(name)) {
                this.data.remove(pet);
                return true;
            }
        }
        return false;
    }

    public Pet getPet(String name, String owner) {
        for (Pet currentPet : this.data) {
            if (currentPet.getName().equals(name) && currentPet.getOwner().equals(owner)) {
                return currentPet;
            }
        }
        return null;
    }

    public Pet getOldestPet() {
        Pet pet = null;
        int oldestPet = Integer.MIN_VALUE;
        for (Pet currentPet : this.data) {
            if (currentPet.getAge() > oldestPet) {
                oldestPet = currentPet.getAge();
                pet = currentPet;

            }
        }
        return pet;
    }

    public int getCount() {
        return this.data.size();
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append("The clinic has the following patients:");
        sb.append(System.lineSeparator());
        for (Pet pet : this.data) {
            sb.append(String.format("%s %s", pet.getName(), pet.getOwner()));
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
