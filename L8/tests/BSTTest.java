import com.sun.source.tree.Tree;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class BSTTest {

    @Test
    public void kth () throws EmptyBSTE {
        BST bst = new EmptyBST();
        bst = bst.BSTinsert(4);
        bst = bst.BSTinsert(0);
        bst = bst.BSTinsert(2);
        bst = bst.BSTinsert(1);
        bst = bst.BSTinsert(3);
        bst = bst.BSTinsert(6);
        bst = bst.BSTinsert(5);
        bst = bst.BSTinsert(9);
        bst = bst.BSTinsert(8);
        bst = bst.BSTinsert(7);
        bst = bst.BSTinsert(11);
        bst = bst.BSTinsert(10);

        TreePrinter.print(bst);
        for (int i=0; i<12;i++) assertEquals(i,bst.kth(i));

        BST flipped = bst.flip();
        TreePrinter.print(flipped);
        for (int i=0; i<12;i++) assertEquals(11-i,flipped.kth(i));
    }
}