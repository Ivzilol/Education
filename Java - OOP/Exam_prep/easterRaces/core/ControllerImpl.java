package easterRaces.core;

import easterRaces.core.interfaces.Controller;
import easterRaces.entities.cars.Car;
import easterRaces.entities.cars.MuscleCar;
import easterRaces.entities.cars.SportsCar;
import easterRaces.entities.drivers.Driver;
import easterRaces.entities.drivers.DriverImpl;
import easterRaces.entities.racers.Race;
import easterRaces.entities.racers.RaceImpl;
import easterRaces.repositories.interfaces.CarRepository;
import easterRaces.repositories.interfaces.DriverRepository;
import easterRaces.repositories.interfaces.RaceRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static easterRaces.common.ExceptionMessages.*;
import static easterRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private DriverRepository<Driver> drivers;
    private CarRepository<Car> cars;

    private RaceRepository<Race> races;

    public ControllerImpl(DriverRepository<Driver> drivers, CarRepository<Car> cars, RaceRepository<Race> races) {
        this.drivers = drivers;
        this.cars = cars;
        this.races = races;
    }

    @Override
    public String createDriver(String driver) {
        Driver byName = this.drivers.getByName(driver);
        if (byName == null) {
            Driver newDriver = new DriverImpl(driver);
            this.drivers.add(newDriver);
            return String.format(DRIVER_CREATED, driver);
        } else {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, driver));
        }
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        Car car = this.cars.getByName(model);
        if (car != null){
            throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        } else {
            switch (type) {
                case "Sports":
                    car = new SportsCar(model, horsePower);
                    break;
                case "Muscle":
                    car = new MuscleCar(model, horsePower);
                    break;
            }
        }
        this.cars.add(car);
        return String.format(CAR_CREATED, car.getClass().getSimpleName(), model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = this.drivers.getByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        Car car = this.cars.getByName(carModel);
        if (car == null) {
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }
        driver.addCar(car);
        this.cars.remove(car);
        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = this.races.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        Driver driver = this.drivers.getByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        race.addDriver(driver);
        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = this.races.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        if (race.getDrivers().size() < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }
        Collection<Driver> drivers = race.getDrivers();
        int numberLaps = race.getLaps();
        List<Driver> finalResults = drivers.stream()
                .sorted((first, second) -> (int) (second.getCar().calculateRacePoints(numberLaps) -
                                        first.getCar().calculateRacePoints(numberLaps)))
                .limit(3)
                .collect(Collectors.toList());
        Driver first = finalResults.get(0);
        Driver second = finalResults.get(1);
        Driver third = finalResults.get(2);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(DRIVER_FIRST_POSITION, first.getName(), race.getName()));
        sb.append(System.lineSeparator());
        sb.append(String.format(DRIVER_SECOND_POSITION, second.getName(), race.getName()));
        sb.append(System.lineSeparator());
        sb.append(String.format(DRIVER_THIRD_POSITION, third.getName(), race.getName()));
        return sb.toString().trim();

    }

    @Override
    public String createRace(String name, int laps) {
        Race race = this.races.getByName(name);
        if (race != null) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }
        race = new RaceImpl(name, laps);
        this.races.add(race);
        return String.format(RACE_CREATED, name);
    }
}
