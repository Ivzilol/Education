package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.instrument.UnmodifiableClassException;
import java.util.List;

public class GarageTests {
    private Garage garage;
    private Car porsche;
    private Car mercedes;
    private Car audi;

    @Before
    public void setup() {
        this.garage = new Garage();
        porsche = new Car("Porsche", 300, 1200);
        mercedes = new Car("Mercedes", 200, 1500);
        audi = new Car("Audi", 220, 100);
    }

    @Test
    public void testGetCount() {
        this.garage.addCar(porsche);
        Assert.assertEquals(1, this.garage.getCount());
    }

    @Test
    public void testGetMaxSpeed() {
        this.garage.addCar(porsche);
        this.garage.addCar(mercedes);
        this.garage.addCar(audi);
        int maxSpeed = 250;
        List<Car> expected = this.garage.findAllCarsWithMaxSpeedAbove(maxSpeed);
        Assert.assertEquals(expected, this.garage.findAllCarsWithMaxSpeedAbove(maxSpeed));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionForNull() {
        this.garage.addCar(null);
    }

    @Test
    public void testAddSuccessfulCar() {
        this.garage.addCar(porsche);
        this.garage.addCar(mercedes);
        Assert.assertEquals(2, this.garage.getCount());
    }

    @Test
    public void testMostExpensiveCar() {
        this.garage.addCar(porsche);
        this.garage.addCar(mercedes);
        this.garage.addCar(audi);
        Assert.assertEquals(mercedes, this.garage.getTheMostExpensiveCar());
    }

    @Test
    public void testFindAllCarsByBrand() {
        this.garage.addCar(porsche);
        this.garage.addCar(porsche);
        this.garage.addCar(audi);
        String brand = "Porsche";
        List<Car> expected = garage.findAllCarsByBrand(brand);
        Assert.assertEquals(2, expected.size());
    }

    @Test
    public void testGetCarsSuccessfully() {
        garage.addCar(porsche);
        garage.addCar(audi);
        List<Car> carsInGarage = garage.getCars();
        Assert.assertEquals(2, garage.getCount());
        Assert.assertEquals(porsche.getBrand(), carsInGarage.get(0).getBrand());
    }

    @Test
    public void testAddCarSuccessFully() {
        garage.addCar(porsche);
        Assert.assertEquals(1, garage.getCount());
        garage.addCar(mercedes);
        Assert.assertEquals(2, garage.getCount());
    }

    @Test
    public void testGetFastestCarMaxSpeedAbove(){
        garage.addCar(porsche);
        garage.addCar(mercedes);
        garage.addCar(audi);
        List<Car> returnMostFastCar = this.garage.findAllCarsWithMaxSpeedAbove(250);
        Assert.assertEquals(porsche.getBrand(), returnMostFastCar.get(0).getBrand());
    }
}