package Exercises_07.GenericSwapMethodInteger_04;

import java.util.ArrayList;
import java.util.List;

public class Box<T> {
    private List<T> elements;

    public Box (){
        this.elements = new ArrayList<>();
    }
    public void add(T element){
        this.elements.add(element);
    }
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for (T element : elements){
            builder.append(element.getClass().getName() + ": " + element).append("\n");
        }
        return builder.toString();
    }
    public void swap(int firstIndex, int secondIndex){
        T firstIndexElement = this.elements.get(firstIndex);
        T secondIndexElement = this.elements.get(secondIndex);
        this.elements.set(firstIndex, secondIndexElement);
        this.elements.set(secondIndex, firstIndexElement);
    }
}
