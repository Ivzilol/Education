package zoo.entities.animals;

public class AquaticAnimal extends BaseAnimal {

    private static final double INITIAL_KG = 2.50;

    private static final double INCREASE_KG = 7.50;

    public AquaticAnimal(String name, String kind, double price) {
        super(name, kind, price);
        setKg(INITIAL_KG);
    }

    @Override
    public void eat() {
        setKg(getKg() + INCREASE_KG);
    }
}
