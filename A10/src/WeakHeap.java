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
    /*
     * Position 0 in elems is for the root.
     * The root has no left child.
     * Position 1 in elems is for the right child of the root
     * After that the left child of an item at position i is at position 2i+flips(i)
     * and the right child is at position 2i+1-flips(i)
     * The parent of a child at position i is at position i/2
     * flips(0) should never be set to 1 because that would give the root
     * a left child instead of a right one
     */

    WeakHeap() {
        this.size = 0;
        this.elems = new ArrayList<>();
        this.flips = new ArrayList<>();
    }

    /*
     * The following constructor does the following sequences of steps:
     *   - it copies the incoming items to a the array items
     *   - for each item, setPosition and setHeap are called with the
     *     appropriate parameters
     *   - for each item, a corresponding flip bit is initialized to 0
     *   - call weakHeapify
     */
    WeakHeap(ArrayList<Item> items) {
        this.size = items.size();
        this.elems = new ArrayList<>();
        this.flips = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Item old = items.get(i);
            old.setPosition(i);
            old.setHeap(this);
            elems.add(old);
            flips.add(0);
        }

        weakHeapify();

    }

    /*
     * This method executes a loop that starts with the last element
     * in the array and ends with the element at position 1. In each
     * iteration, the item is joined with its distinguished ancestor.
     * Note that when calling join, the distinguished ancestor is
     * always in the first argument position.
     */
    void weakHeapify() {

        try {
            for (int i = size - 1; i > 0; i--) {
                join(getDAncestorIndex(i), i);
            }
        } catch (RootE e) {
            throw new Error("Heapify: root was wrongly reached");
        }
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

    Item getElem(int i) {
        return elems.get(i);
    }

    int getValue(int i) {
        return elems.get(i).getValue();
    }

    int getFlip(int i) {
        return flips.get(i);
    }

    public String toString() {
        return getElems().toString();
    }

    // Computations with indices

    int getParentIndex(int i) throws RootE {

        if (i != 0) {
            return i / 2;
        }

        throw new RootE();
    }

    int getLeftChildIndex(int i) throws NoLeftChildE {
        if (i != 0 && i < size && 2 * i + getFlip(i) < size)
            return 2 * i + getFlip(i);
        throw new NoLeftChildE();
    }

    int getRightChildIndex(int i) throws NoRightChildE {

        if (i < size && ((2 * i + 1 - getFlip(i))) < size)
            return 2 * i + 1 - getFlip(i);
        throw new NoRightChildE();
    }

    boolean isLeftChild(int i) throws RootE {
        return i != 0 && i % 2 == flips.get(getParentIndex(i));
    }

    boolean isRightChild(int i) throws RootE {
        return i != 0 && i % 2 != flips.get(getParentIndex(i));

    }

    int getDAncestorIndex(int i) throws RootE {
        if (isRightChild(i)) {
            return getParentIndex(i);
        }
        return getDAncestorIndex(getParentIndex(i));
    }

    int getLeftMostChildIndex() throws NoLeftChildE {
        if (size > 1) {
            int i = 1;
            while (true) {
                try {
                    i = getLeftChildIndex(i);
                } catch (NoLeftChildE e) {
                    break;
                }
            }
            return i;
        }
        throw new NoLeftChildE();
    }

    // Helpers for main methods

    void swap(int i, int j) {
        Item atI = elems.get(i);
        Item atJ = elems.get(j);

        atI.setPosition(j);
        atJ.setPosition(i);

        elems.set(i,atJ);
        elems.set(j, atI);
    }

    /*
     * If the value at position j is smaller than the value
     * at position i, they are swapped and the flip bit at
     * position j is negated. In that case the method returns
     * false. If no action was taken, the method returns true.
     */
    boolean join(int i, int j) {

        if(getValue(j) < getValue(i)){
            swap(i,j);
            flips.set(j,1-flips.get(j));
            return false;
        }
        return true;
    }

    /*
     * The method starts by joining j with its distinguished ancestor.
     * If a swap was performed, the method recursively continues by moving
     * the distinguished ancestor up. If not, the method returns immediately.
     */
    void moveUp(int j) {
        try {
            int ancestor = getDAncestorIndex(j);
            if(!join(ancestor, j)){
                moveUp(ancestor);
            }
        } catch (RootE rootE) {

        }

    }

    /*
     * The method starts by locating the leftmost child along the leftmost
     * spine. It then repeatedely joins j with that leftmost child and its
     * parents.
     */
    void moveDown(int j) {
        try {
            int leftMost = getLeftMostChildIndex();

            while(leftMost != j){
                join(j, leftMost);
                leftMost = getParentIndex(leftMost);
            }
        } catch (NoLeftChildE | RootE e) {

        }
    }

    // Main methods: insert and extractMin

    /*
     * The method adds the new item at the end of the array making sure
     * it calls setPosition and setHeap with the appropriate parameters
     * and initializes the associated flip bit correctly. As a little
     * optimization, if the inserted item is in a left child position, it
     * will reset the flip bit of the parent to 0.
     * The method then calls moveUp.
     */
    void insert(Item item) {

        elems.add(item);
        flips.add(0);
        size++;
        item.setHeap(this);
        item.setPosition(size-1);

        try {
            if(isLeftChild(size-1)){
                int parent = getParentIndex(size-1);
                flips.set(parent, 0);

            }
            moveUp(size-1);
        } catch (RootE rootE) {

            throw new Error("Internal bug: insert. Seemingly impossible exception");
        }
    }

    /*
     * Like we did in the previous and as is outlined in the lecture notes,
     * the last item in the array is moved to location 0. And then moveDown
     * is called. Just make sure you don't call moveDown if the array has exactly
     * one element!
     */
    Item extractMin() {
        // TODO
       Item minItem = findMin();

       swap(0,size-1);

       elems.remove(size-1);
       size--;
       moveDown(0);

       return minItem;

    }

    /*
     * This method is useful for testing and debugging. It loops over the elements
     * of the array starting from the end until reaching index 1. For each item,
     * the method checks that it is larger than its distinguished ancestor.
     */
    boolean checkOrder() {
        try {
            for (int j = size - 1; j >= 1; j--) {
                int i = getDAncestorIndex(j);
                if (getValue(j) < getValue(i)) return false;
            }
            return true;
        }
        catch (RootE e) {
            throw new Error("Internal bug: checkOrder");
        }

    }




}
