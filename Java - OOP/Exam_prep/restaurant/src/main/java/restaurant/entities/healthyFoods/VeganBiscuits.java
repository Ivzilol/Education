package restaurant.entities.healthyFoods;

public class VeganBiscuits extends Food{

    private final static double INITIAL_VEGAN_BISCUIT_PORTION = 150;

    public VeganBiscuits(String name, double price) {
        super(name, INITIAL_VEGAN_BISCUIT_PORTION, price);
    }
}
