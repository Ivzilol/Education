package ExamPreparetion_01.sanctuary;

import java.util.ArrayList;
import java.util.List;

public class Habitat {

    private List<Elephant> data;
    private int capacity;

    public Habitat(int capacity) {
        this.data = new ArrayList<>();
        this.capacity = capacity;
    }
    public void add(Elephant elephant){
        if (this.data.size() < this.capacity){
            this.data.add(elephant);
        }
    }

    public boolean remove(String name){
        for (Elephant elephant : this.data){
            if (elephant.getName().equals(name)){
                return this.data.remove(elephant);
            }
        }
        return false;
    }

    public Elephant getElephant(String retiredFrom){
        for (Elephant elephant : this.data){
            if (elephant.getRetiredFrom().equals(retiredFrom)){
                return elephant;
            }
        }
        return null;
    }
    public Elephant getOldestElephant(){
        Elephant elephant = null;
        int oldestElephant = Integer.MIN_VALUE;
        for (Elephant currentElephant : this.data){
            if (currentElephant.getAge() > oldestElephant){
                oldestElephant = currentElephant.getAge();
                elephant = currentElephant;
            }
        }
        return elephant;
    }
    public int getAllElephants(){
        return this.data.size();
    }

    public String getReport() {
        StringBuilder builder = new StringBuilder();
        builder.append("Saved elephants in the park:").append(System.lineSeparator());
        for (Elephant elephant : this.data) {
            builder.append(elephant.getName().toString()).append(" came from: ").append(elephant.getRetiredFrom())
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }
}
