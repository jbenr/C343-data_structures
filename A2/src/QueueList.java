// Complete the implementation of this class
// For this implementation, you can make some of the methods in the interface take O(n) time instead of the expected O(1) time


class QueueList<E> implements QueueI<E> {
    private List<E> elements;

    QueueList() {
        elements = new EmptyL<>();
    }

    public void clear() {
        elements = new EmptyL<>();
    }

    public void offer(E elem) {
        elements = elements.addLast(elem);
    }

    public E poll() throws NoSuchElementE {
        return elements.getFirst();
    }

    public E remove() throws NoSuchElementE {
        E val = elements.getFirst();
        elements = elements.getRest();
        return val;
    }

    public int size() {
        return elements.length();
    }
}
