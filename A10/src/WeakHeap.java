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
    WeakHeap (ArrayList<Item> items) {
	// TODO
        size = items.size();
        for(int i = 0; i < size; i++) {
            Item item = items.get(i);
            item.setPosition(i);
            item.setHeap(this);
            elems.set(i, item);
            flips.set(i, 0);
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
    void weakHeapify () {
	// TODO
        for(int i = 0; i <= size; i++) {
            try {
                join(getDAncestorIndex(size-i), getValue(size-i));
            } catch (RootE rootE) {
                rootE.printStackTrace();
            }
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
	// TODO
        if (i >= 0) {
            return i/2;
        } else {
            throw new RootE();
        }
    }

    int getLeftChildIndex(int i) throws NoLeftChildE {
	// TODO
        return getElem(i*2).getPosition();
    }

    int getRightChildIndex(int i) throws NoRightChildE {
        // TODO
        return getElem((i*2) + 1).getPosition();
    }

    boolean isLeftChild (int i) throws RootE {
        // TODO
        return i % 2 == 0;
    }

    boolean isRightChild (int i) throws RootE {
        // TODO
        return i % 2 == 1;
    }

    int getDAncestorIndex(int i) throws RootE {
	// TODO
        if(isLeftChild(i)) {
            return getDAncestorIndex(i/2);
        } else {
            return i/2;
        }
    }

    int getLeftMostChildIndex () throws NoLeftChildE {
	// TODO
	    if(size == 0){
	        throw new NoLeftChildE();
        } else if(size == 1){
	        return 1;
        } else {
	        return 4;
        }
    }

    // Helpers for main methods

    void swap(int i, int j) {
        // TODO -- should be identical to swap in A9
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

    /*
     * If the value at position j is smaller than the value 
     * at position i, they are swapped and the flip bit at 
     * position j is negated. In that case the method returns 
     * false. If no action was taken, the method returns true.
     */
    boolean join (int i, int j) {
        // TODO
        if(getValue(i) > getValue(j)) {
            swap(i,j);
            flips.set(j,1);
            return false;
        } else {
            return true;
        }
    }

    /*
     * The method starts by joining j with its distinguished ancestor. 
     * If a swap was performed, the method recursively continues by moving
     * the distinguished ancestor up. If not, the method returns immediately.
     */
    void moveUp (int j) throws RootE {
	// TODO
   //     if(getValue(j) < getValue(getDAncestorIndex(j))) {
    //        join(j, getDAncestorIndex(j));
     //       moveUp(getDAncestorIndex(j));
      //  } else {
       //     return;
        //}
    }


    /*
     * The method starts by locating the leftmost child along the leftmost
     * spine. It then repeatedely joins j with that leftmost child and its 
     * parents. 
     */
    void moveDown (int j) {
	// TODO

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
    void insert (Item item) {
	// TODO
        try {
            size++;
            item.setPosition(size -1);
            item.setHeap(this);
            elems.add(item);
            flips.add(0);
            if(isLeftChild(size-1)){
                flips.set(getParentIndex(size-1), 0);
            }
            moveUp(size-1);
        } catch(RootE rootE){}
    }

    /*
     * Like we did in the previous and as is outlined in the lecture notes, 
     * the last item in the array is moved to location 0. And then moveDown 
     * is called. Just make sure you don't call moveDown if the array has exactly 
     * one element!
     */
    Item extractMin () {
	// TODO
        return null;
    }

    /*
     * This method is useful for testing and debugging. It loops over the elements
     * of the array starting from the end until reaching index 1. For each item,
     * the method checks that it is larger than its distinguished ancestor.
     */
    boolean checkOrder () throws RootE {
	// TODO
        boolean result = true;
	    for(int i = 0; i >= size; i++){
	        if(getValue(size-i) > getValue(getDAncestorIndex(size-i))) {
	            result = true;
            } else {
	            return false;
            }
        }
	    return result;
    }

}