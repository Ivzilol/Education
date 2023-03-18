package christmasRaces.core;

import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static christmasRaces.common.ExceptionMessages.*;
import static christmasRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }


    @Override
    public String createDriver(String driver) {
        if (driverRepository.getByName(driver) != null) {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, driver));
        } else {
            Driver newDriver = new DriverImpl(driver);
            this.driverRepository.add(newDriver);
            return String.format(DRIVER_CREATED, driver);
        }
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        Car car;
        String message = "";
        switch (type) {
            case "Sports":
                if (carRepository.getByName(model) != null) {
                    throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
                } else {
                    car = new SportsCar(model, horsePower);
                    this.carRepository.add(car);
                    message = String.format(CAR_CREATED, "SportsCar", model);
                }
                break;
            case "Muscle":
                if (carRepository.getByName(model) != null) {
                    throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
                } else {
                    car = new MuscleCar(model, horsePower);
                    this.carRepository.add(car);
                    message = String.format(CAR_CREATED, "MuscleCar", model);
                }
                break;
        }
        return message;
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = driverRepository.getByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        Car car = this.carRepository.getByName(carModel);
        if (car == null) {
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }
        driver.addCar(car);
        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = raceRepository.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        Driver driver = driverRepository.getByName(driverName);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        race.addDriver(driver);
        raceRepository.add(race);
        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String createRace(String name, int laps) {
        Race race = this.raceRepository.getByName(name);
        if (race != null) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }
        Race newRace = new RaceImpl(name, laps);
        this.raceRepository.add(newRace);
        return String.format(RACE_CREATED, name);
    }

    @Override
    public String startRace(String raceName) {
        Race race = raceRepository.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        if (race.getDrivers().size() < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }

        Collection<Driver> drivers = race.getDrivers();
        int numberLaps = race.getLaps();
        List<Driver> winners = drivers.stream()
                .sorted((firstDriver, secondDriver) -> (int) (secondDriver.getCar().calculateRacePoints(numberLaps)
                        - firstDriver.getCar().calculateRacePoints(numberLaps)))
                .limit(3)
                .collect(Collectors.toList());
        Driver firstDriver = winners.get(0);
        Driver secondDriver = winners.get(1);
        Driver thirdDriver = winners.get(2);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(DRIVER_FIRST_POSITION, firstDriver.getName(), race.getName()));
        sb.append(System.lineSeparator());
        sb.append(String.format(DRIVER_SECOND_POSITION, secondDriver.getName(), race.getName()));
        sb.append(System.lineSeparator());
        sb.append(String.format(DRIVER_THIRD_POSITION, thirdDriver.getName(), race.getName()));
        return sb.toString().trim();
    }
}
