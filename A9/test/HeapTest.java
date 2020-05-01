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

    @Test
    public void heapify() throws NoLeftChildE {
        ArrayList<Item> items = new ArrayList<>();

        items.add(new Item("a",5));
        BinaryHeap bh = new BinaryHeap(items);
    //    TreePrinter.print(bh.findMin());
        assertEquals(5, bh.findMin().getValue());

        items.add(new Item("b",6));
        bh = new BinaryHeap(items);
    //    TreePrinter.print(bh.findMin());
        assertEquals(5, bh.findMin().getValue());

        items.add(new Item("c",7));
        bh = new BinaryHeap(items);
    //    TreePrinter.print(bh.findMin());
        assertEquals(5, bh.findMin().getValue());

        items.add(new Item("c",4));
        bh = new BinaryHeap(items);
    //    TreePrinter.print(bh.findMin());
        assertEquals(4, bh.findMin().getValue());

        items.add(new Item("c",3));
        bh = new BinaryHeap(items);
    //    TreePrinter.print(bh.findMin());
        assertEquals(3, bh.findMin().getValue());

        items.add(new Item("c",2));
        bh = new BinaryHeap(items);
    //    TreePrinter.print(bh.findMin());
        assertEquals(2, bh.findMin().getValue());

        items.add(new Item("c",1));
        bh = new BinaryHeap(items);
    //    TreePrinter.print(bh.findMin());
        assertEquals(1, bh.findMin().getValue());
    }

    @Test
    public void empty() throws NoLeftChildE {
        ArrayList<Item> items = new ArrayList<>();
        BinaryHeap bh = new BinaryHeap(items);
        assertTrue(bh.isEmpty());
    }

    @Test
    public void size() throws NoLeftChildE {
        ArrayList<Item> items = new ArrayList<>();
        BinaryHeap bh = new BinaryHeap(items);
        assertEquals(0, bh.getSize());

        items.add(new Item("apples",6));
        items.add(new Item("oranges",7));
        bh = new BinaryHeap(items);
        assertEquals(2,bh.getSize());
    }

    @Test
    public void parent() throws NoLeftChildE, NoParentE {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("child",20));
        items.add(new Item("grandchild",200));
        items.add(new Item("parent",2));

        BinaryHeap bh = new BinaryHeap(items);

        assertEquals(0,bh.getParentIndex(2));
        assertEquals(bh.getValue(0),bh.getValue(bh.getParentIndex(1)));
    }

    @Test
    public void child() throws NoLeftChildE, NoRightChildE {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("child",20));
        items.add(new Item("grandchild",200));
        items.add(new Item("parent",2));

        BinaryHeap bh = new BinaryHeap(items);
        TreePrinter.print(bh.findMin());

        assertEquals(200, bh.getValue(bh.getLeftChildIndex(0)));
        assertEquals(20, bh.getValue(bh.getRightChildIndex(0)));

        assertTrue(bh.getValue(1) > bh.getValue(2));
        assertEquals(2, bh.minChildIndex(0));
    }

    @Test
    public void swap() throws NoLeftChildE {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("child",20));
        items.add(new Item("grandchild",200));
        items.add(new Item("parent",2));
        items.add(new Item("brotha",50));
        items.add(new Item("sista",60));
        items.add(new Item("motha",80));
        BinaryHeap bh = new BinaryHeap(items);
        TreePrinter.print(bh.findMin());

        assertEquals(2, bh.getValue(0));
        bh.swap(0,5);
        TreePrinter.print(bh.findMin());
        assertEquals(80,bh.getValue(0));

        bh.heapify();
        TreePrinter.print(bh.findMin());
        assertEquals(2, bh.getValue(0));
    }

    @Test
    public void moveUp() throws NoLeftChildE {
        ArrayList<Item> items = new ArrayList<>();

        items.add(new Item("1",10));
        items.add(new Item("2",8));
        items.add(new Item("3",6));
        items.add(new Item("1",11));
        items.add(new Item("2",4));
        items.add(new Item("3",2));
        items.add(new Item("1",1));
        items.add(new Item("2",9));
        BinaryHeap bh = new BinaryHeap(items);
        TreePrinter.print(bh.findMin());

        bh.swap(0,7);
        TreePrinter.print(bh.findMin());

        assertEquals(1, bh.getValue(items.size()-1));

        bh.moveUp(items.size()-1);
        TreePrinter.print(bh.findMin());

        assertEquals(1,bh.getValue(0));
    }

    @Test
    public void insert() throws NoLeftChildE {
        ArrayList<Item> items = new ArrayList<>();

        items.add(new Item("1", 10));
        items.add(new Item("2", 8));
        items.add(new Item("3", 6));

        BinaryHeap bh = new BinaryHeap(items);
        assertEquals(3,bh.getSize());

        bh.insert(new Item("hey",20));
        assertEquals(4,bh.getSize());
    }

    @Test
    public void moveDown() throws NoLeftChildE {
        ArrayList<Item> items = new ArrayList<>();

        items.add(new Item("1",10));
        items.add(new Item("2",8));
        items.add(new Item("3",6));
        items.add(new Item("1",11));
        items.add(new Item("2",4));
        items.add(new Item("3",2));
        items.add(new Item("1",1));
        items.add(new Item("2",9));
        BinaryHeap bh = new BinaryHeap(items);
        TreePrinter.print(bh.findMin());

        bh.swap(0,7);
        TreePrinter.print(bh.findMin());

        assertEquals(11, bh.getValue(0));

        bh.moveDown(0);
        TreePrinter.print(bh.findMin());

        assertEquals(11,bh.getValue(2));
    }
}
