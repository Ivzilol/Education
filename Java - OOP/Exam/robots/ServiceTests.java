package robots;

import org.junit.Assert;
import org.junit.Test;

public class ServiceTests {


    @Test
    public void testGetName() {
       Service service = new Service("Ivo", 10);
        Assert.assertEquals("Ivo", service.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testNameNull() {
        Service service = new Service(null, 100);
    }

    @Test(expected = NullPointerException.class)
    public void testNameWhiteSpace() {
        Service service = new Service("    ", 100);
    }

    @Test
    public void testGetCapacity(){
        Robot robot = new Robot("Ivan");
        Service service = new Service("Ivo", 10);
        service.add(robot);
        Assert.assertEquals(10, service.getCapacity());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testWrongCapacity(){
        Service service = new Service("Ivo", -10);
    }

    @Test
    public void testCount() {
        Robot robot = new Robot("Ivan");
        Service service = new Service("Ivo", 10);
        service.add(robot);
        Assert.assertEquals(1, service.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFulCapacity() {
        Robot robot = new Robot("Ivan");
        Robot robot2 = new Robot("Pesho");
        Service service = new Service("Ivo", 1);
        service.add(robot);
        service.add(robot2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveRobotException() {
        Robot robot = new Robot("Ivan");
        Robot robot2 = new Robot("Pesho");
        Service service = new Service("Ivo", 1);
        service.add(robot);
        service.add(robot2);
        service.remove("Ivoooooo");
    }

    @Test
    public void successfulRemoveRobot() {
        Robot robot = new Robot("Ivan");
        Robot robot2 = new Robot("Pesho");
        Service service = new Service("Ivo", 10);
        service.add(robot);
        service.add(robot2);
        service.remove("Ivan");
        Assert.assertEquals(1, service.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testForSaleRobotNotExist() {
        Robot robot = new Robot("Ivan");
        Robot robot2 = new Robot("Pesho");
        Service service = new Service("Ivo", 10);
        service.add(robot);
        service.add(robot2);
        service.forSale("Ivoooooooo");
    }

    @Test
    public void successfulSaleRobot() {
        Robot robot = new Robot("Ivan");
        Robot robot2 = new Robot("Pesho");
        Service service = new Service("Ivo", 10);
        service.add(robot);
        service.add(robot2);
        Robot expected = service.forSale("Ivan");
        Assert.assertFalse(expected.isReadyForSale());
    }

    @Test
    public void testReport() {
        Robot robot = new Robot("Ivan");
        Robot robot2 = new Robot("Pesho");
        Service service = new Service("Ivo", 10);
        service.add(robot);
        service.add(robot2);
        Assert.assertEquals("The robot Ivan, Pesho is in the service Ivo!", service.report());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveRobotNull() {
        Service service = new Service("Ivo", 10);
        service.remove(null);
    }

}
