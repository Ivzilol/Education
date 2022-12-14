package zoo.entities.foods;

public class Vegetable extends BaseFood{

    private static final int VEGETABLE_CALORIES = 50;
    private static final double VEGETABLE_PRICE = 5;


    public Vegetable() {
        super(VEGETABLE_CALORIES, VEGETABLE_PRICE);
    }
}
