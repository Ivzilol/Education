package fairyShop.models;

public class Sleepy extends BaseHelper{

    private static final int ENERGY = 50;

    public Sleepy(String name) {
        super(name, ENERGY);
    }

    @Override
    public void work() {
        this.setEnergy(getEnergy() - 5);
    }

    @Override
    public boolean canWork() {
        return this.getEnergy() >= 0;
    }
}
