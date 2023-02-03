package ExamPreparetion_01.parrots;

public class Parrot {

    public String name;
    public String species;

    public boolean available;

    public Parrot(String name, String species) {
        this.name = name;
        this.species = species;
        this.available = true;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public boolean isAvailable() {
        return available;
    }

    public Parrot setAvailable(boolean available) {
        this.available = available;
        return this;
    }

    @Override
    public String toString() {
        return "Parrot (" + this.species + "): " + this.name;
    }
}
