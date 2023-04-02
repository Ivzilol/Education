package easterRaces.entities.racers;

import easterRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;

import static easterRaces.common.ExceptionMessages.*;

public class RaceImpl implements Race {

    private String name;

    private int lap;

    private Collection<Driver> drivers;

    public RaceImpl(String name, int lap) {
        setName(name);
        setLap(lap);
        this.drivers = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().length() < 5) {
            throw new IllegalArgumentException(String.format(INVALID_NAME, name, 5));
        }
        this.name = name;
    }

    public void setLap(int lap) {
        if (lap < 1) {
            throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_LAPS, 1));
        }
        this.lap = lap;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLaps() {
        return this.lap;
    }

    @Override
    public Collection<Driver> getDrivers() {
        return this.drivers;
    }

    @Override
    public void addDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException(DRIVER_INVALID);
        }
        if (!driver.getCanParticipate()) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_PARTICIPATE, driver.getName()));
        }
        if (this.drivers.contains(driver)) {
            throw new IllegalArgumentException(String.format(DRIVER_ALREADY_ADDED, driver.getName(), this.name));
        }
        this.drivers.add(driver);
    }
}
