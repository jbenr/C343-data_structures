import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.BiFunction;

// -------------------------------------------------------

/**
 *
 * The abstract class for the four hash tables we will implement. The
 * file HashTableTest has four test cases that should produce the same
 * information as Figures 5.5, 5.11, 5.13, and 5.18 in the book. You
 * should implement many more test cases!!!
 *
 */
abstract class HashTable {
    abstract int getCapacity();
    abstract void setCapacity(int capacity);

    abstract void insert (int key);
    abstract void delete (int key);
    abstract boolean search (int key);

    abstract void rehash ();
}

// -------------------------------------------------------

/**
 *
 * An implementation of a hash table that uses separate chaining. The
 * constructor should take one arguments 'hf' of type HashFunction.
 * The bound should be extracted from the HashFunction and an ArrayList
 * of that bound should be created in the constructor. Each
 * entry in that ArrayList should be initialized to an empty linked list.
 * The three methods to insert, delete, and search
 * should be implemented as described in the book. You should also
 * implement a method rehash that resizes the hash table to the next prime
 * after doubling the capacity.
 */
class HashSeparateChaining extends HashTable {
    // finish the implementation and remove the abstract annotation

    private int capacity;
    private HashFunction hf;
    private ArrayList<LinkedList<Integer>> chains;

    HashSeparateChaining (HashFunction hf) {
        this.capacity = hf.getBound();
        this.hf = hf;
        this.chains = new ArrayList<>(capacity);
        for (int i=0; i<capacity; i++)
            chains.add(i, new LinkedList<>());
    }

    public String toString () {
        String result = "";
        for (int i=0; i<capacity; i++) {
            result += "chain[" + i + "] = ";
            result += chains.get(i).toString();
            result += "\n";
        }
        return result;
    }

    @Override
    int getCapacity() {
        return capacity;
    }

    @Override
    void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    void insert(int key) {
        LinkedList<Integer> keyList = chains.get(hf.apply(key));
        if (!keyList.contains(key))
            keyList.add(Integer.valueOf(key));
    }

    @Override
    void delete(int key) {
        LinkedList<Integer> keyList = chains.get(hf.apply(key));
        if (keyList.contains(key))
            keyList.remove(Integer.valueOf(key));
    }

    @Override
    boolean search(int key) {
        return chains.get(hf.apply(key)).contains(key);
    }

    @Override
    void rehash() {
        int newcap = BigInteger.valueOf(2*getCapacity()).nextProbablePrime().intValue();
        ArrayList<LinkedList<Integer>> oldchains = chains;
        chains = new ArrayList<>(newcap);
        int oldcap = capacity;
        int capacity = newcap;
        setCapacity(newcap);
        hf.setBound(newcap);

        for(int i = 0; i<getCapacity(); i++) {
            chains.add(i, new LinkedList<>());
        }

        for(int i = 0; i<oldcap; i++) {
            for(int item : oldchains.get(i)) {
                insert(item);
            }
        }
    }
}

// -------------------------------------------------------

/**
 *
 * Before implementing the next three variants of hash tables, we will
 * implement a small class hierarchy of hash tables entries. There are
 * three kinds of entries: an entry that contains an 'int' value, an
 * entry that is empty and hence available, and an entry that is
 * marked as deleted. A deleted entry is available for insertions but
 * cannot be marked as empty. See the book for details.
 *
 */

abstract class Entry {
    static Entry EMPTY = new Empty();
    static Entry DELETED = new Deleted();
    abstract boolean available ();
}

class Empty extends Entry {
    boolean available () { return true; }
    public String toString () { return ""; }
}

class Deleted extends Entry {
    boolean available () { return true; }
    public String toString () { return "X"; }
}

class Value extends Entry {
    int i;
    Value (int i) { this.i = i; }
    int get () { return this.i; }
    boolean available () { return false; }
    public String toString () { return String.valueOf(this.i); }
}

// -------------------------------------------------------

