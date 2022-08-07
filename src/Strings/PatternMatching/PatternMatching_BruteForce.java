package Strings.PatternMatching;

public class PatternMatching_BruteForce {
    /******************************** Brute Force: Nested Loops ***************************************
     * Basic brute approach is to check all substrings of string ‘S’ and see if that substring is equal
        to ‘pattern’ or not.
     * In the complete string, we need to find a substring that is equivalent to our pattern.
     * The sliding window algorithm would have a window size equal to the length of the pattern.
     * We would check substrings of length "m" starting from the left (0 index) to right.

     * Time Complexity: O(N * M)
        * where N and M is the length of string S and pattern respectively.
        * The outer loop runs from 0 to “N - M” and the inner loop runs from 0 to ‘M’.
     * Space Complexity: O(1)
        * Since we are using constant extra memory
     */
    public boolean containsSubstring(String s, String pattern) {
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
                return true;
        }
        // return s.contains(pattern);       // Inbuilt Short Solution
        return false;                  // String Pattern not found in Original String
    }
}
