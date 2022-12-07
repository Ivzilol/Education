package football.entities.supplement;

public class Liquid extends BaseSupplement{

    private static final int LIQUID_ENERGY = 90;
    private static final int LIQUID_PRICE = 25;

    public Liquid() {
        super(LIQUID_ENERGY, LIQUID_PRICE);
    }
}
