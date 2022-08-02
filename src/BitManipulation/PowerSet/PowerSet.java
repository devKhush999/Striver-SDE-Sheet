package BitManipulation.PowerSet;
import java.util.ArrayList;
import java.util.List;

// https://youtu.be/b7AYbpM5YrE
// Great Reading: https://takeuforward.org/data-structure/power-set-print-all-the-possible-subsequences-of-the-string/
// https://www.geeksforgeeks.org/power-set/
// https://www.geeksforgeeks.org/recursive-program-to-generate-power-set/

public class PowerSet {
    /********************************** Power Set ***************************************
     * Power set P(S) of a set S is the set of all subsets of S.
     * If S has n elements in it then P(s) will have 2n elements
     * Same as "All Sub-subsequence of String" in "Recursion & Backtracking"

     * Time Complexity: O(n * 2^n)
        * Reason: O(2^n) for the outer for loop and O(n) for the inner for loop.
     * Space Complexity: O(1)
        * Ignore output ArrayList Size
     */
    public List<String> powerSet(String s){
        int n = s.length();

        List<String> allSubSequences = new ArrayList<>();

        // (1 << n) gives 2^n, it just multiplies 2 to itself n times (Recall left shift) (1 * 2^n)
        // Consider all the subsequence of String of length n (total 2^n in number)
        // (1 << n) == 2^n
        for (int num = 0; num < (1 << n); num++){
            String subSequence = "";

            for (int i = 0; i < n; i++){
                // Check if 'ith' bit is set to 1 in "num" or not
                // If it is set to 1, consider the character at that index in our SubSequence
                // There are two ways to do this:
                // (1 & (num >> i)) == 1
                // (num & (1 << i)) != 0
                if ((num & (1 << i)) != 0)
                    subSequence += s.charAt(i);
            }

            // Add SubSequence into the all SubSequence ArrayList
            if (!subSequence.equals(""))
                allSubSequences.add(subSequence);
        }

        // Both sorting are same
        // Collections.sort(allSubSequences);
        allSubSequences.sort((a, b) -> a.compareTo(b));

        return allSubSequences;
    }
}
