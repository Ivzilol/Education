package catHouse.entities.cat;

public class ShorthairCat extends BaseCat{

    private static final int SHORTHAIR_CAT_KG = 7;
    public ShorthairCat(String name, String breed, double price) {
        super(name, breed, SHORTHAIR_CAT_KG, price);
    }

    @Override
    public void eating() {
        this.setKilograms(this.getKilograms() + 1);
    }
}
