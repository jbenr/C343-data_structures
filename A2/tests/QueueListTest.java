import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueueListTest {

    private QueueList<Integer> queue1, empty;

    @Before
    public void setUp() throws Exception {
        queue1 = new QueueList<>();
        queue1.offer(1);
        queue1.offer(2); // Begin 1 2 End
        queue1.offer(3); // Begin 1 2 3 End

        empty = new QueueList<>();
    }

    @After
    public void tearDown() throws Exception {
        queue1 = null;
        empty = null;
    }

    @Test
    public void clear() {
        assertEquals(3, queue1.size());
        queue1.clear();
        assertEquals(0, queue1.size());
    }

    @Test
    public void offer() throws NoSuchElementE {
        assertEquals(3, queue1.size());
        queue1.offer(4);
        assertEquals(4, queue1.size());
        assertEquals(1, (int) queue1.poll());
    }

    @Test
    public void poll() throws NoSuchElementE {
        queue1.offer(4);
        assertEquals(1, (int) queue1.poll());
    }

    @Test(expected=NoSuchElementE.class)
    public void pollEmpty() throws NoSuchElementE {
        empty.poll();
    }

    @Test
    public void remove() throws NoSuchElementE {
        assertEquals(3, queue1.size());
        queue1.remove();
        assertEquals(2, queue1.size());
    }

    @Test(expected=NoSuchElementE.class)
    public void removeEmpty() throws NoSuchElementE {
        empty.remove();
    }

    @Test
    public void size() {
        assertEquals(0, empty.size());
        empty.offer(10);
        assertEquals(1, empty.size());
    }
}