import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class HashTableTest {
    @Test
    public void hashSeparateChaining () {
        Random r = new Random(1);
        HashFunction hf = new HashUniversal(r,31, 10);
        HashTable ht = new HashSeparateChaining(hf);
        ht.insert(1);
        ht.insert(12);
        assertTrue(ht.search(12));
        ht.delete(12);
        assertFalse(ht.search(12));
        assertTrue(ht.search(1));
        assertFalse(ht.search(2));
        ht.insert(22);
        System.out.println("Before rehashing");
        System.out.println(ht);
        ht.rehash();
        System.out.println("After rehashing");
        System.out.println(ht);
    }

    @Test
    public void HashLinearProbing() {
        HashFunction hf = new HashMod(10);
        HashTable ht = new HashLinearProbing(hf);
        ht.insert(3);
        ht.insert(4);
        ht.insert(5);
        assertEquals(true, ht.search(3));
        ht.delete(3);
        assertEquals(false, ht.search(3));
        System.out.println(ht);
    }

    @Test
    public void HashQuadProbing() {
        HashFunction hf = new HashMod(10);
        HashTable ht = new HashQuadProbing(hf);
        ht.insert(3);
        ht.insert(4);
        ht.insert(5);
        assertEquals(true, ht.search(3));
        ht.delete(3);
        assertEquals(false, ht.search(3));
        System.out.println(ht);
    }

    @Test
    public void HashDouble() {
        HashFunction hf = new HashMod(3);
        HashFunction hf2 = new HashMod(5);
        HashTable ht = new HashDouble(hf,hf2);
        ht.insert(2);
        assertEquals(false, ht.search(5));
        assertEquals(true, ht.search(2));
    }

    @Test
    public void fig55 () {
        HashFunction hf = new HashMod(10);
        HashTable ht = new HashSeparateChaining(hf);
        ht.insert(0);
        ht.insert(81);
        ht.insert(64);
        ht.insert(49);
        ht.insert(9);
        ht.insert(36);
        ht.insert(25);
        ht.insert(16);
        ht.insert(4);
        ht.insert(1);
        System.out.println("Fig. 5.5");
        System.out.println(ht);
    }

    @Test
    public void fig511 () {
        HashFunction hf = new HashMod(10);
        HashTable ht = new HashLinearProbing(hf);
        ht.insert(89);
        ht.insert(18);
        ht.insert(49);
        ht.insert(58);
        ht.insert(69);
        System.out.println("Fig. 5.11");
        System.out.println(ht);
    }

    @Test
    public void fig513 () {
        HashFunction hf = new HashMod(10);
        HashTable ht = new HashQuadProbing (hf);
        ht.insert(89);
        ht.insert(18);
        ht.insert(49);
        ht.insert(58);
        ht.insert(69);
        System.out.println("Fig. 5.13");
        System.out.println(ht);
        ht.insert(26);
        ht.insert(72);
        ht.insert(95);
        System.out.println("Before rehash");
        System.out.println(ht);
        ht.rehash();
        System.out.println("After rehash");
        System.out.println(ht);
    }

    @Test
    public void fig518 () {
        HashFunction hf = new HashMod(10);
        HashFunction hf2 = new HashModThen(7, h -> 7 - h);
        HashTable ht = new HashDouble (hf, hf2);
        ht.insert(89);
        ht.insert(18);
        ht.insert(49);
        ht.insert(58);
        ht.insert(69);
        System.out.println("Fig. 5.18");
        System.out.println(ht);
    }

    @Test
    public void hashSCgetCapacity () {
        HashFunction hf = new HashMod(10);
        HashTable ht = new HashSeparateChaining(hf);
        assertEquals(10, ht.getCapacity());
    }

    @Test
    public void hashSCsetCapacity () {
        HashFunction hf = new HashMod(10);
        HashTable ht = new HashSeparateChaining(hf);
    }

    @Test
    public void hashLPgetCapacity () {
        HashFunction hf = new HashMod(10);
        HashTable ht = new HashLinearProbing(hf);
        assertEquals(10, ht.getCapacity());
    }

    @Test
    public void hashQPgetCapacity () {
        HashFunction hf = new HashMod(10);
        HashTable ht = new HashQuadProbing(hf);
        assertEquals(10, ht.getCapacity());
    }



    @Test
    public void hashDgetCapacity () {
        HashFunction hf = new HashMod(10);
        HashFunction hf1 = new HashMod(10);
        HashTable ht = new HashDouble(hf, hf1);
        assertEquals(10, ht.getCapacity());
    }

}