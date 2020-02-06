import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;

public class DynamicArrayTest {
    private DynamicArray<Integer> d, emptyd;
    private StackI<Integer> s, emptys;
    private QueueI<Integer> q, emptyq;
    private DequeI<Integer> dq, emptydq;

    @Before
    public void setUp() throws Exception {
        d = new DynamicArray<>(5);
        emptyd = new DynamicArray<>(5);
        emptys = new DynamicArray<>(4);
        emptyq = new DynamicArray<>(4);
        emptydq = new DynamicArray<>(4);
        d.addFirst(5);
        d.addFirst(4);
        s = new DynamicArray<>(4);
        s.push(1);
        s.push(2);
        s.push(3);
        q = new DynamicArray<>(4);
        q.offer(10);
        q.offer(11);
        dq = new DynamicArray<>(4);
        dq.addLast(9);
        dq.addFirst(6);
    }

    @After
    public void tearDown() throws Exception {
        d = null;
        s = null;
        q = null;
        dq = null;
        emptys = null;
        emptyq = null;
    }

    @Test
    public void clear() {
        //dynamic
        assertEquals(2, d.size());
        d.clear();
        assertEquals(0, d.size());
        //stack
        assertEquals(3, s.size());
        s.clear();
        assertEquals(0, s.size());
        //queue
        assertEquals(2, q.size());
        q.clear();
        assertEquals(0, q.size());
        //deque
        assertEquals(2, dq.size());
        dq.clear();
        assertEquals(0, dq.size());
    }

    @Test
    public void size() {
        //dynamic
        assertEquals(2, d.size());
        d.addFirst(1);
        d.addLast(2);
        d.addFirst(3);
        assertEquals(5, d.size());
        //stack
        assertEquals(3, s.size());
        s.push(6);
        assertEquals(4, s.size());
        //queue
        assertEquals(2, q.size());
        q.offer(1);
        assertEquals(3, q.size());
        //deque
        assertEquals(2, dq.size());
        dq.addFirst(1);
        assertEquals(3, dq.size());
    }

    @Test //stack
    public void push() throws NoSuchElementE {
        s.push(5);
        assertEquals(5, (int) s.pop());
    }

    @Test //stack
    public void peek() throws NoSuchElementE {
        assertEquals(3, (int) s.peek());
    }

    @Test(expected=NoSuchElementE.class)
    public void peekEmpty() throws NoSuchElementE {
        emptys.peek();
    }

    @Test //stack
    public void pop() throws NoSuchElementE {
        assertEquals(3, (int) s.pop());
    }

    @Test(expected=NoSuchElementE.class)
    public void popEmpty() throws NoSuchElementE {
        emptys.peek();
    }

    @Test //queue
    public void offer() {
        assertEquals(2, q.size());
        q.offer(1);
        assertEquals(3, q.size());
    }

    @Test //queue
    public void poll() throws NoSuchElementE {
        assertEquals(10, (int) q.poll());
    }

    @Test(expected=NoSuchElementE.class)
    public void pollEmpty() throws NoSuchElementE {
        emptyq.poll();
    }

    @Test //queue
    public void remove() throws NoSuchElementE {
        assertEquals(10, (int) q.remove());
    }

    @Test(expected=NoSuchElementE.class)
    public void removeEmpty() throws NoSuchElementE {
        emptyq.remove();
    }

    @Test //deque
    public void addFirst() throws NoSuchElementE {
        dq.addFirst(1);
        assertEquals(1, (int) dq.getFirst());
        dq.addFirst(5);
        assertEquals(5, (int) dq.getFirst());
    }

    @Test //deque
    public void addLast() throws NoSuchElementE {
        dq.addFirst(1);
        dq.addLast(5);
        assertEquals(5, (int) dq.getLast());
    }

    @Test //deque
    public void getFirst() throws NoSuchElementE {
        dq.addFirst(1);
        dq.addLast(5);
        assertEquals(1, (int) dq.getFirst());
    }

    @Test(expected=NoSuchElementE.class)
    public void getFirstEmpty() throws NoSuchElementE {
        emptydq.getFirst();
    }

    @Test //deque
    public void getLast() throws NoSuchElementE {
        dq.addFirst(1);
        dq.addLast(5);
        assertEquals(5, (int) dq.getLast());
    }

    @Test(expected=NoSuchElementE.class)
    public void getLastEmpty() throws NoSuchElementE {
        emptydq.getLast();
    }

    @Test //deque
    public void removeFirst() throws NoSuchElementE {
        dq.addFirst(1);
        dq.addFirst(2);
        assertEquals(2, (int) dq.removeFirst());
    }

    @Test(expected=NoSuchElementE.class)
    public void removeFirstEmpty() throws NoSuchElementE {
        emptydq.removeFirst();
    }

    @Test //deque
    public void removeLast() throws NoSuchElementE {
        dq.addLast(1);
        dq.addLast(2);
        assertEquals(2, (int) dq.removeLast());
    }

    @Test(expected=NoSuchElementE.class)
    public void removeLastEmpty() throws NoSuchElementE {
        emptydq.removeLast();
    }

    @Test
    public void dequeNoResize() throws NoSuchElementE {
        d.clear();
        assertEquals(0, d.size());
        d.addFirst(1);
        d.addFirst(2);
        d.addFirst(3);
        assertEquals(3, (int) d.removeFirst());
        assertEquals(1, (int) d.removeLast());
        assertEquals(2, (int) d.removeLast());
        assertEquals(0, d.size());
        d.addLast(1);
        d.addLast(2);
        d.addFirst(3);
        d.addLast(4);
        d.addFirst(5);
        assertEquals(5, (int) d.removeFirst());
        assertEquals(3, (int) d.removeFirst());
        assertEquals(1, (int) d.removeFirst());
        assertEquals(2, (int) d.removeFirst());
        assertEquals(4, (int) d.removeFirst());
    }

    @Test
    public void dequeResize() throws NoSuchElementE {
        d.clear();
        d.addLast(1);
        d.addLast(2);
        d.addFirst(3);
        d.addLast(4);
        d.addFirst(5);
        d.addFirst(6);
        assertEquals(10, d.getCapacity());
        assertEquals(6, (int) d.removeFirst());
        assertEquals(5, (int) d.removeFirst());
        assertEquals(3, (int) d.removeFirst());
        assertEquals(1, (int) d.removeFirst());
        assertEquals(2, (int) d.removeFirst());
        assertEquals(4, (int) d.removeFirst());
    }
}
