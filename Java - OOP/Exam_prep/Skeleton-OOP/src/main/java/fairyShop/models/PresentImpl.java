package fairyShop.models;

import static fairyShop.common.ExceptionMessages.PRESENT_ENERGY_LESS_THAN_ZERO;
import static fairyShop.common.ExceptionMessages.PRESENT_NAME_NULL_OR_EMPTY;

public class PresentImpl implements Present{

    private String name;

    private int energyRequired;

    public PresentImpl(String name, int energyRequired) {
        setName(name);
        setEnergyRequired(energyRequired);
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(PRESENT_NAME_NULL_OR_EMPTY);
        } else {
            this.name = name;
        }
    }

    public void setEnergyRequired(int energyRequired) {
        if (energyRequired < 0) {
            throw new IllegalArgumentException(PRESENT_ENERGY_LESS_THAN_ZERO);
        } else {
            this.energyRequired = energyRequired;
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEnergyRequired() {
        return this.energyRequired;
    }

    @Override
    public boolean isDone() {
        return energyRequired <= 0;
    }

    @Override
    public void getCrafted() {
        this.energyRequired -= 10;
        if (energyRequired < 0) {
            setEnergyRequired(0);
        }
    }
}
