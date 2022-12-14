package zoo.entities.foods;

public class Meat extends BaseFood{

    private static final int MEAT_CALORIES = 70;
    private static final double MEAT_PRICE = 10;

    public Meat() {
        super(MEAT_CALORIES, MEAT_PRICE);
    }
}
