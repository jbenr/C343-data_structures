import com.sun.source.tree.Tree;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class AVLTest {
    BST bst;
    AVL avl;

    @Before
    public void setUp() {
        List<Integer> nums = IntStream.range(0, 8).boxed().collect(Collectors.toList());
        Collections.shuffle(nums);
        bst = BST.EBST;
        avl = AVL.EAVL;
        for (int i : nums) {
            bst = bst.BSTinsert(i);
            avl = avl.AVLinsert(i);
        }
    }

    @Test
    public void AVLinsert() {
        avl = AVL.AVLLeaf(2);
        avl = avl.AVLinsert(1);
        avl = avl.AVLinsert(3);
        assertTrue(avl.AVLfind(3)); //find
        avl = avl.AVLinsert(5);
        avl = avl.AVLinsert(7);
        assertEquals(3, avl.AVLHeight());
    }

    @Test
    public void toAVL() throws EmptyAVLE, EmptyBSTE {

        bst = new BSTNode(1, BST.EBST, new BSTNode(2, BST.EBST, BST.BSTLeaf(3)));
        TreePrinter.print(bst);
        AVL avl = BST.toAVL(bst);
        TreePrinter.print(avl);

        BST bst2 = AVL.toBST(BST.toAVL(bst));
        Iterator<Integer> bstIter = bst.iterator();
        Iterator<Integer> bst2Iter = bst2.iterator();
        while (bstIter.hasNext() && bst2Iter.hasNext()) {
            assertEquals((int) bstIter.next(), (int) bst2Iter.next());
        }
    }

    @Test
    public void AVLeasyRight() throws EmptyAVLE {
        avl = new AVLNode(40, new AVLNode(20, new AVLNode(10, AVL.EAVL, AVL.AVLLeaf(15)), AVL.AVLLeaf(30)), AVL.AVLLeaf(50));
        assertTrue(avl.AVLeasyRight().AVLHeight() < avl.AVLHeight());

        avl = AVL.EAVL;
        avl = avl.AVLinsert(40);
        TreePrinter.print(avl);
        avl = avl.AVLinsert(50);
        TreePrinter.print(avl);
        avl = avl.AVLinsert(20);
        TreePrinter.print(avl);
        avl = avl.AVLinsert(10);
        TreePrinter.print(avl);
        avl = avl.AVLinsert(30);
        TreePrinter.print(avl);
        avl = avl.AVLinsert(15);
        TreePrinter.print(avl);

        AVL left = avl.AVLLeft();
        AVL right = avl.AVLRight();

        assertTrue(Math.abs(left.AVLHeight() - right.AVLHeight()) <= 1);

        assertEquals(20, avl.AVLData());
        assertEquals(10, left.AVLData());
        assertEquals(15, left.AVLRight().AVLData());
        assertEquals(40, right.AVLData());
        assertEquals(30, right.AVLLeft().AVLData());
        assertEquals(50, right.AVLRight().AVLData());
    }

    @Test
    public void AVLeasyLeft() throws EmptyAVLE {
        avl = AVL.EAVL;
        avl = avl.AVLinsert(40);
        TreePrinter.print(avl);
        avl = avl.AVLinsert(50);
        TreePrinter.print(avl);
        avl = avl.AVLinsert(20);
        TreePrinter.print(avl);
        avl = avl.AVLinsert(45);
        TreePrinter.print(avl);
        avl = avl.AVLinsert(60);
        TreePrinter.print(avl);
        avl = avl.AVLinsert(59);
        TreePrinter.print(avl);

        assertTrue(Math.abs(avl.AVLLeft().AVLHeight() - avl.AVLRight().AVLHeight()) < 2);
    }

    @Test
    public void AVLrotateRight() throws EmptyAVLE {
        avl = AVL.EAVL;
        avl = avl.AVLinsert(40);
        TreePrinter.print(avl);
        avl = avl.AVLinsert(50);
        TreePrinter.print(avl);
        avl = avl.AVLinsert(20);
        TreePrinter.print(avl);
        avl = avl.AVLinsert(10);
        TreePrinter.print(avl);
        avl = avl.AVLinsert(30);
        TreePrinter.print(avl);
        TreePrinter.print(avl.AVLrotateRight());
        avl = avl.AVLinsert(25);
        TreePrinter.print(avl);

        AVL left = avl.AVLLeft();
        AVL right = avl.AVLRight();
        assertEquals(30, avl.AVLData());
        assertEquals(20, left.AVLData());
        assertEquals(10, left.AVLLeft().AVLData());
        assertEquals(25, left.AVLRight().AVLData());
        assertEquals(40, right.AVLData());
        assertEquals(50, right.AVLRight().AVLData());
    }

    @Test
    public void AVLrotateLeft() throws EmptyAVLE {

        avl = AVL.EAVL;
        avl = avl.AVLinsert(40);
        TreePrinter.print(avl);
        avl = avl.AVLinsert(50);
        TreePrinter.print(avl);
        avl = avl.AVLinsert(20);
        TreePrinter.print(avl);
        avl = avl.AVLinsert(45);
        TreePrinter.print(avl);
        avl = avl.AVLinsert(60);
        TreePrinter.print(avl);
        avl = avl.AVLinsert(46);
        TreePrinter.print(avl);

        assertTrue(Math.abs(avl.AVLLeft().AVLHeight() - avl.AVLRight().AVLHeight()) < 2);
    }

    @Test
    public void AVLdelete() throws EmptyAVLE {
        avl = AVL.EAVL;
        avl = avl.AVLinsert(35);
        avl = avl.AVLinsert(20);
        avl = avl.AVLinsert(40);
        avl = avl.AVLinsert(7);
        avl = avl.AVLinsert(30);
        avl = avl.AVLinsert(50);
        avl = avl.AVLinsert(5);
        avl = avl.AVLinsert(10);
        TreePrinter.print(avl);

        AVL avl2 = avl.AVLdelete(35);
        TreePrinter.print(avl2);
        AVL left = avl2.AVLLeft();
        AVL right = avl2.AVLRight();
        assertEquals(30, avl2.AVLData());
        assertEquals(7, left.AVLData());
        assertEquals(5, left.AVLLeft().AVLData());
        assertEquals(20, left.AVLRight().AVLData());
        assertEquals(10, left.AVLRight().AVLLeft().AVLData());
        assertEquals(40, right.AVLData());
        assertEquals(50, right.AVLRight().AVLData());




        int n = 50;
        int l = -20;
        int g = 20;


        avl = new EmptyAVL();
        List<Integer> nums = new ArrayList<>();
        List<Integer> numc = new LinkedList<>();

        HashSet<Integer> seen = new HashSet<Integer>();
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            int r = l + rand.nextInt(g - l);
            if (!seen.contains(r)){
                nums.add(r);
                numc.add(r);
                seen.add(r);
            }
        }
        n = seen.size();
        for (int i = 0; i < n; i++) {

            avl = avl.AVLinsert(nums.get(i));
        }

        try {
            avl.AVLdelete(10000000);
            fail("AVL doesn't throw an exception when a number that is not in the AVL is deleted.");
        } catch (EmptyAVLE e) {
        }
    }

    @Test
    public void stuff() throws EmptyAVLE {
        avl = AVL.EAVL;
        avl = avl.AVLinsert(44);
        avl = avl.AVLinsert(17);
        avl = avl.AVLinsert(78);
        avl = avl.AVLinsert(32);
        avl = avl.AVLinsert(50);
        avl = avl.AVLinsert(88);
        avl = avl.AVLinsert(48);
        avl = avl.AVLinsert(62);
        TreePrinter.print(avl);
        avl = avl.AVLdelete(32);
        TreePrinter.print(avl);
        System.out.println(avl.AVLHeight());
    }
}