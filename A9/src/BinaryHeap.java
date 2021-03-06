import java.util.ArrayList;
import java.util.List;

/**
 * Binary heap with reverse bits...
 * We can flip left and right subtrees in one operation
 * <p>
 * There is a subtle interaction between the heap and the items it contains.
 * - the heap maintains an arraylist of all items
 * - each item has a reference to the heap and its position within the arraylist
 */

class NoParentE extends Exception {
}

class NoLeftChildE extends Exception {
}

class NoRightChildE extends Exception {
}

public class BinaryHeap {
    private int size;
    private ArrayList<Item> elems;

    BinaryHeap() {
        this.size = 0;
        this.elems = new ArrayList<>();
    }

    /**
     * This constructor performs "heapify". First it copy the incoming
     * elements one-by-one to the arraylist 'elems' stored as an instance variable.
     * For each item copied, the constructor should initialize properly using
     * setPosition and setHeap. When everything is properly initialized and
     * copied to 'elems' the constructor calls 'heapify'.
     */
    BinaryHeap(ArrayList<Item> es) throws NoLeftChildE {
        // TODO
        this.elems = new ArrayList<>();
        size = es.size();
        for (int i = 0; i < size; i++) {
            Item old = es.get(i);
            old.setPosition(i);
            old.setHeap(this);
            elems.add(old);
        }
        heapify();
    }

    /**
     * Implement it as discussed in class...
     */
    void heapify () throws NoLeftChildE {
        // TODO
        int half = size/2 + 1;
        for(int i = 0; i < size; i++) {
            moveDown(i);
            moveUp(i);
        }
    }

    boolean isEmpty() {
        return size == 0;
    }

    int getSize() {
        return size;
    }

    /**
     * We will location 0 in the array. The minimum is always guaranteed to be there
     * unless of course the array is empty
     */
    Item findMin() {
        // TODO
        return elems.get(0);
    }

    List<Item> getElems() {
        return elems;
    }

    Item getElem(int i) {
        return elems.get(i);
    }

    /**
     * As discussed in class and in the notes, the parent is at index i/2
     * unless of course the current node is the root of the tree
     */
    int getParentIndex(int i) throws NoParentE {
        // TODO
        if (i >= size || i == 0) { throw new NoParentE(); }
        else { return (i-1)/2; }
    }

    /**
     * Under normal circumstances the left child is at index 2i+1. It is possible
     * the index 2i+1 is outside of the array bounds and in that case the node
     * does not have a left child. It is also possible that the current element
     * has its reverse bit set, which means that the child at index 2i+1 is actually
     * the right child and the child at index 2i+2 is the left child.
     */
    int getLeftChildIndex(int i) throws NoLeftChildE {
        // TODO
        if (i >= size) { throw new NoLeftChildE(); }
        else if (2*i+1 >= elems.size()) { throw new NoLeftChildE(); }
        else if (getElem(i).getRevbit() == 1 && 2*i+2 >= elems.size()) { throw new NoLeftChildE(); }
        else if (getElem(i).getRevbit() == 1 && 2*i+2 < elems.size()) { return 2*i+2; }
        else { return 2*i+1+getElem(i).getRevbit(); }
    }

    int getRightChildIndex(int i) throws NoRightChildE {
        if (!elems.isEmpty()) {
            if (this.getElem(i).getRevbit() == 0) {
                if (2 * i + 2 < size) {
                    return 2 * i + 2;
                }
            }
            if (this.getElem(i).getRevbit() == 1) {
                if (2 * i + 1 < size) {
                    return 2 * i + 1;
                }
            }
        }
        throw new NoRightChildE();
    }

    /**
     * This method swaps the array entries at the given indices. It also needs
     * to update each element with its new position.
     */
    void swap(int i, int j) {
        // TODO
        Item tempi = getElem(i);
        Item tempj = getElem(j);

        Item oldI = this.getElem(i);
        Item oldJ = this.getElem(j);

        elems.set(i, tempj);
        elems.set(j, tempi);

        oldI.setPosition(j);
        oldJ.setPosition(i);

        elems.set(j, oldI);
        elems.set(i, oldJ);
    }

    int getValue(int i) {
        return elems.get(i).getValue();
    }

    /**
     * When an element is inserted, it is inserted in the bottom layer of the
     * tree and then moveUp is recursively called to move it to its proper
     * position.
     */
    void moveUp(int i) {
        // TODO
        try {
            int parent = getParentIndex(i);
            if (getValue(i) < getValue(parent)) {
                swap(i, parent);
                moveUp(parent);
            }
        }
        catch (NoParentE e) {}
    }

    void insert(Item ek) {
        // TODO
        ek.setPosition(size);
        ek.setHeap(this);
        elems.add(ek);
        size++;
        moveUp(size-1);
        try {
            int parentIndex = getParentIndex(ek.getValue());
            swap(parentIndex, ek.getValue());
            moveUp(parentIndex);
        } catch (NoParentE e) {}
    }

    int minChildIndex(int i) throws NoLeftChildE {
        // TODO
        int l = i*2+1;
        int r = i*2+2;
        if (l >= size) throw new NoLeftChildE();
        else if (r >= size) return l;
        else if (r + 1 >= size) {
            if (elems.get(l).getValue() > elems.get(r).getValue()) return r;
            else return l;
        }
        if (elems.get(l).getValue() > elems.get(r).getValue()) return r;
        else return l;
    }

    /**
     * When a large element finds itself high in the tree for some reason,
     * we need to move it down. For that we need to compare it to both its
     * children and swap it with the smaller of them
     */
    void moveDown(int i) throws NoLeftChildE {
        // TODO
        try {
            int min = minChildIndex(i);
            if (getValue(i) > getValue(min)) {
                swap(i, min);
                moveDown(i);
            }
        }
        catch (NoLeftChildE e) {}
    }

    /**
     * The minimum is at location 0. To remove it we take the last element
     * in the array and move it to location 0 and then recursively apply
     * moveDown.
     */
    Item extractMin() throws NoLeftChildE {
        // TODO
        if(size >= 1) {
            Item min = findMin();
            swap(0, size - 1);
            elems.remove(size - 1);
            size--;
            heapify();
            return min;
        } else {
            Item min = findMin();
            elems.remove(0);
            size = 0;
            return min;
        }
    }

    public String toString() {
        return getElems().toString();
    }
}
