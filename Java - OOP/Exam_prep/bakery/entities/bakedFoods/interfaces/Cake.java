package bakery.entities.bakedFoods.interfaces;

import bakery.entities.bakedFoods.interfaces.BaseFood;

public class Cake extends BaseFood{


    private static final double INITIAL_BREAD_PORTION = 245;

    public Cake(String name, double price) {
        super(name, INITIAL_BREAD_PORTION, price);
    }
}
