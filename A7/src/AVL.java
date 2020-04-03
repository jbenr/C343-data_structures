//-----------------------------------------------------------------------
// Empty AVL exception

class EmptyAVLE extends Exception {}

//-----------------------------------------------------------------------
// Abstract AVL class

abstract class AVL implements TreePrinter.PrintableNode {

    //--------------------------
    // Static fields and methods
    //--------------------------

    static EmptyAVLE EAVLX = new EmptyAVLE();

    static AVL EAVL = new EmptyAVL();

    static AVL AVLLeaf(int elem) {
        return new AVLNode(elem, EAVL, EAVL);
    }

    // Recursively copy the tree changing AVL nodes to BST nodes
    static BST toBST (AVL avl) {
        return null; // TODO
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    abstract int AVLData() throws EmptyAVLE;

    abstract AVL AVLLeft() throws EmptyAVLE;

    abstract AVL AVLRight() throws EmptyAVLE;

    abstract int AVLHeight();

    abstract boolean isEmpty ();

    //--------------------------
    // Main methods
    //--------------------------

    abstract boolean AVLfind (int key);

    abstract AVL AVLinsert(int key);

    abstract AVL AVLeasyRight();

    abstract AVL AVLrotateRight();

    abstract AVL AVLeasyLeft();

    abstract AVL AVLrotateLeft();

    abstract AVL AVLdelete(int key) throws EmptyAVLE;

    abstract Pair<Integer, AVL> AVLshrink() throws EmptyAVLE;
}

//-----------------------------------------------------------------------

class EmptyAVL extends AVL {

    //--------------------------
    // Getters and simple methods
    //--------------------------

    int AVLData() throws EmptyAVLE {
        throw EAVLX;
    }

    AVL AVLLeft() throws EmptyAVLE {
        throw EAVLX;
    }

    AVL AVLRight() throws EmptyAVLE {
        throw EAVLX;
    }

    int AVLHeight() {
        return 0;
    }

    boolean isEmpty () { return true; };

    //--------------------------
    // Main methods
    //--------------------------

    boolean AVLfind (int key) {
        return false;
    }

    AVL AVLinsert(int key) {
        return AVLLeaf(key);
    }

    AVL AVLeasyRight() {
        throw new Error("Internal bug: should never call easyRight on empty tree");
    }

    AVL AVLrotateRight() {
        throw new Error("Internal bug: should never call rotateRight on empty tree");
    }

    AVL AVLeasyLeft() {
        throw new Error("Internal bug: should never call easyLeft on empty tree");
    }

    AVL AVLrotateLeft() {
        throw new Error("Internal bug: should never call rotateLeft on empty tree");
    }

    AVL AVLdelete(int key) throws EmptyAVLE {
        throw EAVLX;
    }

    Pair<Integer, AVL> AVLshrink() throws EmptyAVLE {
        throw EAVLX;
    }

    //--------------------------
    // Override
    //--------------------------

    public boolean equals (Object o) {
        return (o instanceof EmptyAVL);
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
}

//-----------------------------------------------------------------------

class AVLNode extends AVL {
    private int data;
    private AVL left, right;
    private int height;

