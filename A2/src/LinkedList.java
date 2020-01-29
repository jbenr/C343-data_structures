// Complete the implementation of this class

class LinkedList<E> implements LinkedListI<E> {
    private List<E> elements;
    private E node;
    private LinkedList<E> rest;

    LinkedList (E node, LinkedList<E> rest) {
        elements = new EmptyL<>();
        this.node = node;
        this.rest = rest;
    }

    public void clear() {
        this.elements = new EmptyL<>();
    }

    public int size() {
        return elements.length();
    }

    public void addFirst(E elem) {
        node = elem;
        rest = new LinkedList<E>(node, rest);
    }

    public void addLast(E elem) {
        for(int i = 0; i < this.size(); i++){
            if(this.rest.size() != 0){
                rest = new LinkedList<E>(rest.node, rest.rest);
            }
            rest.rest = new LinkedList<E>(elem, rest.rest);
        }
    }

    public E getFirst() throws NoSuchElementE {
        if (this.size() == 0)
            throw new NoSuchElementE();
        return node;
    }

    public E getLast() {
        if(rest.size() == 0) { return node; }
        else return rest.getLast();
    }

    public E removeFirst() throws NoSuchElementE {
        node = rest.getFirst();
        rest = rest.rest;
        return this.node;
    }
}
