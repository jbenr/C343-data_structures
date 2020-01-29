// Complete the implementation of this class

class LinkedList<E> implements LinkedListI<E> {
    private List<E> elements;
    private E node;
    private LinkedList<E> rest;

    LinkedList (E node, LinkedList<E> rest) {
        elements = new EmptyL<>();
    }

    public void clear() {
        this.elements = new EmptyL<>();
    }

    public int size() {
        int count = 0;
        if(this.node != null){
            count++;
            count = count + this.rest.size();
        }
        return count;
    }

    public void addFirst(E elem) {
        node = elem;
        rest = new LinkedList<E>(node, rest);
    }

    public void addLast(E elem) {
        rest =
    }

    public E getFirst() throws NoSuchElementE {
        if (this.size() == 0)
            throw new NoSuchElementE();
        return node;
    }

    public E getLast() throws NoSuchElementE {
        for(int i = 0; i < size(); i++){
            
        }
    }

    public E removeFirst() throws NoSuchElementE {
        return null;
    }
}
