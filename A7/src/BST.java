import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        return new BSTNode(elem, EBST, EBST);
    }

    // Use the iterator (that you need to define below) to get the BST nodes
    // one-by-one and insert them into the resulting AVL tree.
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
        return new Iterator<>() {
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

    int BSTData() throws EmptyBSTE {
        return data;
    }

    BST BSTLeft() throws EmptyBSTE {
        return left;
    }

    BST BSTRight() throws EmptyBSTE {
        return right;
    }

    int BSTHeight() { return height; }

    boolean isEmpty() {
        return false;
    }

    //--------------------------
    // Main methods
    //--------------------------

    boolean BSTfind(int key) {
        boolean hm = false;
        if(key == data){
            return true;
        } else if(key > data){
            hm = right.BSTfind(key);
        } else if(key < data) {
            hm = left.BSTfind(key);
        }
        return hm;
    }

    BST BSTinsert(int key) {
        if (key < data) {
            return new BSTNode(data, left.BSTinsert(key), right);
        } else {
            return new BSTNode(data, left, right.BSTinsert(key));
        }
    }

    BST BSTdelete(int key) throws EmptyBSTE {
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
    }
}

//-----------------------------------------------------------------------
//-----------------------------------------------------------------------
