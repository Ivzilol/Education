
package p01_Database;


/*public class DatabaseTest {
    public Database database;
    private static final Integer[] NUMBERS = {7, 45, 34, 12, 98, 23};

    @Before
    public void prepareDatabase() throws OperationNotSupportedException {
        database = new Database(NUMBERS);
    }


    @Test
    public void testConstructorHasCreateValidObject() {
        Integer[] element = database.getElements();
        Assert.assertArrayEquals("Arrays are not the same", element, NUMBERS);
        Assert.assertEquals("Count of elements is incorrect", element.length, NUMBERS.length);
        for (int i = 0; i < element.length; i++) {
            Assert.assertEquals("Arrays are not the same", element[i], NUMBERS[i]);

        }
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowWhenMoreThenSixteenElements() throws OperationNotSupportedException {
        Integer[] numbers = new Integer[17];
        new Database(numbers);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowWhenLessThanOneElement() throws OperationNotSupportedException {
        Integer[] numbers = new Integer[0];
        new Database(numbers);
    }

    @Test
    public void TestAddShouldAddElement() throws OperationNotSupportedException {
        database.add(67);
        Integer[] elements = database.getElements();
        Assert.assertEquals("Invalid add operation"
                , elements.length, NUMBERS.length + 1);
        Assert.assertEquals(elements[elements.length - 1], Integer.valueOf(67));
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddShouldThrowNullParam() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void testRemoveLastElement() throws OperationNotSupportedException {
        database.remove();
        Integer[] elements = database.getElements();
        Assert.assertEquals(elements.length, NUMBERS.length - 1);
        Assert.assertEquals(elements[elements.length - 1], Integer.valueOf(98));
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveThrowEmptyDatabase() throws OperationNotSupportedException {
        for (int i = 0; i < NUMBERS.length; i++) {
            database.remove();
        }
        database.remove();
    }
}*/