package p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;


public class DatabaseTest {
    private Database database;
    private static final Person[] PEOPLE = {new Person(1, "Boris"),
            new Person(2, "Petar"),
            new Person(3, "Miroslav")};

    @Before
    public void prepareDatabase() throws OperationNotSupportedException {
        database = new Database(PEOPLE);
    }


    @Test
    public void testConstructorHasCreateValidObject() {
        Person[] element = database.getElements();
        Assert.assertArrayEquals("Arrays are not the same", element, PEOPLE);
        Assert.assertEquals("Count of elements is incorrect", element.length, PEOPLE.length);
        for (int i = 0; i < element.length; i++) {
            Assert.assertEquals("Arrays are not the same", element[i], PEOPLE[i]);

        }
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowWhenMoreThenSixteenElements() throws OperationNotSupportedException {
        Person[] people = new Person[17];
        new Database(people);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowWhenLessThanOneElement() throws OperationNotSupportedException {
        Person[] people = new Person[0];
        new Database(people);
    }



    @Test
    public void TestAddShouldAddElement() throws OperationNotSupportedException {
        database.add(new Person(4, "Desi"));
        Person[] people = database.getElements();
        Assert.assertEquals("Invalid add operation"
                , people.length, PEOPLE.length + 1);
        Assert.assertEquals(Integer.valueOf(people[people.length - 1].getId()), Integer.valueOf(4));
        Assert.assertEquals((people[people.length - 1].getUsername()), "Desi");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddShouldThrowNullParam() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void testRemoveLastElement() throws OperationNotSupportedException {
        database.remove();
        Person[] people = database.getElements();
        Assert.assertEquals(people.length, PEOPLE.length - 1);
        Assert.assertEquals(Integer.valueOf(people[people.length - 1].getId()), Integer.valueOf(2));
        Assert.assertEquals((people[people.length - 1].getUsername()), "Petar");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveThrowEmptyDatabase() throws OperationNotSupportedException {
        database = new Database();
        database.remove();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameThrowNullParam() throws OperationNotSupportedException {
        database.findByUsername(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameThrowEmptyDatabase() throws OperationNotSupportedException {
        database = new Database();
        database.findByUsername("Peter");
    }

    @Test
    public void testFindByUsername() throws OperationNotSupportedException {
        Person person = database.findByUsername("Petar");
        Assert.assertEquals(2,person.getId());
        Assert.assertEquals("Petar", person.getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameMoreThanOnePerson() throws OperationNotSupportedException {
        database.add(new Person(4, "Miroslav"));
        database.findByUsername("Miroslav");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameWithInvalidName() throws OperationNotSupportedException {
        database.findByUsername("Pesho");
    }

    @Test
    public void getById() throws OperationNotSupportedException {
        Person person = database.findById(1);
        Assert.assertEquals(person.getId(), 1);
        Assert.assertEquals(person.getUsername(), "Boris");
    }

    @Test (expected = OperationNotSupportedException.class)
    public void testFindByIdMoreThenOneId() throws OperationNotSupportedException {
        database.add(new Person(2, "Pesho"));
        database.findById(2);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdEmptyData() throws OperationNotSupportedException {
        database = new Database();
        database.findById(2);
    }

}