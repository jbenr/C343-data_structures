import java.util.ArrayList;
import java.util.List;

class RootE extends Exception {
}

class NoLeftChildE extends Exception {
}

class NoRightChildE extends Exception {
}

public class WeakHeap {
    private int size;
    private ArrayList<Item> elems;
    private ArrayList<Integer> flips;

    WeakHeap() {
        this.size = 0;
        this.elems = new ArrayList<>();
        this.flips = new ArrayList<>();
    }

    WeakHeap (ArrayList<Item> items) {
    }

    void weakHeapify () {
    }

    // Trivial methods

    boolean isEmpty() {
        return size == 0;
    }

    int getSize() {
        return size;
    }

    Item findMin() {
        return elems.get(0);
    }

    List<Item> getElems() {
        return elems;
    }

    Item getElem (int i) {
        return elems.get(i);
    }

    int getValue(int i) {
        return elems.get(i).getValue();
    }

    int getFlip (int i) {
        return flips.get(i);
    }

    public String toString() {
        return getElems().toString();
    }

    // Computations with indices

    int getParentIndex(int i) throws RootE {
        return 0;
    }

    int getLeftChildIndex(int i) throws NoLeftChildE {
        return 0;
    }

    int getRightChildIndex(int i) throws NoRightChildE {
        return 0;
    }

    boolean isLeftChild (int i) throws RootE {
        return false;
    }

    boolean isRightChild (int i) throws RootE {
        return false;
    }

    int getDAncestorIndex(int i) throws RootE {
        return 0;
    }

    int getLeftMostChildIndex () throws NoLeftChildE {
        return 0;
    }

    // Helpers for main methods

    void swap(int i, int j) {
    }

    boolean join (int i, int j) {
        return false;
    }

    void moveUp (int j) {
    }

    void moveDown (int j) {
    }

    void updateKey (int i, int value) {
	assert value < elems.get(i).getValue();
        // TODO

    }

    // Main methods: insert and extractMin

    void insert (Item item) {
    }

    Item extractMin () {
        return null;
    }

    // For debugging

    boolean checkOrder () {
        return false;
    }
}


