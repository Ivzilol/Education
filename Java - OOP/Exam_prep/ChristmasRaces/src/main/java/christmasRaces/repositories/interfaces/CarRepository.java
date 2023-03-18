package christmasRaces.repositories.interfaces;

import christmasRaces.entities.cars.Car;

import java.util.*;

public class CarRepository implements Repository<Car>{

    private Map<String, Car> cars;

    public CarRepository() {
        this.cars = new LinkedHashMap<>();
    }

    @Override
    public Car getByName(String name) {
        return  cars.get(name);
    }

    @Override
    public Collection<Car> getAll() {
        return Collections.unmodifiableCollection(cars.values());
    }

    @Override
    public void add(Car car) {
        this.cars.put(car.getModel(), car);
    }

    @Override
    public boolean remove(Car car) {
        return this.cars.remove(car.getModel()) != null;
    }
}
