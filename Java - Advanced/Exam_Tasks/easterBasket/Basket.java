package ExamPreparetion_01.easterBasket;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private String material;
    private int capacity;
    private List<Egg> data;

    public Basket(String material, int capacity) {
        this.material = material;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void addEgg(Egg egg) {
        if (this.data.size() < capacity) {
            this.data.add(egg);
        }
    }

    public boolean removeEgg(String color)  {
        for (Egg egg : this.data) {
            if (egg.getColor().equals(color)) {
                this.data.remove(egg);
                return true;
            }
        }
        return false;
    }

    public Egg getStrongestEgg() {
        Egg egg = null;
        int strongEgg = Integer.MIN_VALUE;
        for (Egg currentEgg : this.data) {
            if (currentEgg.getStrength() > strongEgg) {
                strongEgg = currentEgg.getStrength();
                egg = currentEgg;

            }
        }
        return egg;
    }

    public Egg getEgg(String color) {
        return data.stream().filter(e -> e.getColor().equals(color)).findFirst().orElse(null);
    }

    public int getCount() {
        return this.data.size();
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s basket contains:", this.material));
        sb.append(System.lineSeparator());
        for (Egg egg : this.data) {
            sb.append(egg.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
