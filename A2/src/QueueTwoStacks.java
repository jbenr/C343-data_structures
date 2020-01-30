// Complete the implementation of this class
// For this implementation all the methods should take amortized O(1) time

class QueueTwoStacks<E> implements QueueI<E> {
    private StackList<E> front, back;

    QueueTwoStacks () {
        front = new StackList<>();
        back = new StackList<>();
    }

    public void clear() {
        front.clear();
        back.clear();
    }

    public void offer(E elem) {
        back.push(elem);
    }

    public E poll() throws NoSuchElementE {
        if(front.empty()){
            while(!back.empty()){
                front.push(back.pop());
            }
            return front.peek();
        }
        return front.peek();
    }


    public E remove() throws NoSuchElementE {
        if(front.empty()){
            while(!back.empty()){
                front.push(back.pop());
            }
            return front.pop();
        }
        return front.pop();
    }

    public int size() {
        return front.size() + back.size();
    }
}
