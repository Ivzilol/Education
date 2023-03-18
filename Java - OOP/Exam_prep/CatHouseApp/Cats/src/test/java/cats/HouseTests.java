package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HouseTests {

    private House house;
    private Cat first;
    private Cat second;
    private Cat third;

    @Before
    public void setup() {
        this.house = new House("BigHouse", 2);
        first = new Cat("Pesho");
        second = new Cat("Gosho");
        third = new Cat("Ivo");
    }

    @Test
    public void testGetName() {
        Assert.assertEquals("BigHouse", house.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameExceptionNull() {
        House house1 = new House(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameExceptionWhitespace() {
        House house1 = new House("    ", 10);
    }

    @Test
    public void testGetCapacity() {
        Assert.assertEquals(2, house.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionSetCapacityException() {
        House house1 = new House("House", -10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCapacityException() {
        house.addCat(first);
        house.addCat(second);
        house.addCat(third);
    }

    @Test
    public void testAddCat() {
        house.addCat(first);
        Assert.assertEquals(1, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCatException() {
        house.addCat(first);
        house.removeCat("Gosho");
    }

    @Test
    public void successfulRemoveCat() {
        house.addCat(first);
        house.addCat(second);
        house.removeCat("Gosho");
        Assert.assertEquals(1, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCatForSaleException() {
        house.addCat(first);
        house.catForSale("Ivo");
    }

    @Test
    public void testSuccessSaleCat() {
       House newHouse = new House("House", 10);
       newHouse.addCat(first);
       newHouse.addCat(second);
       newHouse.addCat(third);
       newHouse.catForSale("Ivo");
       Cat returnedCat = newHouse.catForSale("Ivo");
       Assert.assertFalse(returnedCat.isHungry());
    }

    @Test
    public void testStatistic() {
        house.addCat(first);
        house.addCat(second);
        Assert.assertEquals("The cat Pesho, Gosho is in the house BigHouse!", house.statistics());
    }
}
