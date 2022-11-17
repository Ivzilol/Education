package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

    @Test(expected = IllegalArgumentException.class)
    public void testAddCarSuccessfully() {
        garage.addCar(null);

    }

    @Test
    public void testAddCarSuccessFully() {
        garage.addCar(porsche);
        Assert.assertEquals(1, garage.getCount());
        garage.addCar(mercedes);
        Assert.assertEquals(2, garage.getCount());
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
    public void testGetFastestCarMaxSpeedAbove(){
        garage.addCar(porsche);
        garage.addCar(mercedes);
        garage.addCar(audi);
        List<Car> returnMostFastCar = this.garage.findAllCarsWithMaxSpeedAbove(250);
        Assert.assertEquals(porsche.getBrand(), returnMostFastCar.get(0).getBrand());
    }

    @Test
    public void testgetTheMostExpensiveCar (){
        garage.addCar(porsche);
        garage.addCar(mercedes);
        garage.addCar(audi);
        Car mostExpensiveCar = garage.getTheMostExpensiveCar();
        Assert.assertEquals(mercedes.getBrand(), mostExpensiveCar.getBrand());
    }
    @Test
    public void testFindAllCarsByBrand(){
        garage.addCar(porsche);
        garage.addCar(porsche);
        garage.addCar(audi);
        List<Car>   cars = garage.findAllCarsByBrand(porsche.getBrand());
        Assert.assertEquals(2, cars.size());
    }
}