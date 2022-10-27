package cats;

import org.junit.Assert;
import org.junit.Test;

public class HouseTests {

    @Test(expected = IllegalArgumentException.class)
    public void testCreateHouseWithInvalidCapacity() {
        new House("House1", -4);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateHouseWithInvalidName() {
        new House(null, 2);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateHouseWithInvalidNameEmpty() {
        new House("", 2);
    }

    @Test
    public void testCreateHouse() {
        House house = new House("House1", 5);
        Assert.assertEquals("House1", house.getName());
        Assert.assertEquals(5, house.getCapacity());
    }

    @Test
    public void testAddCat() {
        House house = new House("House1", 10);
        Cat mike = new Cat("Mike");
        Assert.assertEquals(0, house.getCount());
        house.addCat(mike);
        Assert.assertEquals(1, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCatThrowFillHouseWithCat() {
        House house = new House("House1", 1);
        Cat mike = new Cat("Mike");
        house.addCat(mike);
        Cat betty = new Cat("Betty");
        house.addCat(betty);
    }

    @Test
    public void testRemoveCat() {
        House house = new House("House`1", 10);
        Cat mike = new Cat("Mike");
        Cat betty = new Cat("Betty");
        house.addCat(mike);
        house.addCat(betty);
        Assert.assertEquals(2, house.getCount());
        house.removeCat(mike.getName());
        Assert.assertEquals(1, house.getCount());
        house.removeCat("Betty");
        Assert.assertEquals(0, house.getCount());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistingCat() {
        House house = new House("House`1", 10);
        Cat mike = new Cat("Mike");
        Cat betty = new Cat("Betty");
        house.removeCat("Ivan");
    }

    @Test
    public void testSuccessfullySaleCat() {
        House house = new House("House`1", 10);
        Cat mike = new Cat("Mike");
        Cat betty = new Cat("Betty");
        house.addCat(mike);
        Cat returnedCat = house.catForSale("Mike");
        Assert.assertFalse(returnedCat.isHungry());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaleNonExistingCat() {
        House house = new House("House`1", 10);
        Cat mike = new Cat("Mike");
        Cat betty = new Cat("Betty");
        house.catForSale("Ivan");
    }

    @Test
    public void testStatistics() {
        House house = new House("House1", 10);
        Cat mike = new Cat("Mike");
        Cat betty = new Cat("Betty");
        house.addCat(mike);
        house.addCat(betty);
        Assert.assertEquals("The cat Mike, Betty is in the house House1!", house.statistics());

    }


}