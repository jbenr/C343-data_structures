<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.util.EmptyStackException;
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

//-----------------------------------------------------------------------
// Empty BST exception

class EmptyBSTE extends Exception {}

//-----------------------------------------------------------------------
// Abstract BST class

abstract class BST implements TreePrinter.PrintableNode, Iterable<Integer> {

    //--------------------------
    // Static fields and methods
    //--------------------------

    static EmptyBSTE EBSTX = new EmptyBSTE();

    static BST EBST = new EmptyBST();

    // A leaf is a tree with empty left and right children
    static BST BSTLeaf(int elem) {
<<<<<<< HEAD
        return new BSTNode(elem, EBST, EBST);
=======
        return new BSTNode(elem, EBST, EBST); // TODO
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    // Use the iterator (that you need to define below) to get the BST nodes
    // one-by-one and insert them into the resulting AVL tree.
<<<<<<< HEAD
    static AVL toAVL (BST bst) throws EmptyBSTE, EmptyAVLE {
        ArrayList<Integer> lst = new ArrayList<>();
        while(!bst.isEmpty()){
            int temp = bst.BSTData();
            lst.add(temp);
            bst = bst.BSTdelete(temp);
        }

        AVL avl = new EmptyAVL();
        for (Integer i : lst) {
            avl = avl.AVLinsert(i);
        }
        return avl;
=======
    static AVL toAVL (BST bst) {

        try {
            Iterator<Integer> bstIterator = bst.iterator();
            AVLNode avl = new AVLNode(bstIterator.next(), new EmptyAVL(), new EmptyAVL());
            while (bstIterator.hasNext()) {
                avl.AVLinsert(bstIterator.next());
            }
            return avl;
        }catch(EmptyStackException e){
            return AVL.EAVL;
        }

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    abstract int BSTData() throws EmptyBSTE;

    abstract BST BSTLeft() throws EmptyBSTE;

    abstract BST BSTRight() throws EmptyBSTE;

    abstract int BSTHeight();

    abstract boolean isEmpty();

    //--------------------------
    // Main methods
    //--------------------------

    abstract boolean BSTfind (int key);

    abstract BST BSTinsert(int key);

    abstract BST BSTdelete(int key) throws EmptyBSTE;

    abstract Pair<Integer, BST> BSTdeleteLeftMostLeaf() throws EmptyBSTE;

    abstract BST flip();
}

//-----------------------------------------------------------------------

class EmptyBST extends BST {

    //--------------------------
    // Getters and simple methods
    //--------------------------

    int BSTData() throws EmptyBSTE {
        throw EBSTX;
    }

    BST BSTLeft() throws EmptyBSTE {
        throw EBSTX;
    }

    BST BSTRight() throws EmptyBSTE {
        throw EBSTX;
    }

    int BSTHeight() {
        return 0;
    }

    boolean isEmpty () { return true; }

    //--------------------------
    // Main methods
    //--------------------------

    boolean BSTfind(int key) {
        return false;
    }

    BST BSTinsert(int key) {
        return BSTLeaf(key);
    }

    BST BSTdelete(int key) throws EmptyBSTE { throw EBSTX; }

    Pair<Integer, BST> BSTdeleteLeftMostLeaf() throws EmptyBSTE { throw EBSTX; }

    @Override
    BST flip() {
        return this;
    }

    //--------------------------
    // Printable interface
    //--------------------------

    public TreePrinter.PrintableNode getLeft() {
        return null;
    }

    public TreePrinter.PrintableNode getRight() {
        return null;
    }

    public String getText() {
        return "";
    }

    //--------------------------
    // Iterable interface
    //--------------------------

    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            public boolean hasNext() {
                return false;
            }

            public Integer next() {
                throw new NoSuchElementException();
            }
        };
    }
}

//-----------------------------------------------------------------------
// Non-empty tree case

class BSTNode extends BST {
    private int data;
    private BST left, right;
    private int height;

    public BSTNode(int data, BST left, BST right) {
        this.data = data;
        this.left = left;
        this.right = right;

        int lHeight = 1 + left.BSTHeight();
        int rHeight = 1 + right.BSTHeight();

        if (lHeight > rHeight) {
            height = lHeight;
        } else {
            height = rHeight;
        }
    }

    public BSTNode(int data, BST left, BST right){
        this.data = data;
        this.left = left;
        this.right = right;
        height = Math.max(left.BSTHeight(),right.BSTHeight())+1;
    }

    int BSTData() throws EmptyBSTE {
        return data;
    }

    BST BSTLeft() throws EmptyBSTE {
        return left;
    }

    BST BSTRight() throws EmptyBSTE {
        return right;
    }

<<<<<<< HEAD
    int BSTHeight() { return height; }
=======
    int BSTHeight() {
        return height; // TODO
    }
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9

    boolean isEmpty() {
        return false;
    }

    //--------------------------
    // Main methods
    //--------------------------

