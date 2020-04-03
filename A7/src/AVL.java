//-----------------------------------------------------------------------
// Empty AVL exception

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
    static BST toBST(AVL avl) {
        List<Integer> lst = null;

        

        BST bst = new EmptyBST();
        for (Integer i : lst) {
            bst = bst.BSTinsert(i);
        }
        return bst;
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
            AVL modifiedRight = new AVLNode(this.data, this.left.AVLRight(), this.right);
            return new AVLNode(this.left.AVLData(), this.left.AVLLeft(), modifiedRight);
        } catch (EmptyAVLE e) { return EAVL; }
    }

    AVL AVLrotateRight() {
        try {

            if (this.left.AVLLeft().AVLHeight() >= this.left.AVLRight().AVLHeight()) {
                return this.AVLeasyRight();
            } else {

                AVL leftChildRotated = this.left.AVLeasyLeft();

                return new AVLNode(
                        leftChildRotated.AVLData(),
                        leftChildRotated.AVLLeft(),
                        new AVLNode(
                                this.data,
                                leftChildRotated.AVLRight(),
                                this.right));
            }
        }
        catch (EmptyAVLE e) { return EAVL; }
    }

    AVL AVLeasyLeft() {
        try {
            AVL modifiedLeft = new AVLNode(this.data, this.left, this.right.AVLLeft());
            return new AVLNode(this.right.AVLData(), modifiedLeft, this.right.AVLRight());
        } catch (EmptyAVLE e) { return EAVL; }
    }

    AVL AVLrotateLeft() {
        try {
            if (this.right.AVLRight().AVLHeight() >= this.right.AVLLeft().AVLHeight()) {
                return this.AVLeasyLeft();
            } else {
                AVL rightChildRotated = this.right.AVLeasyRight();
                return new AVLNode(
                        rightChildRotated.AVLData(),
                        new AVLNode(
                                this.data,
                                this.left,
                                rightChildRotated.AVLLeft()),
                        rightChildRotated.AVLRight());
            }
        } catch (EmptyAVLE e) { return new EmptyAVL(); }
    }

    AVL AVLdelete(int key) throws EmptyAVLE {
        if (this.data == key) {
            if ((this.data == key) && (this.height == 1)) {
                return EAVL;
            } else {
                Pair<Integer, AVL> substitute = null;
                AVL newTree = null;
                if (this.left.isEmpty()) {
                    substitute = new Pair<>(this.right.AVLData(), EAVL);
                    newTree = new AVLNode(substitute.getFirst(), EAVL, substitute.getSecond());
                } else {
                    substitute = this.left.AVLshrink();
                    newTree = new AVLNode(substitute.getFirst(), substitute.getSecond(), right);
                }
                if (Math.abs(newTree.AVLLeft().AVLHeight() - newTree.AVLRight().AVLHeight()) > 1) {
                    if (newTree.AVLLeft().AVLHeight() < newTree.AVLRight().AVLHeight()) {
                        return newTree.AVLrotateLeft();
                    } else {
                        return newTree.AVLeasyRight();
                    }
                } else {
                    return newTree;
                }
            }
        } else if (key < this.data) {
            AVL deletedLeft = this.left.AVLdelete(key);
            if (Math.abs(deletedLeft.AVLHeight() - right.AVLHeight()) > 1) {
                return new AVLNode(this.data, deletedLeft, right).AVLrotateLeft();
            } else {
                return new AVLNode(this.data, deletedLeft, right);
            }
        } else {
            AVL deletedRight = this.right.AVLdelete(key);
            if (Math.abs(deletedRight.AVLHeight() - left.AVLHeight()) > 1) {
                return new AVLNode(this.data, left, deletedRight).AVLrotateRight();
            } else {
                return new AVLNode(this.data, left, deletedRight);
            }
        }
    }

    Pair<Integer, AVL> AVLshrink() throws EmptyAVLE {
        if (this.height == 1) {
            return new Pair<>(this.data, EAVL);
        } else if (this.right.isEmpty()) {
            if (this.left.isEmpty()) {
                return new Pair<>(this.data, EAVL);
            } else {
                return new Pair<>(this.data, this.left);
            }
        } else {
            Pair<Integer, AVL> temp = this.right.AVLshrink();
            AVL postDeletion = new AVLNode(this.data, left, temp.getSecond());

            if (Math.abs(postDeletion.AVLLeft().AVLHeight() - postDeletion.AVLRight().AVLHeight()) > 1) {
                if (postDeletion.AVLLeft().AVLHeight() < postDeletion.AVLRight().AVLHeight()) {
                    AVL balanced = postDeletion.AVLrotateLeft();
                    return new Pair<>(temp.getFirst(), balanced);
                } else {
                    AVL balanced = postDeletion.AVLrotateRight();
                    return new Pair<>(temp.getFirst(), balanced);
                }
            } else {
                return new Pair<>(temp.getFirst(), new AVLNode(this.data, left, temp.getSecond()));
            }
        }
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
