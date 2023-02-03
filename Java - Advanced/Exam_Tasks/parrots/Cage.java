package ExamPreparetion_01.parrots;

import java.util.ArrayList;
import java.util.List;

public class Cage {

    public String name;

    public int capacity;

    public List<Parrot> data;

    public Cage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void add(Parrot parrot) {
        if (this.data.size() < this.capacity) {
            this.data.add(parrot);
        }
    }

    public boolean remove (String name) {
        for (Parrot parrot : this.data) {
            if (parrot.getName().equals(name)) {
                this.data.remove(parrot);
                return true;
            }
        }
        return false;
    }

    public Parrot sellParrot(String name) {
        Parrot parrot = null;
        for (Parrot currentParrot : this.data) {
            if (currentParrot.getName().equals(name)) {
                parrot = currentParrot;
                currentParrot.setAvailable(false);
            }
        }
        return parrot;
    }

    public List<Parrot> sellParrotBySpecies(String species) {
        List<Parrot> parrotList = new ArrayList<>();
        for (Parrot currentParrot : this.data) {
            if (currentParrot.getSpecies().equals(species)) {
                currentParrot.setAvailable(false);
                parrotList.add(currentParrot);
            }
        }
        return parrotList;
    }

    public int count() {
        return this.data.size();
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append("Parrots available at ").append(this.name).append(":").append(System.lineSeparator());
        for (Parrot parrot : this.data) {
            if (parrot.isAvailable()) {
                sb.append(parrot.toString()).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
