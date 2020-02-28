import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.WeakHashMap;

class minDistance {
    final static int GAP = 2;
    final static int MATCH = 0;
    final static int MISMATCH = 1;

    enum BASE { A, T, G, C }

    // recursive min distance
    static int minDistance (List<BASE> dna1, List<BASE> dna2) {
        try {
            int current = dna1.getFirst() == dna2.getFirst() ? MATCH : MISMATCH;
            int d1 = current + minDistance(dna1.getRest(), dna2.getRest());
            int d2 = GAP + minDistance(dna1.getRest(), dna2);
            int d3 = GAP + minDistance(dna1, dna2.getRest());
            return d1 < d2 ? d1 : d2;
        }
        catch (EmptyListE e) {
            if (dna1.isEmpty()) return GAP * dna2.length();
            else return GAP * dna1.length();
        }
    }

    static Map<Pair<List<BASE>,List<BASE>>,Integer> minDistanceMemo = new WeakHashMap<>();

    // memoized (top down) min distance
    static int mminDistance (List<BASE> dna11, List<BASE> dna21) {
        return minDistanceMemo.computeIfAbsent(new Pair<>(dna11, dna21), p -> {
            List<BASE> dna1 = p.getFirst();
            List<BASE> dna2 = p.getSecond();
            try {
                int current = dna1.getFirst() == dna2.getFirst() ? MATCH : MISMATCH;
                int d1 = current + mminDistance(dna1.getRest(), dna2.getRest());
                int d2 = GAP + mminDistance(dna1.getRest(), dna2);
                int d3 = GAP + mminDistance(dna1, dna2.getRest());
                return Math.min(d1,Math.min(d2,d3));
            }
            catch (EmptyListE e) {
                if (dna1.isEmpty()) return GAP * dna2.length();
                else return GAP * dna1.length();
            }
        });
    }

    // bottom up min distance
    static int buminDistance (List<BASE> dna11, List<BASE> dna21) {
        ArrayList<BASE> dna1 = dna11.toArrayList();
        ArrayList<BASE> dna2 = dna21.toArrayList();

        int size1 = dna1.size()+1;
        int size2 = dna2.size()+1;

        int[][] matrix = new int[size2][size1];
        matrix[0][0] = 0;

        for (int i = 1; i < size2; i++) {
            matrix[0][i] = matrix[0][i-1] + GAP;
        }

        for (int i = 1; i < size2; i++) {
            matrix[i][0] = matrix[i-1][0] + GAP;
        }

        for(int row = 1; row < size2; row++) {
            for (int col = 1; col < size1; col++) {

                int side = matrix[row][col-1];
                int up = matrix[row-1][col];
                int diag = matrix[row-1][col-1];

                if(dna1.get(col -1) == dna2.get(row-1)) {
                    matrix[row][col] = diag;
                } else {
                    up += GAP;
                    side += GAP;
                    diag += MISMATCH;

                    ArrayList<Integer> cost = new ArrayList<Integer>();
                    cost.add(up);
                    cost.add(side);
                    cost.add(diag);

                    int min = up;
                    for (int i : cost){
                        if(i < min) {
                            min = i;
                        }
                    }

                    matrix[row][col] = min;
                }
            }
        }

        return matrix[size2-1][size1-1];
    }
}
