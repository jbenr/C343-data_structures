import java.util.Stack;

class Traversal<E> {

    // in-order recursive 
    static <E> List<E> inOrderRecursive(BinTree<E> tree){
	try {
            return inOrderRecursive(tree.getLeftT()).append(new Node<>(tree.getData(), inOrderRecursive(tree.getRightT())));
        } catch (EmptyTreeE e) {
            return new Empty<>();
        }
    }

    // in-order iterative
    static <E> List<E> inOrderIterative(BinTree<E> tree){
	List<E> result = new Empty<>();
        Stack<Pair<BinTree<E>,E>> stack = new Stack<>();
        VISIT_NODE: while (true) {
            try {
                stack.push(new Pair<>(tree.getRightT(), tree.getData()));
                tree = tree.getLeftT();
            } catch (EmptyTreeE e) {
                while (!stack.isEmpty()) {
                    Pair<BinTree<E>,E> frame = stack.pop();
                    result = result.append(new Node<>(frame.getSecond(),new Empty<>()));
                    tree = frame.getFirst();
                    continue VISIT_NODE;
                }
                return result;
            }
        }
    }
    
    // pre-order recursive
    static <E> List<E> preOrderRecursive(BinTree<E> tree){
        return new Empty<>();
    }

    // pre-order iterative
    static <E> List<E> preOrderIterative(BinTree<E> tree){
	return new Empty<>();
    }

    // post-order recursive
    static <E> List<E> postOrderRecursive(BinTree<E> tree) {
        return new Empty<>();
    }

    // post-order iterative
    static <E> List<E> postOrderIterative(BinTree<E> tree) {
	return new Empty<>();
    }
}
