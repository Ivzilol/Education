package garage;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GarageTests {

    @Test
    public void testUnmodifiableList() {
        Car car = new Car("Lada", 100, 1000);
        Garage garage = new Garage();
        garage.addCar(car);
        List<Car> expected = List.of(car);
        Assert.assertEquals(expected, garage.getCars());
    }

    @Test
    public void testCount() {
        Car car = new Car("Lada", 100, 1000);
        Garage garage = new Garage();
        garage.addCar(car);
        Assert.assertEquals(1, garage.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionCarNull() {
        Garage garage = new Garage();
        garage.addCar(null);
    }

    @Test
    public void testSuccessfulAddCar() {
        Car car = new Car("Lada", 100, 1000);
        Garage garage = new Garage();
        garage.addCar(car);
        Assert.assertEquals(1, garage.getCars().size());
    }

    @Test
    public void testGetMostExpensiveCar() {
        Car car = new Car("Lada", 100, 1000);
        Car car2 = new Car("Kia", 120, 2000);
        Garage garage = new Garage();
        garage.addCar(car);
        garage.addCar(car2);
        Assert.assertEquals(car2, garage.getTheMostExpensiveCar());
    }

    @Test
    public void testGetCarByBrand() {
        Car car = new Car("Lada", 100, 1000);
        Car car2 = new Car("Kia", 120, 2000);
        Garage garage = new Garage();
        garage.addCar(car);
        garage.addCar(car2);
        List<Car> expected = List.of(car2);
        Assert.assertEquals(expected, garage.findAllCarsByBrand("Kia"));
    }

    @Test
    public void testFindCarWithMaxSpeed() {
        Car car = new Car("Lada", 100, 1000);
        Car car2 = new Car("Kia", 120, 2000);
        Garage garage = new Garage();
        garage.addCar(car);
        garage.addCar(car2);
        List<Car> expected = List.of(car2);
        Assert.assertEquals(expected, garage.findAllCarsWithMaxSpeedAbove(110));
    }
}