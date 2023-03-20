package farmville;

import org.junit.Assert;
import org.junit.Test;

public class FarmvilleTests {

    @Test
    public void testConstructor() {
        Farm farm = new Farm("Farm", 10);

        String expectedName = "Farm";
        int expectedCapacity = 10;

        String actualName = farm.getName();
        int actualCapacity = farm.getCapacity();;

        Assert.assertEquals(expectedName, actualName);
        Assert.assertEquals(expectedCapacity, actualCapacity);
    }

    @Test(expected = NullPointerException.class)
    public void testExceptionConstructorName() {
        Farm farm = new Farm(null, 10);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testExceptionConstructorCapacity() {
        Farm farm = new Farm("Farm", -10);
    }

    @Test
    public void testGetCount() {
        Animal animal = new Animal("Cat", 10);
        Farm farm = new Farm("Farm", 10);
        farm.add(animal);
        Assert.assertEquals(1, farm.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionIfFarmIsFull() {
        Animal animal = new Animal("Cat", 10);
        Animal animal2 = new Animal("Dog", 10);
        Farm farm = new Farm("Farm", 1);
        farm.add(animal);
        farm.add(animal2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionIfAnimalExist() {
        Animal animal = new Animal("Cat", 10);
        Animal animal2 = new Animal("Cat", 10);
        Farm farm = new Farm("Farm", 2);
        farm.add(animal);
        farm.add(animal2);
    }

    @Test
    public void successfulAdd() {
        Animal animal = new Animal("Cat", 10);
        Animal animal2 = new Animal("Dog", 10);
        Farm farm = new Farm("Farm", 2);
        farm.add(animal);
        farm.add(animal2);
        Assert.assertEquals(2, farm.getCount());
    }

    @Test
    public void removeUnsuccessful() {
        Animal animal = new Animal("Cat", 10);
        Animal animal2 = new Animal("Dog", 10);
        Farm farm = new Farm("Farm", 2);
        farm.add(animal);
        farm.add(animal2);
        boolean isRemove = farm.remove("Cat");
        Assert.assertTrue(isRemove);
        Assert.assertEquals(1, farm.getCount());
    }


}
