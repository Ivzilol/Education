package zoo.entities.animals;

public class TerrestrialAnimal extends BaseAnimal{

    private static final double INITIAL_KG = 5.50;

    private static final double INCREASE_KG = 5.70;

    public TerrestrialAnimal(String name, String kind, double price) {
        super(name, kind, price);
        setKg(INITIAL_KG);
    }

    @Override
    public void eat() {
        setKg(getKg() + INCREASE_KG);
    }
}