    boolean BSTfind(int key) {
<<<<<<< HEAD
        boolean hm = false;
        if(key == data){
            return true;
        } else if(key > data){
            hm = right.BSTfind(key);
        } else if(key < data) {
            hm = left.BSTfind(key);
        }
        return hm;
=======

        boolean found = false;
        if (key == this.data){
            found = true;
        }
        else if(key<this.data){
            found = left.BSTfind(key);
        }
        else{
            found = right.BSTfind(key);
        }

        return found;
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    /** @noinspection Duplicates*/
    BST BSTinsert(int key) {
<<<<<<< HEAD
        if (key < data) {
            return new BSTNode(data, left.BSTinsert(key), right);
        } else {
            return new BSTNode(data, left, right.BSTinsert(key));
        }
=======

        BSTNode b;
        if(key<this.data){
           b = new BSTNode(data,left.BSTinsert(key),right);
        }
        else{
           b = new BSTNode(data, left, right.BSTinsert(key));
        }

return b;
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }



    /** @noinspection Duplicates*/
    BST BSTdelete(int key) throws EmptyBSTE {
<<<<<<< HEAD
        if (key < data) {
            return new BSTNode(data, left.BSTdelete(key), right);
        } else if (key > data) {
            return new BSTNode(data, left, right.BSTdelete(key));
        } else {
            try {
                Pair<Integer, BST> rt = right.BSTdeleteLeftMostLeaf();
                return new BSTNode(rt.getFirst(), left, rt.getSecond());
            } catch (EmptyBSTE e) {
                return left;
            }
        }
    }

    Pair<Integer, BST> BSTdeleteLeftMostLeaf() {
        try {
            Pair<Integer, BST> leftRecur = left.BSTdeleteLeftMostLeaf();
            return new Pair<>(leftRecur.getFirst(), new BSTNode(data, leftRecur.getSecond(), right));
        } catch (EmptyBSTE e) {
            return new Pair<>(data, right);
        } // TODO
=======
        if (key == this.data){
            try {
                Pair<Integer, BST> leftMostChildOnRightAndTreeWithoutIt = right.BSTdeleteLeftMostLeaf();
                return new BSTNode(leftMostChildOnRightAndTreeWithoutIt.getFirst(), left, leftMostChildOnRightAndTreeWithoutIt.getSecond());
            }catch(EmptyBSTE e){
                return left;
            }
        }
        else if(key<this.data){
            return new BSTNode(data, left.BSTdelete(key),right);
        }
        else{
            return new BSTNode(data, left,right.BSTdelete(key));
        }
    }

    Pair<Integer, BST> BSTdeleteLeftMostLeaf() {
        try{
            Pair<Integer, BST> alpha = left.BSTdeleteLeftMostLeaf();
            return new Pair<Integer,BST>(alpha.getFirst(),new BSTNode(data, alpha.getSecond() ,right));
        }catch(EmptyBSTE e){
            return new Pair<>(data, right);
        }
    }

    @Override
    BST flip() {
        return new BSTNode(data, right.flip(), left.flip())
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    //--------------------------
    // Printable interface
    //--------------------------

    public TreePrinter.PrintableNode getLeft() {
        return left.isEmpty() ? null : left;
    }

    public TreePrinter.PrintableNode getRight() {
        return right.isEmpty() ? null : right;
    }

    public String getText() {
        return String.valueOf(data);
    }

    //--------------------------
    // Iterable interface
    //--------------------------

    public Iterator<Integer> iterator() {
<<<<<<< HEAD
        return new Iterator<>() {
            Iterator<Integer> iterLeft = left.iterator();
            Iterator<Integer> iterRight = right.iterator();
            boolean tracked = false;
            boolean hasNext = true;

            @Override
            public boolean hasNext() {
                return hasNext;
            }

            @Override
            public Integer next() {
                if (iterLeft.hasNext()) {
                    return iterLeft.next();
                }
                if (!tracked == true) {
                    tracked = true;
                    hasNext = iterRight.hasNext();
                    return data;
                }
                if (iterRight.hasNext()) {
                    int next = iterRight.next();
                    hasNext = iterRight.hasNext();
                    return next;
                }
                throw new NoSuchElementException();
            }
        };
=======
        return new TreeIterator(this);
    }


}

class TreeIterator implements Iterator<Integer>{


    private Stack<BST> stack = new Stack<>();

    public TreeIterator(BST b){
        loadStack(b);
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }




    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Integer next() {
        try {
            BST s = stack.pop();
            int t = 0;
            try {
                t = s.BSTData();
                this.loadStack(s.BSTRight());
            } catch (EmptyBSTE emptyBSTE) {
                emptyBSTE.printStackTrace();
            }
            return t;
        } catch (EmptyStackException e){
            throw new NoSuchElementException();
        }

    }

    private void loadStack(BST root){
        if (!root.isEmpty()){
            stack.push(root);
            try {
                loadStack(root.BSTLeft());
            } catch (EmptyBSTE emptyBSTE) {
                emptyBSTE.printStackTrace();
            }
        }
    }

}

//-----------------------------------------------------------------------
//-----------------------------------------------------------------------
