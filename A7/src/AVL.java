//-----------------------------------------------------------------------
// Empty AVL exception

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class EmptyAVLE extends Exception {}
=======
import java.util.NoSuchElementException;

class EmptyAVLE extends Exception {
}
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9

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
<<<<<<< HEAD
    static BST toBST(AVL avl) throws EmptyAVLE, EmptyBSTE {
        ArrayList<Integer> lst = new ArrayList<>();
        while(!avl.isEmpty()){
            int temp = avl.AVLData();
            lst.add(temp);
            avl = avl.AVLdelete(temp);
        }

        BST bst = new EmptyBST();
        for (Integer i : lst) {
            bst = bst.BSTinsert(i);
        }
        return bst;
=======
    static BST toBST(AVL avl) {
        try {
            return new BSTNode(avl.AVLData(), toBST(avl.AVLLeft()), toBST(avl.AVLRight()));
        } catch (EmptyAVLE emptyAVLE) {
            return new EmptyBST();
        }
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    abstract int AVLData() throws EmptyAVLE;

    abstract AVL AVLLeft() throws EmptyAVLE;

    abstract AVL AVLRight() throws EmptyAVLE;

    abstract int AVLHeight();

    abstract boolean isEmpty();

    //--------------------------
    // Main methods
    //--------------------------

    abstract boolean AVLfind(int key);

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

    boolean isEmpty() {
        return true;
    }

    ;

    //--------------------------
    // Main methods
    //--------------------------

    boolean AVLfind(int key) {
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

    public boolean equals(Object o) {
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
<<<<<<< HEAD

        int lH = 1 + left.AVLHeight();
        int rH = 1 + right.AVLHeight();

        if (lH > rH) {
            height = lH;
        } else {
            height = rH;
        }
=======
        height = 1 + Math.max(left.AVLHeight(), right.AVLHeight());
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
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
<<<<<<< HEAD
=======

    ;
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9

    //--------------------------
    // Main methods
    //--------------------------

    /**
     * @noinspection Duplicates
     */
    boolean AVLfind(int key) {
<<<<<<< HEAD
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
=======
        boolean found = false;

        if (key == this.data) {
            found = true;
        } else if (key < this.data) {
            found = left.AVLfind(key);
        } else {
            found = right.AVLfind(key);
        }
        return found;
    }

    AVL AVLinsert(int key) {
        AVL b;
        if (key < this.data) {
            AVL newLeft = left.AVLinsert(key);
            b = new AVLNode(data, newLeft, right);

            if (right.AVLHeight()+1 < newLeft.AVLHeight()) {
                b = b.AVLrotateRight();
            }
        } else {
            AVL newRight = right.AVLinsert(key);
            b = new AVLNode(data, left, newRight);
            if (left.AVLHeight()+1 < newRight.AVLHeight() ) {
                b = b.AVLrotateLeft();
            }
        }




        return b;
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    AVL AVLeasyRight() {
        try {
<<<<<<< HEAD
            AVL modifiedRight = new AVLNode(this.data, this.left.AVLRight(), this.right);
            return new AVLNode(this.left.AVLData(), this.left.AVLLeft(), modifiedRight);
        } catch (EmptyAVLE e) { return EAVL; }
=======
            AVL newRoot = left;
            AVL danglingMan = newRoot.AVLRight();

            return new AVLNode(newRoot.AVLData(), newRoot.AVLLeft(),  new AVLNode(data, danglingMan,right));


        } catch (EmptyAVLE e) {
            throw new Error("Why are you rotating something that's empty?");
        }
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }


    AVL AVLrotateRight() {
        try {
<<<<<<< HEAD

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
=======
            AVL finalTree;
            if (left.AVLLeft().AVLHeight() >= left.AVLRight().AVLHeight()) {
                finalTree = this.AVLeasyRight();
            } else {
                AVL newLeft = left.AVLeasyLeft();
                AVL midwayTree = new AVLNode(data, newLeft, right);
                finalTree = midwayTree.AVLeasyRight();

            }


            return finalTree;
        } catch (EmptyAVLE E) {
            throw new Error ("Why are you rotating something that's empty?");
        }

>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }

    AVL AVLeasyLeft() {
        try {
<<<<<<< HEAD
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
=======
            AVL newRoot = right;
            AVL danglingMan = newRoot.AVLLeft();

            return new AVLNode(newRoot.AVLData(), new AVLNode(data, left, danglingMan), newRoot.AVLRight());


        } catch (EmptyAVLE e) {
            throw new Error("Why are you rotating something that's empty?");
        }
    }

    AVL AVLrotateLeft() {
       try{
           AVL finalTree;
           if(right.AVLRight().AVLHeight() >= right.AVLLeft().AVLHeight()){
               finalTree = this.AVLeasyLeft();
           }
           else{
               AVL newRight = right.AVLeasyRight();
               AVL midwayTree = new AVLNode(data, left, newRight);
               finalTree = midwayTree.AVLeasyLeft();

           }

           return finalTree;
       }catch(EmptyAVLE e){
           throw new Error("What are you doing rotating something empty?");
       }
    }

    AVL AVLdelete(int key) throws EmptyAVLE {
        AVL finalTree;

            if (key < data) {
                AVL newLeft = left.AVLdelete(key);
                finalTree = new AVLNode(data, newLeft, right);
                if (newLeft.AVLHeight() + 1 < right.AVLHeight()) {
                    finalTree = finalTree.AVLrotateLeft();
                }

            } else if (key > data) {
                AVL newRight = right.AVLdelete(key);
                finalTree = new AVLNode(data, left, newRight);
                if (newRight.AVLHeight() + 1 < left.AVLHeight()) {
                    finalTree = finalTree.AVLrotateRight();
                }
            } else {
                try {
                    Pair<Integer, AVL> largestElementOnLeftAndBalancedLeft = left.AVLshrink();
                    finalTree = new AVLNode(largestElementOnLeftAndBalancedLeft.getFirst(), largestElementOnLeftAndBalancedLeft.getSecond(), right);
                    if (largestElementOnLeftAndBalancedLeft.getSecond().AVLHeight() + 1 < right.AVLHeight()) {
                        finalTree = finalTree.AVLrotateLeft();
                    }

                } catch (EmptyAVLE e) {
                    //return right if "this" doesnt have a left
                    finalTree = right;
                }
            }


        return finalTree;

    }

    Pair<Integer, AVL> AVLshrink() throws EmptyAVLE {
       try{
           Pair<Integer, AVL> rightMostChildAndTreeWithoutIt = right.AVLshrink();

           AVL newLeft = new AVLNode(data, left, rightMostChildAndTreeWithoutIt.getSecond());

           if(left.AVLHeight() > rightMostChildAndTreeWithoutIt.getSecond().AVLHeight()+1){
               newLeft = newLeft.AVLrotateRight();
           }
           return new Pair<>(rightMostChildAndTreeWithoutIt.getFirst(), newLeft);

       }catch(EmptyAVLE e){
           return new Pair<Integer,AVL>(data, left);

       }
>>>>>>> 970c8099d69d25b9cdca7ab52f7a0f74023ffac9
    }


    //--------------------------
    // Override
    //--------------------------

    public boolean equals(Object o) {
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
