package christmasRaces.repositories.interfaces;

import christmasRaces.entities.drivers.Driver;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DriverRepository implements Repository<Driver>{

    private Map<String, Driver> drivers;

    public DriverRepository() {
        this.drivers = new LinkedHashMap<>();
    }

    @Override
    public Driver getByName(String name) {
        return this.drivers.get(name);
    }

    @Override
    public Collection<Driver> getAll() {
        return Collections.unmodifiableCollection(drivers.values());
    }

    @Override
    public void add(Driver driver) {
        this.drivers.put(driver.getName(), driver);
    }

    @Override
    public boolean remove(Driver driver) {
        return this.drivers.remove(driver.getName()) != null;
    }
}
