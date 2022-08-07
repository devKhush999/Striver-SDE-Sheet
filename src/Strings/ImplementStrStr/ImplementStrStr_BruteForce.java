package Strings.ImplementStrStr;

public class ImplementStrStr_BruteForce {
    /******************************** Brute Force: Nested Loops ***************************************
     * Time Complexity: O(N * M)
        * where N and M is the length of string S and pattern respectively.
        * The outer loop runs from 0 to “N - M” and the inner loop runs from 0 to ‘M’.
     * Space Complexity: O(1)
        * Since we are using constant extra memory
     */
    public int containsSubstring(String s, String pattern) {
        int n = s.length();
        int m = pattern.length();

        // For every index 'i', we’re checking if there is a substring that is equal to ‘pattern’ or not,
        // hence we’re using nested loops for that. If found we return a starting index
        for (int i = 0; i <= n-m; i++){
            int j = 0;
            for (; j < m; j++){
                if (pattern.charAt(j) != s.charAt(i + j))
                    break;
            }
            if (j == m)     // String Pattern found in Original String
                return i;
        }
        // return s.indexOf(pattern);       // Inbuilt Short Solution
        return -1;                          // String Pattern not found in Original String
    }
}