/**
 *
 * This class, although abstract, will have a constructor and an
 * implementation of each of the three methods: insert, delete, and
 * search.
 *
 * The constructor should take two arguments: an
 * argument 'hf' of type HashFunction, and an argument 'f' of type
 * BiFunction<Integer,Integer,Integer>. The constructor should create
 * an ArrayList of the given size and initialize each slot in that
 * array with an Empty entry. The constructor should also define a
 * BiFunction<Integer,Integer,Integer> called 'dhf' as follows:
 *
 *   this.dhf = (key,index) -> (hf.apply(key) + f.apply(key,index)) % size;
 *
 * This auxiliary hash function applies the regular hash function 'hf'
 * and then modifies the result using the BiFunction 'f' that depends
 * on the current index in the hash table. The subclasses for linear
 * probing, quadratic probing, and double hashing, will each pass an
 * appropriate function 'f' to control the auxiliary function.
 *
 * Each of the methods insert, delete, and search takes a value 'key'
 * to process. Each of the methods will involve a loop that iterates
 * over all the positions in the ArrayList. At iteration 'i', an
 * integer position is calculated using:
 *
 *   int h = dhf.apply(key,i)
 *
 * For insert: if the position 'h' is available then the value 'key'
 * is stored.
 *
 * For delete: if position 'h' has an entry of kind 'Value' and if the
 * stored integer is identical to 'key' then the entry is marked as
 * deleted.
 *
 * For search: if position 'h' is Empty then the item is not found. If
 * position 'h' has an entry of kind 'Value' and if the stored value
 * is identical to 'key' then the item is found.
 *
 */

class HashTableAux extends HashTable {
    private int capacity;
    private HashFunction hf;
    private BiFunction<Integer,Integer,Integer> f;
    private BiFunction<Integer,Integer,Integer> dhf;
    private ArrayList<Entry> slots;

    HashTableAux (HashFunction hf, BiFunction<Integer,Integer,Integer> f) {
        this.capacity = hf.getBound();
        this.hf = hf;
        this.f = f;
        this.dhf = (key,index) -> Math.floorMod(hf.apply(key) + f.apply(key,index), capacity);
        this.slots = new ArrayList<>(capacity);
        for (int i = 0; i< capacity; i++) this.slots.add(i, Entry.EMPTY);
    }

    // finish the implementation

    public String toString () {
        String result = "";
        for (int i = 0; i< capacity; i++) {
            result += "entry[" + i + "] = ";
            result += slots.get(i).toString();
            result += "\n";
        }
        return result;
    }

    int getCapacity() {
        return capacity;
    }

    void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void insert(int key) {
        for(int i = 0; i<capacity; i++) {
            int h = dhf.apply(key, i);
            if (slots.get(h).available()) {
                slots.set(h, new Value(key));
                return;
            }
        }
        rehash();
        insert(key);
    }

    public void delete(int key) {
        for(int i = 0; i<this.slots.size(); i++) {
            int h = dhf.apply(key, i);
            Entry e = this.slots.get(h);
            if (e instanceof Value) {
                Value v = (Value) e;
                if (v.i == key) {
                    this.slots.set((int) h, new Deleted());
                    return;
                }
            }
        }
    }

    public boolean search(int key) {
        for (int i = 0; i<capacity; i++){
            int h = dhf.apply(key, i);
            if (!(this.slots.get(h).available()) && this.slots.get(h).toString().equals(String.valueOf(key))) {
                return true;
            }
        }
        return false;
    }

    @Override
    void rehash() {
        BigInteger doubleCapacity = BigInteger.valueOf(capacity*2);
        capacity = doubleCapacity.nextProbablePrime().intValue();
        hf.setBound(capacity);

        ArrayList<Entry> oldSlot = slots;
        slots = new ArrayList<Entry>(capacity);

        for (int i = 0; i<capacity; i++) {
            slots.add(i, Entry.EMPTY);
        }

        for (int i = 0; i<oldSlot.size(); i++) {
            if (oldSlot.get(i) instanceof Value) {
                Value val = (Value) oldSlot.get(i);
                insert(val.get());
            }
        }
    }
}

// -------------------------------------------------------



class HashLinearProbing extends HashTableAux {
    HashLinearProbing(HashFunction hf) {
        super(hf, (key, f) -> f);
    }

}


// -------------------------------------------------------


class HashQuadProbing extends HashTableAux {
    HashQuadProbing(HashFunction hf) {
        super(hf, (key, f) -> f*f);
    }
    // write the constructor and uncomment
}


// -------------------------------------------------------


class HashDouble extends HashTableAux {
    HashDouble(HashFunction hf, HashFunction hf2) {
        super(hf, (key, f) -> f*hf2.apply(key));
    }
    // write the constructor and uncomment
}


// -------------------------------------------------------
