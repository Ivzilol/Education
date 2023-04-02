package easterRaces.repositories.interfaces;

import easterRaces.entities.cars.Car;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CarRepository<T> implements Repository<Car>{

    private Collection<Car> cars;

    public CarRepository() {
        this.cars = new ArrayList<>();
    }

    @Override
    public Car getByName(String name) {
        return this.cars.stream().filter(c -> c.getModel().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<Car> getAll() {
        return Collections.unmodifiableCollection(this.cars);
    }

    @Override
    public void add(Car model) {
        this.cars.add(model);
    }

    @Override
    public boolean remove(Car model) {
        return this.cars.remove(model);
    }
}
