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
    public void clear () {
        Arrays.fill(elements, Optional.empty());
        front = capacity - 1;
        back = 0;
        size = 0;
    }

    public int size () {
	    return size;
    }

    // stack methods: delegate to dequeue
    public void push(E item) {
<<<<<<< HEAD
        addFirst(item);
=======
	
>>>>>>> bc17e900c22d50e60d3f18b44a1c4637f12fe25d
    }

    public E peek() throws NoSuchElementE {
        return getFirst();
    }

    public E pop() throws NoSuchElementE {
        return removeFirst();
    }

    // queue methods: delegate to dequeue
    public void offer(E elem) {
        addLast(elem);
    }

    public E poll() throws NoSuchElementE {
        return getFirst();
    }

    public E remove() throws NoSuchElementE {
        return removeFirst();
    }

    // dequeue methods
    public void addFirst(E elem) {
        if (size == capacity) doubleCapacity();
        elements[front] = Optional.of(elem);
        front = Math.floorMod(front - 1, capacity);
        size++;
    }

    public void addLast(E elem) {
        if (size == capacity) doubleCapacity();
        elements[back] = Optional.of(elem);
        back = Math.floorMod(back + 1, capacity);
        size++;
    }

    public E getFirst() throws NoSuchElementE {
        if (size == 0) {
            throw new NoSuchElementE();
        } else {
            return elements[Math.floorMod(front + 1, capacity)].get();
        }
    }

    public E getLast() throws NoSuchElementE {
        if (size == 0) {
            throw new NoSuchElementE();
        } else {
            return elements[Math.floorMod(back - 1, capacity)].get();
        }
    }

    public E removeFirst() throws NoSuchElementE {
        if (size == 0) {
            throw new NoSuchElementE();
        } else {
            front = Math.floorMod(front + 1, capacity);
            E apple = elements[front].get();
            elements[front] = Optional.empty();
            size--;
            return apple;
        }
    }

    public E removeLast() throws NoSuchElementE {
        if (size == 0) {
            throw new NoSuchElementE();
        } else {
            back = Math.floorMod(back -1, capacity);
            E orange = elements[back].get();
            elements[back] = Optional.empty();
            size--;
            return orange;
        }
    }

    private void doubleCapacity () {
        // newElements ...
        // fill
        // copy old elements to newElements
        // loop from i=0; to capacity - 1
        // newElements[i] = old elements front + 1 + i
        // set back and front
        // capacity = 2 capacity
        Optional<E>[] newElements = (Optional<E>[]) Array.newInstance(Optional.class, 2*capacity);
        Arrays.fill(newElements, Optional.empty());
        for (int i = 0; i < capacity; i++) {
            newElements[i] = elements[Math.floorMod(front + 1 + i,capacity)];
        }
        capacity = capacity*2;
        front = capacity - 1;
        back = capacity / 2;
        elements = newElements;
    }

    // the following methods are used for debugging and testing;
    // please do not edit them

    Optional<E>[] getElements () { return elements; }

    int getCapacity () { return capacity; }

    int getFront () { return front; }

    int getBack () { return back; }

}
