package Strings.AllMatchingIndicesInString;
import java.util.ArrayList;

// PRE_REQUISITE: "Pattern Matching: Brute Force"
// https://www.codingninjas.com/codestudio/problems/1115738
// https://www.geeksforgeeks.org/naive-algorithm-for-pattern-searching/

public class AllMatchingIndicesInString_Brute {
    /**************************** Brute Force Solution ********************************************
     * Time Complexity: O(m * n)
     * Space Complexity: O(1)
     */
    public ArrayList<Integer> findAllOccurrenceInString(String text, String pattern) {
        // Store the starting indices where the pattern is found each time
        ArrayList<Integer> startingIndices = new ArrayList<>();

        int n = text.length();
        int m = pattern.length();

        // No. of times pattern appears in the string
        int numberOfOccurrence = 0;

        for (int i = 0; i <= n - m; i++){
            int j = 0;
            for (j = 0; j < m; j++){
                if (text.charAt(i + j) != pattern.charAt(j))
                    break;
            }
            if (j == m) {
                startingIndices.add(i);
                numberOfOccurrence++;
            }
        }
        return startingIndices;
    }
}
