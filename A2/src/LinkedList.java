// Complete the implementation of this class

class LinkedList<E> implements LinkedListI<E> {
    private List<E> elements;

    LinkedList () { elements = new EmptyL<>(); }

    public void clear() {
        elements = new EmptyL<>();
    }

    public int size() {
        return elements.length();
    }

    public void addFirst(E elem) {
        elements = new NodeL<E>(elem, elements);
    }

    public void addLast(E elem) {
        elements = elements.addLast(elem);
    }

    public E getFirst() throws NoSuchElementE {
        return elements.getFirst();
    }

    public E getLast() throws NoSuchElementE {
        return elements.getLast();
    }

    public E removeFirst() throws NoSuchElementE {
        E val = elements.getFirst();
        elements = elements.getRest();
        return val;
    }
}
