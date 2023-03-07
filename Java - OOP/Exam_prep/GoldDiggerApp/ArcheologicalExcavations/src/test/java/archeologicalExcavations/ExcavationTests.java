package archeologicalExcavations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.lang.invoke.VarHandle;
import java.util.*;


public class ExcavationTests {

    @Test
    public void constructor_ShouldSetSuccessfullyValues() {
        Excavation excavation = new Excavation("Misionis", 15);

        String expectedName = "Misionis";
        int expectedCapacity = 15;

        String actualName = excavation.getName();
        int actualCapacity = excavation.getCapacity();

        Assert.assertEquals(expectedCapacity, actualCapacity);
        Assert.assertEquals(expectedName, actualName);
    }

    @Test(expected = NullPointerException.class)
    public void constructor_ShouldThrowArgumentNullExceptionForInvalidName() {
        new Excavation(null, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_ShouldThrowArgumentExceptionForInvalidCapacity() {
        new Excavation("Misionis", -10);
    }

    @Test
    public void addMethod_ShouldAddSuccessfully() {
        Excavation excavation = new Excavation("Misionis", 10);
        Archaeologist archaeologist = new Archaeologist("George", 20);

        excavation.addArchaeologist(archaeologist);

        Assert.assertEquals(1, excavation.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addMethod_ShouldThrowsExceptionForInvalidCapacity() {
        Excavation excavation = new Excavation("Misionis", 0);
        Archaeologist archaeologist = new Archaeologist("Mike", 20);

        excavation.addArchaeologist(archaeologist);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addMethod_ShouldThrowsExceptionForDuplicateAstronaut() {
        Excavation excavation = new Excavation("Misionis", 2);
        Archaeologist archaeologist = new Archaeologist("Mike", 20);

        excavation.addArchaeologist(archaeologist);
        excavation.addArchaeologist(archaeologist);
    }

    @Test
    public void removeMethod_ShouldReturnTrueIfAstronautIsFound() {
        Excavation excavation = new Excavation("Misionis", 2);
        Archaeologist archaeologist = new Archaeologist("Mike", 20);

        excavation.addArchaeologist(archaeologist);

        boolean isRemove = excavation.removeArchaeologist("Mike");

        Assert.assertTrue(isRemove);
        Assert.assertEquals(0, excavation.getCount());
    }


}
