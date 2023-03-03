package toyStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ToyStoryTest {

    private ToyStore toyStore;

    @Before
    public void init() {
        toyStore = new ToyStore();
    }

    @Test
    public void getValueCells() {
        Map<String, Toy> toyShelf;
        toyShelf = new LinkedHashMap<>();
        toyShelf.put("A", null);
        toyShelf.put("B", null);
        toyShelf.put("C", null);
        toyShelf.put("D", null);
        toyShelf.put("E", null);
        toyShelf.put("F", null);
        toyShelf.put("G", null);
        Assert.assertEquals(toyShelf, toyStore.getToyShelf());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addToyMissShelf() throws OperationNotSupportedException {
        Toy toy1 = new Toy("FirstToy", "FirstToyID");
        toyStore.   addToy("H123", toy1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addInMapException() throws OperationNotSupportedException {
        Toy toy1 = new Toy("FirstToy", "FirstToyID");
        Toy toy2 = new Toy("SecondToy", "SecondToyID");
        toyStore.addToy("A", toy1);
        long exist = toyStore.getToyShelf().values().stream().filter(Objects::nonNull).count();
        Assert.assertEquals(1, exist);
        toyStore.addToy("A", toy2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addToyShelfExist() throws OperationNotSupportedException {
        Toy toy = new Toy("FirstToy", "FirstToyID");
        Toy toy2 = new Toy("SecondToy", "SecondToyID");
        toyStore.addToy("A", toy);
        toyStore.addToy("A", toy2);
    }

    @Test(expected = OperationNotSupportedException.class)
        public void addToySecondTime() throws OperationNotSupportedException {
        Toy toy = new Toy("FirstToy", "FirstToyID");
        toyStore.addToy("A", toy);
        toyStore.addToy("B", toy);
    }

    @Test
    public void successAddToy() throws OperationNotSupportedException {
        Toy toy = new Toy("FirstToy", "FirstToyID");
        toyStore.addToy("A", toy);
        Assert.assertEquals(1, toyStore.getToyShelf().values().stream().filter(Objects::nonNull).count());
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeToy() throws OperationNotSupportedException {
        Toy toy = new Toy("FirstToy", "FirstToyID");
        toyStore.addToy("A", toy);
        toyStore.removeToy("H2", toy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeToy2() throws OperationNotSupportedException {
        Toy toy = new Toy("FirstToy", "FirstToyID");
        Toy toy2 = new Toy("SecondToy", "SecondToyID");
        toyStore.addToy("A", toy);
        toyStore.addToy("B" ,toy2);
        toyStore.removeToy("A", toy2);
    }

    @Test
    public void removeToySuccessful() throws OperationNotSupportedException {
        Toy toy = new Toy("FirstToy", "FirstToyID");
        Toy toy2 = new Toy("SecondToy", "SecondToyID");
        toyStore.addToy("A", toy);
        toyStore.addToy("B" ,toy2);
        this.toyStore.removeToy("A", toy);
        Assert.assertEquals(1, toyStore.getToyShelf().values().stream().filter(Objects::nonNull).count());

    }
}