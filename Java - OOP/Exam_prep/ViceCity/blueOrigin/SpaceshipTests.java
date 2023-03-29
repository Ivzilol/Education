package blueOrigin;

import org.junit.Assert;
import org.junit.Test;

public class SpaceshipTests {

    @Test
    public void testGetCount() {
        Astronaut astronaut = new Astronaut("Ivo", 20);
        Spaceship spaceship = new Spaceship("Cargo", 10);
        spaceship.add(astronaut);
        Assert.assertEquals(1, spaceship.getCount());
    }

    @Test
    public void testGetName(){
        Spaceship spaceship = new Spaceship("Cargo", 10);
        Assert.assertEquals("Cargo", spaceship.getName());
    }

    @Test
    public void testGetCapacity(){
        Spaceship spaceship = new Spaceship("Cargo", 10);
        Assert.assertEquals(10, spaceship.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionFullCapacity() {
        Astronaut astronaut = new Astronaut("Ivo", 20);
        Astronaut astronaut2 = new Astronaut("Ivan", 20);
        Spaceship spaceship = new Spaceship("Cargo", 1);
        spaceship.add(astronaut);
        spaceship.add(astronaut2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionAstronautExist() {
        Astronaut astronaut = new Astronaut("Ivo", 20);
        Astronaut astronaut2 = new Astronaut("Ivo", 20);
        Spaceship spaceship = new Spaceship("Cargo", 10);
        spaceship.add(astronaut);
        spaceship.add(astronaut2);
    }

    @Test
    public void successfulAddAstronaut() {
        Astronaut astronaut = new Astronaut("Ivo", 20);
        Astronaut astronaut2 = new Astronaut("Ivan", 20);
        Spaceship spaceship = new Spaceship("Cargo", 10);
        spaceship.add(astronaut);
        spaceship.add(astronaut2);
    }

    @Test
    public void testSuccessfulRemoveAstronaut() {
        Astronaut astronaut = new Astronaut("Ivo", 20);
        Astronaut astronaut2 = new Astronaut("Ivan", 20);
        Spaceship spaceship = new Spaceship("Cargo", 10);
        spaceship.add(astronaut);
        spaceship.add(astronaut2);
        Assert.assertFalse(spaceship.remove("gosho"));
    }

    @Test
    public void testUnsuccessfulRemove() {
        Astronaut astronaut = new Astronaut("Ivo", 20);
        Astronaut astronaut2 = new Astronaut("Ivan", 20);
        Spaceship spaceship = new Spaceship("Cargo", 10);
        spaceship.add(astronaut);
        spaceship.add(astronaut2);
        Assert.assertTrue(spaceship.remove("Ivan"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnderZeroCapacity() {
        Spaceship spaceship = new Spaceship("Cargo", -10);
    }

    @Test(expected = NullPointerException.class)
    public void testExceptionFromWhitespace(){
        Spaceship spaceship = new Spaceship("      ", 10);
    }

    @Test(expected = NullPointerException.class)
    public void testExceptionNull(){
        Spaceship spaceship = new Spaceship(null, 10);
    }
}
