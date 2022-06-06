package Arrays.PascalTriangle;
import java.util.ArrayList;
import java.util.List;

// https://youtu.be/6FLvhQjZqvM
// https://takeuforward.org/data-structure/program-to-generate-pascals-triangle/

public class PascalTriangle {

    // ****************************** Version 1 ******************************
    // T.C -> O(n*n)
    // S.C -> O(n*n)

    public List<List<Integer>> generatePascals_(int n) {
        List<List<Integer>> allPascals = new ArrayList<>();

        for (int i = 0; i < n; i++){
            ArrayList<Integer> currentPascal = new ArrayList<>();
            allPascals.add(currentPascal);

            currentPascal.add(1);

            for (int j = 1; j <= i; j++){
                int prevPascal1 = allPascals.get(i-1).get(j-1);
                int prevPascal2 = allPascals.get(i-1).size() == j ?  0 : allPascals.get(i-1).get(j);

                currentPascal.add(prevPascal1 + prevPascal2);
            }
        }

        return allPascals;
    }



    // ****************************** Version 2 ******************************
    // T.C -> O(n*n)
    // S.C -> O(n*n)

    public List<List<Integer>> generatePascals(int n) {
        List<List<Integer>> allPascals = new ArrayList<>();

        for (int i = 0; i < n; i++){
            ArrayList<Integer> currentPascal = new ArrayList<>();

            for (int j = 0; j <= i; j++){
                if (j == 0)
                    currentPascal.add(1);

                else if (j == i)
                    currentPascal.add(1);

                else{
                    int prevPascal1 = allPascals.get(i-1).get(j-1);
                    int prevPascal2 = allPascals.get(i-1).get(j);
                    currentPascal.add(prevPascal1 + prevPascal2);
                }
            }

            allPascals.add(currentPascal);
        }
        return allPascals;
    }
}
