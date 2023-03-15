package fairyShop.models;

import java.util.ArrayList;
import java.util.Collection;

import static fairyShop.common.ExceptionMessages.HELPER_NAME_NULL_OR_EMPTY;

public abstract class BaseHelper implements Helper{

    private String name;

    private int energy;

    private Collection<Instrument> instruments;

    public BaseHelper(String name, int energy) {
        setName(name);
        this.energy = energy;
        this.instruments = new ArrayList<>();
    }


    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(HELPER_NAME_NULL_OR_EMPTY);
        } else {
            this.name = name;
        }
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setInstruments(Collection<Instrument> instruments) {
        this.instruments = instruments;
    }

    @Override
    public void work() {
        energy -= 10;
        if (energy < 0) {
            setEnergy(0);
        }
    }

    @Override
    public void addInstrument(Instrument instrument) {
        this.instruments.add(instrument);
    }

    @Override
    public boolean canWork() {
        return this.energy > 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEnergy() {
        return this.energy;
    }

    @Override
    public Collection<Instrument> getInstruments() {
        return this.instruments;
    }

    public boolean removeInstrument(Instrument instrument) {
        return this.instruments.remove(instrument);
    }
}
