package christmasRaces.entities.races;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;

public class RaceImpl implements Race {

    private String name;
    private int laps;
    private Collection<Driver> drivers;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);
        drivers = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().length() < 5) {
            String massage = String.format(ExceptionMessages.INVALID_NAME, name, 5);
            throw new IllegalArgumentException(massage);
        }
        this.name = name;
    }

    private void setLaps(int laps) {
        if (laps < 1) {
            String massage = String.format(ExceptionMessages.INVALID_NUMBER_OF_LAPS, 1);
            throw new IllegalArgumentException(massage);
        }
        this.laps = laps;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLaps() {
        return laps;
    }

    @Override
    public Collection<Driver> getDrivers() {
        return drivers;
    }

    @Override
    public void addDriver(Driver driver) {
        if (driver == null) {
            String massage = ExceptionMessages.DRIVER_INVALID;
            throw new IllegalArgumentException(massage);
        } else if (!driver.getCanParticipate()) {
            String massage = String.format(ExceptionMessages.DRIVER_NOT_PARTICIPATE, driver.getName());
            throw new IllegalArgumentException(massage);
        } else if (drivers.contains(driver)) {
            String massage = String.format(ExceptionMessages.DRIVER_ALREADY_ADDED, driver.getName(), this.name);
            throw new IllegalArgumentException(massage);
        }
        drivers.add(driver);
    }
}
