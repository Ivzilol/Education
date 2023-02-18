package kindergarten;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Kindergarten {
    private String name;
    private int capacity;
    private List<Child> registry;

    public Kindergarten(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.registry = new ArrayList<>();
    }

    public boolean addChild(Child child) {
        if (this.registry.size() < capacity) {
            this.registry.add(child);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeChild(String firstName) {
        for (Child child : this.registry) {
            if (child.getFirstName().equals(firstName)) {
                this.registry.remove(child);
                return true;
            }
        }
        return false;
    }

    public int getChildrenCount() {
        return this.registry.size();
    }

    public Child getChild(String firstName) {
        Child child = null;
        for (Child currentChild : this.registry) {
            if (currentChild.getFirstName().equals(firstName)) {
                child = currentChild;
            }
        }
        return child;
    }

    public String registryReport() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Registered children in %s:\n", name));
        List<Child> sortedList = registry.stream()
                .sorted(Comparator.comparing(Child::getAge)
                        .thenComparing(Child::getFirstName)
                        .thenComparing(Child::getLastName)).collect(Collectors.toList());


        for (Child child : sortedList) {
            sb.append("--");
            sb.append(System.lineSeparator());
            sb.append(child);
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
