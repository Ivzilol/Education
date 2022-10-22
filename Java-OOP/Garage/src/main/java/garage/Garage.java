package garage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Garage {
    private List<Car> cars;

    public Garage() {
        this.cars = new ArrayList<>();
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(this.cars);
    }

    public int getCount() {
        return this.cars.size();
    }

    public List<Car> findAllCarsWithMaxSpeedAbove(int speed) {
        List<Car> cars = this.cars.stream().filter(c -> c.getMaxSpeed() > speed).collect(Collectors.toList());

        return cars;
    }

    public void addCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car can't be null");
        }

        this.cars.add(car);
    }

    public Car getTheMostExpensiveCar() {
        Car car = this
                .cars
                .stream()
                .sorted((c1, c2) -> Double.compare(c2.getPrice(), c1.getPrice()))
                .limit(1)
                .findFirst()
                .orElse(null);

        return car;
    }

    public List<Car> findAllCarsByBrand(String brand) {
        List<Car> cars = this.cars.stream().filter(c -> c.getBrand().equals(brand)).collect(Collectors.toList());

        return cars;
    }
}