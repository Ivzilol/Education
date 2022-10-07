package Exercises_07.GenericCountMethodDouble_06;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Comparable<T>> {
    private List<T> elements;

    public Box() {
        this.elements = new ArrayList<>();
    }

    public void add(T element) {
        this.elements.add(element);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (T element : elements) {
            builder.append(element.getClass().getName() + ": " + element).append("\n");
        }
        return builder.toString();
    }

    public void swap(int firstIndex, int secondIndex) {
        T firstIndexElement = this.elements.get(firstIndex);
        T secondIndexElement = this.elements.get(secondIndex);
        this.elements.set(firstIndex, secondIndexElement);
        this.elements.set(secondIndex, firstIndexElement);
    }

    public int countGreaterThan(T element) {
        int count = 0;
        for (T elementInList : this.elements) {
            if (elementInList.compareTo(element) > 0) {
                count++;
            }
        }
        return count;
    }
}
