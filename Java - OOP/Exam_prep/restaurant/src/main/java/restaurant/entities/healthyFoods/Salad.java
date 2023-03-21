package restaurant.entities.healthyFoods;

public class Salad extends Food{

    private final static double INITIAL_SALAD_PORTION = 150;

    public Salad(String name, double price) {
        super(name, INITIAL_SALAD_PORTION, price);
    }
}
