// Complete the implementation of this class

class LinkedList<E> implements LinkedListI<E> {
    private List<E> elements;
    private E elem;
    private LinkedList<E> rest;

    LinkedList (E node, LinkedList<E> rest) {
        elements = new EmptyL<>();
    }

    public void clear() {
        this.elements = new EmptyL<>();
    }

    public int size() {
        int count = 0;
        while (this.elements != null) {
            count = count + 1;
        }
        return count;
    }

    public void addFirst(E elem) {
    }

    public void addLast(E elem) {

    }

    public E getFirst() throws NoSuchElementE {
        if (this.size() == 0)
            throw new NoSuchElementE();
        return elem;
    }

    public E getLast() throws NoSuchElementE {
        return null;
    }

    public E removeFirst() throws NoSuchElementE {
        return null;
    }
}
