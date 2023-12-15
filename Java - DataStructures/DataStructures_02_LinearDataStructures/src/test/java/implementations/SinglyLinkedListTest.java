package implementations;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SinglyLinkedListTest {
    private SinglyLinkedList<String> list;

    @Before
    public void setUp() {
        try {
            this.list = new SinglyLinkedList<>();
            for (int i = 0; i < 100; i++) {
                list.addLast(String.valueOf(i));
            }
        } catch (Exception ignored) {
            this.list = new SinglyLinkedList<>();
        }
    }

    @Test
    public void testAddSingleElementInFront() {
        SinglyLinkedList<Integer> integers = new SinglyLinkedList<>();
        integers.addFirst(73);
        assertEquals(Integer.valueOf(73), integers.getFirst());
    }

    @Test
    public void testAddSingleElementInBack() {
        SinglyLinkedList<Integer> integers = new SinglyLinkedList<>();
        integers.addLast(73);
        assertEquals(Integer.valueOf(73), integers.getLast());
    }

    @Test
    public void testAddFirstShouldAddInFront() {
        this.list.addFirst("Slayer");
        assertEquals("Slayer", list.getFirst());
    }

    @Test
    public void testAddLastShouldAddAtTheEnd() {
        this.list.addLast("Slayer");
        assertEquals("Slayer", list.getLast());
    }

    @Test
    public void testRemoveFirstShouldRemoveTheFirstElement() {
        assertEquals("0", list.getFirst());
        assertEquals("0", list.removeFirst());
        assertEquals("1", list.getFirst());
    }

    @Test
    public void testRemoveLastShouldRemoveTheLAstElement() {
        assertEquals("99", list.getLast());
        assertEquals("99", list.removeLast());
        assertEquals("98", list.getLast());
    }

    @Test
    public void testGetFirstShouldReturnButNotRemove() {
        assertEquals("0", list.getFirst());
        assertEquals("0", list.getFirst());
        assertEquals(100, list.size());
    }

    @Test
    public void testGetLastShouldReturnButNotRemove() {
        assertEquals("99", list.getLast());
        assertEquals("99", list.getLast());
        assertEquals(100, list.size());
    }

    @Test
    public void testSize() {
        assertEquals(100, list.size());
        assertEquals(0, new SinglyLinkedList<>().size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(list.isEmpty());
        assertTrue(new SinglyLinkedList<>().isEmpty());
    }
}