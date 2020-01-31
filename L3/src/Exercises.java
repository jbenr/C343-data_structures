import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;


// ------------------------------------------------------------------------
// Solve the following 5 exercises using the five approaches.
// Examples and explanations of the five approaches can be found in Main.java.
// ------------------------------------------------------------------------

public class Exercises {
    private static List<Integer> ints = new ArrayList<>(Arrays.asList(1,2,3,4,5));

    // ------------------------------------------------------------------------
    // Exercise I: multiply the elements in the list
    //
    // Thinking point:
    // Take note that there isn't a method using the map-reduce approach. Why?
    // ------------------------------------------------------------------------

    static int mul1 (List<Integer> lst) {
        //for loop
        int result = 1;
        for (int i = 0; i < ints.size(); i++) {
            result = result * ints.get(i);
        }
        return result;
    }

    static int mul2 (List<Integer> lst) {
        //foreach approach
        int result = 1;
        for (int i: ints){
            result = result * i;
        }
        return result;
    }

    static int mul3 (List<Integer> lst) {
        //iterator approach
        int result = 1;
        ListIterator<Integer> iterator = ints.listIterator();
        while(iterator.hasNext()){
            int k = iterator.next();
            result = result * k;
        }
        return result;
    }

    static int mul5 (List<Integer> lst) {
	//reduce approach
        int mulReduce = ints.stream().reduce(1, (result, i) -> result * i);
        return mulReduce;
    }

    // ------------------------------------------------------------------------
    // Exercise II: check if all elements in the list are even
    // ------------------------------------------------------------------------

    static boolean even1 (List<Integer> ints) {
        //for loop approach
        Boolean result = true;
        for (int i = 0; i < ints.size(); i++) {
            if ((ints.get(i) % 2) != 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    static boolean even2 (List<Integer> ints) {
        //foreach approach
        Boolean result = true;
        for(int i: ints) {
            if ((i % 2) != 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    static boolean even3 (List<Integer> ints) {
        //iterator approach
        Boolean result = true;
        ListIterator<Integer> iterator = ints.listIterator();
        while(iterator.hasNext()){
            int k = iterator.next();
            if((k % 2) != 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    static boolean even4 (List<Integer> ints) {
	//map-reduce approach
        boolean evenMapreduce = ints.stream().map(k -> k % 2 == 0).reduce(true, (result, k) -> result == k);
        return evenMapreduce;
    }

    static boolean even5 (List<Integer> ints) {
	//reduce approach
        boolean evenReduce = ints.stream().reduce(true, (result, k) -> result && k % 2 == 0, (r1, r2) -> r1 && r2 );
        return evenReduce;
    }

    // ------------------------------------------------------------------------
    // Exercise III: compute the maximum number in the list
    //
    // Thinking point:
    // Take note that there isn't a method using the map-reduce approach. Why?
    // ------------------------------------------------------------------------

    static int max1 (List<Integer> ints) {
        //for loop approach
        int max = ints.get(0);
        for(int i = 0; i < ints.size(); i++) {
            if (ints.get(i) > max) {
                max = ints.get(i);
            }
        }
        return max;

    }

    static int max2 (List<Integer> ints) {
        //foreach approach
        int max = ints.get(0);
        for(int i: ints) {
            if (ints.get(i) > max) {
                max = ints.get(i);
            }
        }
        return max;
    }

    static int max3 (List<Integer> ints) {
        //iterator approach
        ListIterator<Integer> iterator = ints.listIterator();
        int max = iterator.next();
        while(iterator.hasNext()){
            int i = iterator.next();
            if(i > max){
                max = i;
            }
        }
        return max;
    }

    static int max5 (List<Integer> ints) {
	//reduce approach
        int maxReduce = ints.stream().reduce(ints.get(0), (max, i) -> i > max ? i : max);
        return maxReduce;
    }

    // ------------------------------------------------------------------------
    // Exercise IV: count occurrences of c in the list
    // ------------------------------------------------------------------------

    static int count1 (int c, List<Integer> ints) {
        //for loop approach
        int count = 0;
        for (int i = 0; i < ints.size(); i++) {
            if(ints.get(i) == c){
                count++;
            }
        }
        return count;
    }

    static int count2 (int c, List<Integer> ints) {
        //foreach approach
        int count = 0;
        for (int i: ints){
            if(i == c){
                count++;
            }
        }
        return count;
    }

    static int count3 (int c, List<Integer> ints) {
        //iterator approach
        ListIterator<Integer> iterator = ints.listIterator();
        int count = 0;
        while(iterator.hasNext()){
            int i = iterator.next();
            if(i == c){
                count++;
            }
        }
        return count;
    }

    static int count4 (int c, List<Integer> ints) {
	//map-reduce approach
        int countMapreduce = ints.stream().map(k -> k == c ? 1 : 0).reduce(0, (count, k) -> count + k);
        return countMapreduce;
    }

    static int count5 (int c, List<Integer> ints) {
	//reduce approach
        int countReduce = ints.stream().reduce(0, (count, i) -> c == i ? ++count : count);
        return countReduce;
    }

    // ------------------------------------------------------------------------
    // Exercise V: triplicate each element in the list
    //
    // Thinking point:
    // Take note that there isn't a solution for this using the reduce approach. Why?
    // ------------------------------------------------------------------------
    
    static List<Integer> trip1 (List<Integer> lst) {
        //for loop approach
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < ints.size(); i++) {
            for(int j = 0; j < 3; j++){
                result.add(ints.get(i));
            }
        }
        return result;
    }

    static List<Integer> trip2 (List<Integer> lst) {
        //foreach approach
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i: ints){
            result.add(ints.get(i));
            result.add(ints.get(i));
            result.add(ints.get(i));
        }
        return result;
    }

    static List<Integer> trip3 (List<Integer> lst) {
        //iterator approach
        ArrayList<Integer> result = new ArrayList<Integer>();

        return result;
    }

    static List<Integer> trip4 (List<Integer> lst) {
	//map-reduce approach
        return new ArrayList<Integer>();
    }

}
