import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DequeListTest {

    private DequeList<Integer> dq, empty;

    @Before
    public void setUp() throws Exception {
        empty = new DequeList<Integer>();
        dq = new DequeList<Integer>();
        dq.addFirst(1);
        dq.addFirst(2);
        dq.addFirst(10); // Front 10 2 1 End
    }

    @After
    public void tearDown() throws Exception {
        empty = null;
        dq = null;
    }

    @Test
    public void clear() {
        assertEquals(3, dq.size());
        dq.clear();
        assertEquals(0, dq.size());
    }

    @Test
    public void addFirst() throws NoSuchElementE {
        empty.addFirst(1);
        assertEquals(1, (int) empty.getFirst());
        empty.addFirst(5);
        assertEquals(5, (int) empty.getFirst());
    }

    @Test
    public void addLast() throws NoSuchElementE {
        empty.addFirst(1);
        empty.addLast(5);
        assertEquals(5, (int) empty.getLast());
    }

    @Test
    public void getFirst() throws NoSuchElementE {
        empty.addFirst(1);
        empty.addLast(5);
        assertEquals(1, (int) empty.getFirst());
    }

    @Test(expected=NoSuchElementE.class)
    public void getFirstEmpty() throws NoSuchElementE {
        empty.getFirst();
    }

    @Test
    public void getLast() throws NoSuchElementE {
        empty.addFirst(1);
        empty.addLast(5);
        assertEquals(5, (int) empty.getLast());
    }

    @Test(expected=NoSuchElementE.class)
    public void getLastEmpty() throws NoSuchElementE {
        empty.getLast();
    }

    @Test
    public void size() {
        assertEquals(3, dq.size());
        assertEquals(0, empty.size());
    }
}