package easterRaces.repositories.interfaces;

import easterRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DriverRepository<T> implements Repository<Driver>{

    private Collection<Driver> drivers;

    public DriverRepository() {
        this.drivers = new ArrayList<>();
    }

    @Override
    public Driver getByName(String name) {
        return this.drivers.stream().filter(d -> d.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<Driver> getAll() {
        return Collections.unmodifiableCollection(this.drivers);
    }

    @Override
    public void add(Driver model) {
        this.drivers.add(model);
    }

    @Override
    public boolean remove(Driver model) {
        return this.drivers.remove(model);
    }
}
