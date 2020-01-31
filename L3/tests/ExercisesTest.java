import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ExercisesTest {
    private static List<Integer> ints = new ArrayList<>(Arrays.asList(1,2,3,4,5));
    private static List<Integer> evens = new ArrayList<>(Arrays.asList(2,4,6));
    private static List<Integer> odds = new ArrayList<>(Arrays.asList(1,3,5));
    private static List<Integer> two = new ArrayList<>(Arrays.asList(2,2));
    private static List<Integer> three = new ArrayList<>(Arrays.asList(3,3,3));

    @Test
    public void mul1() {
        assertEquals(120, (int) Exercises.mul1(ints));
    }

    @Test
    public void mul2() {
        assertEquals(120, (int) Exercises.mul2(ints));
    }

    @Test
    public void mul3() {
        assertEquals(120, (int) Exercises.mul3(ints));
    }

    @Test
    public void mul5() {
        assertEquals(120, (int) Exercises.mul5(ints));
    }

    @Test
    public void even1() {
        assertTrue(Exercises.even1(evens));
        assertFalse(Exercises.even1(odds));
    }

    @Test
    public void even2() {
        assertTrue(Exercises.even1(evens));
        assertFalse(Exercises.even1(odds));
    }

    @Test
    public void even3() {
        assertTrue(Exercises.even1(evens));
        assertFalse(Exercises.even1(odds));
    }

    @Test
    public void even4() {
        assertTrue(Exercises.even1(evens));
        assertFalse(Exercises.even1(odds));
    }

    @Test
    public void even5() {
        assertTrue(Exercises.even1(evens));
        assertFalse(Exercises.even1(odds));
    }

    @Test
    public void count1() {
        assertEquals(2, Exercises.count1(2, two));
        assertEquals(3, Exercises.count1(3, three));
    }

    @Test
    public void count2() {
        assertEquals(2, Exercises.count2(2, two));
        assertEquals(3, Exercises.count2(3, three));
    }

    @Test
    public void count3() {
        assertEquals(2, Exercises.count3(2, two));
        assertEquals(3, Exercises.count3(3, three));
    }

    @Test
    public void count4() {
        assertEquals(2, Exercises.count4(2, two));
        assertEquals(3, Exercises.count4(3, three));
    }

    @Test
    public void count5() {
        assertEquals(2, Exercises.count5(2, two));
        assertEquals(3, Exercises.count5(3, three));
    }

    @Test
    public void trip1() {
    }

    @Test
    public void trip2() {
    }

    @Test
    public void trip3() {
    }

    @Test
    public void trip4() {
    }
}