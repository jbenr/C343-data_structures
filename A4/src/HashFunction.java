
import java.util.Random;
import java.util.function.Function;

// -------------------------------------------------------

abstract class HashFunction implements Function<Integer,Integer> {
    abstract int getBound ();
    abstract void setBound (int bound);
}

// -------------------------------------------------------

/**
 *
 * An instance of this class is created with a parameter 'bound'. Then
 * every time the hash function is applied to an argument 'x', it
 * returns the value of 'x' modulo the 'bound'. See the test cases in
 * HashFunctionTest for some examples.
 *
 */
class HashMod extends HashFunction {
    // complete the implementation and remove the abstract annotation
    private int bound;

    HashMod(int bound) {
        this.bound = bound;
    }

    public Integer apply (Integer x) {
        return Math.floorMod(x, bound);
    }

    @Override
    int getBound() {
        return bound;
    }

    @Override
    void setBound(int bound) {
        this.bound = bound;
    }
}

// -------------------------------------------------------

/**
 *
 * An instance of this class is created with two parameters: a first
 * argument 'bound' that is used to create an instance of the
 * superclass, and a second argument 'after' of type
 * Function<Integer,Integer> that is used as follows. When the hash
 * function is invoked, the basic functionality of the super class is
 * first invoked, and then that result is given to the function
 * 'after'. The result of 'after' is also wrapped around so that it does not exceed bound.
 * See the test cases in HashFunctionTest for some examples.
 *
 */
class HashModThen extends HashMod {
    private final Function<Integer, Integer> after;

    HashModThen(int bound, Function<Integer, Integer> after) {
        super(bound);
        this.after = after;
    }
    // complete the implementation and remove the abstract annotation

    public Integer apply(Integer x) {
        Integer result = super.apply(x);
        result = after.apply(result);
        return Math.floorMod(result, super.getBound());
    }
}

// -------------------------------------------------------

/**
 *
 * An instance of this class is created with three parameters: a
 * random number generator 'r' of type Random and two integers 'p' and
 * 'm' where 'p' should be a prime number greater than or equal to
 * 'm'. Using the random number generator, the constructor generates
 * two random numbers 'a' and 'b' such that 'a' is in the range 1
 * (inclusive) and p (exclusive) and 'b' is in the range 0 (inclusive)
 * and p (exclusive). The resulting hash function should be a
 * universal hash function as explained in the book. Again see the
 * test cases in HashFunctionTest for some examples.
 *
 */
class HashUniversal extends HashFunction {
    Random r = new Random();
    private int p, m, a, b;

    // complete the implementation and remove the abstract annotation
    HashUniversal(Random r, Integer p, Integer m) {
        this.m = m;
        this.p = p;
        a = r.nextInt(p-1) + 1;
        b = r.nextInt(p);
    }

    @Override
    public Integer apply(Integer x) {
        return Math.floorMod(Math.floorMod(x*a + b, p), m);
    }

    @Override
    int getBound() {
        return m;
    }

    @Override
    void setBound(int bound) {
        m = bound;
    }
}

// -------------------------------------------------------
// -------------------------------------------------------
