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
    }

    @Test
    public void offer() {
    }

    @Test
    public void poll() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void size() {
    }
}