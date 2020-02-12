import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

public class DynamicArray<E> implements StackI<E>, QueueI<E>, DequeI<E> {
    private Optional<E>[] elements;
    private int capacity, front, back, size;
    //
    // data stored in locations:
    // front+1, front+2, ... back-2, back-1 (all mod capacity)
    //
    // common cases:
    // front points to an empty location
    // back points to an empty location
    // adding to front decreases 'front' by 1
    // adding to back increases 'back' by 1
    // removing does the opposite
    //
    // |-------------------------|
    // | 4 5 6 _ _ _ _ _ _ 1 2 3 |
    // |-------------------------|
    //         /\        /\      /\
    //        back      front  capacity
    //

    @SuppressWarnings("unchecked")
    DynamicArray (int initialCapacity) {
        elements = (Optional<E>[]) Array.newInstance(Optional.class, initialCapacity);
        Arrays.fill(elements, Optional.empty());
        capacity = initialCapacity;
        front = capacity - 1;
        back = 0;
        size = 0;
    }

    // Complete the definitions of the following methods from the interfaces

    public int size () {
	return 0;
    }

    public void push(E item) {
	
    }

    public E peek() throws NoSuchElementE {
        return null;
    }

    public E pop() throws NoSuchElementE {
        return null;
    }

    public void offer(E elem) {
    }

    public E poll() throws NoSuchElementE {
        return null;
    }

    public E remove() throws NoSuchElementE {
        return null;
    }

    public void addFirst(E elem) {
    }

    public void addLast(E elem) {
    }

    public E getFirst() throws NoSuchElementE {
        return null;
    }

    public E getLast() throws NoSuchElementE {
        return null;
    }

    public E removeFirst() throws NoSuchElementE {
        return null;
    }

    public E removeLast() throws NoSuchElementE {
        return null;
    }

    // the following methods are used for debugging and testing;
    // please do not edit them

    Optional<E>[] getElements () { return elements; }

    int getCapacity () { return capacity; }

    int getFront () { return front; }

    int getBack () { return back; }

}
