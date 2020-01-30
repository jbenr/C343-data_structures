import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SetListTest {

    private SetList<Integer> set1, empty;

    @Before
    public void setUp() throws Exception {
        empty = new SetList<Integer>();
        set1 = new SetList<Integer>();
        set1.add(1);
        set1.add(5);
    }

    @After
    public void tearDown() throws Exception {
        empty = null;
        set1 = null;
    }

    @Test
    public void clear() {
        assertEquals(2, set1.size());
        set1.clear();
        assertEquals(0, set1.size());
    }

    @Test
    public void isEmpty() {
        assertTrue(empty.isEmpty());
        assertFalse(set1.isEmpty());
        set1.clear();
        assertTrue(set1.isEmpty());
    }

    @Test
    public void add() {
        assertTrue(empty.isEmpty());
        empty.add(1);
        assertFalse(empty.isEmpty());
        assertEquals(1, empty.size());
        empty.add(1);
        assertEquals(1, empty.size());
    }

    @Test
    public void contains() {
        empty.add(5);
        assertTrue(empty.contains(5));
        assertFalse(empty.contains(6));
        empty.clear();
        assertFalse(empty.contains(5));
    }

    @Test
    public void size() {
        assertEquals(0, empty.size());
        empty.add(10);
        assertEquals(1, empty.size());
    }
}