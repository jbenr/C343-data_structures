import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTwoStacksTest {

    private QueueTwoStacks<Integer> q1, empty;

    @Before
    public void setUp() throws Exception {
        empty = new QueueTwoStacks<Integer>(); // empty empty
        q1 = new QueueTwoStacks<Integer>();
        q1.offer(1);
        q1.offer(2);
        q1.offer(3); // Front: empty / Back: Top 1 2 3 Bottom
    }

    @After
    public void tearDown() throws Exception {
        empty = null;
        q1 = null;
    }

    @Test
    public void clear() {
        q1.clear();
        assertEquals(0, q1.size());
    }

    @Test
    public void offer() throws NoSuchElementE {
        empty.offer(1);
        empty.offer(2);
        assertEquals(2, empty.size());
        assertEquals(1, (int) empty.poll());
    }

    @Test
    public void poll() throws NoSuchElementE {
        assertEquals(1, (int) q1.poll());
    }

    @Test(expected=NoSuchElementE.class)
    public void pollEmpty() throws NoSuchElementE {
        empty.poll();
    }

    @Test
    public void remove() throws NoSuchElementE {
        assertEquals(1, (int) q1.remove());
    }

    @Test(expected=NoSuchElementE.class)
    public void removeEmpty() throws NoSuchElementE {
        empty.remove();
    }

    @Test
    public void size() {
        assertEquals(0, empty.size());
        assertEquals(3, q1.size());
    }
}