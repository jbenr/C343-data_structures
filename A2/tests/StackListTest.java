import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackListTest {

    private StackList<Integer> empty, stack1, stack2;

    @Before
    public void setUp() throws Exception {
        empty = new StackList<Integer>();
        stack1 = new StackList<Integer>();
        stack1.push(1);
        stack1.push(2);
        stack1.push(3); // Top 3 2 1 Bottom
        stack2 = new StackList<Integer>();
        stack2.push(10); // Top 10 Bottom
    }

    @After
    public void tearDown() throws Exception {
        empty = null;
        stack1 = null;
        stack2 = null;
    }

    @Test
    public void clear() {
        stack1.clear();
        assertEquals(0, stack1.size());
        assertTrue(stack1.empty());
    }

    @Test
    public void empty() {
        assertTrue(empty.empty());
        assertFalse(stack1.empty());
    }

    @Test
    public void peek() throws NoSuchElementE {
        assertEquals(3, (int) stack1.peek());
    }

    @Test(expected=NoSuchElementE.class)
    public void peekEmpty() throws NoSuchElementE {
        empty.peek();
    }

    @Test
    public void pop() throws NoSuchElementE {
        assertEquals(3, (int) stack1.pop());
        assertEquals(10, (int) stack2.pop());
    }

    @Test(expected=NoSuchElementE.class)
    public void popEmpty() throws NoSuchElementE {
        empty.pop();
    }

    @Test
    public void push() throws NoSuchElementE {
        empty.push(200);
        assertEquals(200, (int) empty.peek());
    }

    @Test
    public void size() {
        assertEquals(0, empty.size());
        assertEquals(3, stack1.size());
    }
}