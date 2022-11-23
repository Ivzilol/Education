package goldDigger.models.discoverer;

import goldDigger.common.ExceptionMessages;
import goldDigger.models.museum.BaseMuseum;
import goldDigger.models.museum.Museum;

public abstract class BaseDiscoverer implements Discoverer {

    private String name;
    private double energy;
    private Museum museum;
    private static final int DEFAULT_ENERGY_LOST = 15;


    public BaseDiscoverer(String name, double energy) {
        this.name = name;
        this.energy = energy;
        this.museum = new BaseMuseum();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            String exceptionMessage = ExceptionMessages.DISCOVERER_NAME_NULL_OR_EMPTY;
            throw new NullPointerException(exceptionMessage);
        } else {
            this.name = name;
        }
    }

    private void setEnergy(double energy) {
        if (energy < 0) {
            String exceptionMessage = ExceptionMessages.DISCOVERER_ENERGY_LESS_THAN_ZERO;
            throw new IllegalArgumentException(exceptionMessage);
        } else {
            this.energy = energy;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getEnergy() {
        return energy;
    }

    @Override
    public boolean canDig() {
        return this.energy > 0;
    }

    @Override
    public void dig() {
        this.energy = energy - DEFAULT_ENERGY_LOST;
        if (this.energy < 0) {
            this.energy = 0;
        }
    }

    @Override
    public Museum getMuseum() {
        return museum;
    }
}
