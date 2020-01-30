import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTest {

    private LinkedList<Integer> empty, lst1, lst2;

    @Before
    public void setUp() throws Exception {
        empty = new LinkedList<Integer>();
        lst1 = new LinkedList<Integer>();
        lst1.addFirst(1); // F 1 B
        lst2 = new LinkedList<Integer>();
        lst2.addFirst(1);
        lst2.addFirst(2);
        lst2.addLast(-1); // F 2 1 -1 B
    }

    @After
    public void tearDown() throws Exception {
        lst1 = null;
        lst2 = null;
        empty = null;
    }

    @Test
    public void clear() {
        lst2.clear();
        assertEquals(0, lst2.size());
    }

    @Test
    public void size() {
        assertEquals(1, lst1.size());
        assertEquals(3, lst2.size());
        assertEquals(0, empty.size());
    }

    @Test
    public void addFirst() throws NoSuchElementE {
        lst1.addFirst(20);
        lst1.addFirst(30);
        assertEquals(30, (int) lst1.getFirst());
        assertEquals(3, lst1.size());
    }

    @Test
    public void addLast() throws NoSuchElementE {
        empty.addLast(9);
        empty.addLast(99);
        assertEquals(2, empty.size());
        assertEquals(99, (int) empty.getLast());
    }

    @Test
    public void getFirst() throws NoSuchElementE {
        assertEquals(2, (int) lst2.getFirst());
    }

    @Test(expected=NoSuchElementE.class)
    public void getFirstEmpty() throws NoSuchElementE {
        empty.getFirst();
    }

    @Test
    public void getLast() throws NoSuchElementE {
        assertEquals(-1, (int) lst2.getLast());
    }

    @Test(expected=NoSuchElementE.class)
    public void getLastEmpty() throws NoSuchElementE {
        empty.getLast();
    }

    @Test
    public void removeFirst() throws NoSuchElementE {
        assertEquals(2, (int) lst2.removeFirst());
    }

    @Test(expected=NoSuchElementE.class)
    public void removeFirstEmpty() throws NoSuchElementE {
        empty.removeFirst();
    }
}