    public AVLNode(int data, AVL left, AVL right) {
        this.data = data;
        this.left = left;
        this.right = right;

        int lH = 1 + left.AVLHeight();
        int rH = 1 + right.AVLHeight();

        if (lH > rH) {
            height = lH;
        } else {
            height = rH;
        }
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    int AVLData() {
        return data;
    }

    AVL AVLLeft() {
        return left;
    }

    AVL AVLRight() {
        return right;
    }

    int AVLHeight() {
        return height;
    }

    boolean isEmpty() {
        return false;
    }

    ;

    //--------------------------
    // Main methods
    //--------------------------

    boolean AVLfind(int key) {
        boolean hm = false;
        if (key == data) {
            return true;
        } else if (key > data) {
            hm = right.AVLfind(key);
        } else if (key < data) {
            hm = left.AVLfind(key);
        }
        return hm;
    }

    AVL AVLinsert(int key) {
        AVL temp = new AVLNode(data, left, right);
        if (key < data) {
            temp = new AVLNode(data, left.AVLinsert(key), right);
        } else {
            temp = new AVLNode(data, left, right.AVLinsert(key));
        }
        try {
            if (Math.abs(temp.AVLLeft().AVLHeight() - temp.AVLRight().AVLHeight()) > 1) {
                if (key < data) {
                    if (temp.AVLLeft().AVLLeft().AVLHeight() > temp.AVLLeft().AVLRight().AVLHeight()) {
                        return temp.AVLeasyRight();
                    } else {
                        return temp.AVLrotateRight();
                    }
                } else {
                        if (right.AVLRight().AVLHeight() > right.AVLLeft().AVLHeight()) {
                            return temp.AVLeasyLeft();
                        } else {
                            return temp.AVLrotateLeft();
                        }
                        }
            } else {
                return temp;
            }
        } catch (EmptyAVLE emptyAVLE) {
            emptyAVLE.printStackTrace();
        }
        return temp;
    }

    AVL AVLeasyRight() {
        try {
            return new AVLNode(left.AVLData(), left.AVLLeft(), new AVLNode(data, left.AVLRight(), right));
        } catch (EmptyAVLE emptyAVLE) {
            return new AVLNode(data, left, right);
        }
    }

    AVL AVLrotateRight() {
        AVL tis;
        tis = new AVLNode(data, left.AVLeasyLeft(), right);
        return tis.AVLeasyRight();
    }

    AVL AVLeasyLeft() {
        try {
            return new AVLNode(right.AVLData(), new AVLNode(data, left, right.AVLLeft()), right.AVLRight());
        } catch (EmptyAVLE emptyAVLE) {
            return new AVLNode(data, left, right);
        }
    }

    AVL AVLrotateLeft() {
        AVL tis;
        tis = new AVLNode(data, left, right.AVLeasyRight());
        return tis.AVLeasyLeft();
    }

    AVL AVLdelete(int key) throws EmptyAVLE {
        AVL tis = new AVLNode(data, left, right);
        Pair<Integer, AVL> shrink;

        if (key < tis.AVLData()) {
            return new AVLNode(tis.AVLData(), tis.AVLLeft().AVLdelete(key), tis.AVLRight());
        } else if (key > tis.AVLData()) {
            return new AVLNode(tis.AVLData(), tis.AVLLeft(), tis.AVLRight().AVLdelete(key));
        } else {
            shrink = tis.AVLshrink();
            if(key < data){
                return new AVLNode(shrink.getFirst(), shrink.getSecond(), right);
            } else {
                return new AVLNode(shrink.getFirst(), left, shrink.getSecond());
            }
        }
    }

    Pair<Integer, AVL> AVLshrink() throws EmptyAVLE {
        Integer result = 0;
        AVL tis = new AVLNode(data, left, right);
        if(left.AVLHeight() > right.AVLHeight()){
            AVL temp = left;
            while(!temp.AVLRight().isEmpty()){
                result = temp.AVLRight().AVLData();
                temp = temp.AVLRight();
            }
        } else {
            AVL temp = right;
            while(!temp.AVLLeft().isEmpty()){
                result = temp.AVLLeft().AVLData();
                temp = temp.AVLLeft();
            }
        }

        return new Pair<Integer, AVL>(result, tis);
    }


    //--------------------------
    // Override
    //--------------------------

    public boolean equals (Object o) {
        if (o instanceof AVLNode) {
            AVLNode other = (AVLNode) o;
            return data == other.data && left.equals(other.left) && right.equals(other.right);
        }
        return false;
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
}

//-----------------------------------------------------------------------
//-----------------------------------------------------------------------
