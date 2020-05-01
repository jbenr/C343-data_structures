import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import static org.junit.Assert.*;

public class HeapTest {

    @Test
    public void sortBH () throws NoLeftChildE {
        ArrayList<Item> items = new ArrayList<>();

        Item it;

        it = new Item("a1",  6);
        items.add(it);

        it = new Item("a2",  1);
        it.reverse();
        items.add(it);

        it = new Item("a3",  8);
        items.add(it);

        it = new Item("a4",  2);
        it.reverse();
        items.add(it);

        it = new Item("a5",  4);
        items.add(it);

        it = new Item("a6",  7);
        items.add(it);

        it = new Item("a7",  9);
        items.add(it);

        it = new Item("a8",  3);
        items.add(it);

        it = new Item("a9",  5);
        items.add(it);

        BinaryHeap bhp = new BinaryHeap(items);
        TreePrinter.print(bhp.findMin());

        for (int i = 1; i < 10; i++) {
            assertEquals(i, bhp.extractMin().getValue());
        }
    }

    @Test
    public void findMin() throws NoLeftChildE {
        ArrayList<Item> items = new ArrayList<>();

        items.add(new Item("1",10));
        items.add(new Item("2",8));
        items.add(new Item("3",6));
        BinaryHeap bh = new BinaryHeap(items);
        TreePrinter.print(bh.findMin());
        items.add(new Item("min",1));

        bh = new BinaryHeap(items);
        TreePrinter.print(bh.findMin());

        assertEquals(1, bh.findMin().getValue());
    }

}
