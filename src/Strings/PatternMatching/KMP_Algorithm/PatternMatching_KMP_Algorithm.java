package Strings.PatternMatching.KMP_Algorithm;

// MUST Watch Great Video with Deep Intuition: https://youtu.be/BXCEFAzhxGY
// LPS Array: https://youtu.be/JoF0Z7nVSrA
// https://youtu.be/V5-7GzOfADQ
// https://youtu.be/ziteu2FpYsA

// Great Article: https://www.scaler.com/topics/data-structures/kmp-algorithm/
// http://www.btechsmartclass.com/data_structures/knuth-morris-pratt-algorithm.html
// https://www.geeksforgeeks.org/naive-algorithm-for-pattern-searching/
// https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/

// It is a "String matching" or "Pattern matching" algorithms

public class PatternMatching_KMP_Algorithm {
    /************************************* KMP Algorithm *******************************************
     * For Intuition watch the Video (Pretty Hard Problem)

     * Idea: The basic idea of KMP is to generate and use LPS array here LPS means "Longest prefix which
        is also a suffix". For example, if a string is ‘aada’ then :-
             * Proper prefixes are : [a, aa, aad]
             * Proper suffix are : [a, da,ada].
        * The LPS array helps us to skip characters while matching pattern with original string.
        * Basically this algorithm works in two parts where the first part is to generate the LPS array
          and the second part is to use this array to skip characters while matching "pattern" with the
          string.

     * Why did we create the LPS table?
        From the problem we discussed above, the partial character matches were causing extra computation time.
        Those partial matches can be skipped by using the LPS table, hence avoiding the unnecessary odd
        comparisons on finding partial matches, and therefore we no longer need to backtrack the pointer
        on string (on which pattern is to be matched) that is the main idea of the algorithm

     * Time Complexity: O(m + n)
        * where N and M is the length of string S and (pattern) T respectively.
     * Space Complexity: O(m)
        * We require the space for "Longest Prefix Suffix (LPS) Array or Pi- Array"
          same size as that of length of pattern (m).
     */
    // Time Complexity : O(n)
    // Space Complexity: O(1)
    public boolean containsPattern(String s, String pattern){
        int n = s.length();
        int m = pattern.length();

        // In the LPS table, we map every character of the pattern to a value. The value mapped to each
        // character, i.e. LPS[i] represents the length of the longest proper prefix that is also a suffix
        // in the first i characters where 0 < i < length(pattern)-1
        // We use the LPS table to decide how many characters are to be skipped for comparison
        // when a mismatch has occurred.
        int[] LPS_Array = get_LPS_Array(pattern, m);

        // variables i and j which are the pointers iterating over the string and pattern respectively
        int i = 0, j = 0;

        // we iterate over the string with the condition i < n
        // In the naive algorithm, the 'i' pointer moved backward in the string.
        // However, here, the i pointer will not.
        while (i < n){
            // if we have a match, we will increment i and j both
            if (s.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            }
            else{
                if (j == 0) {
                    // If case of mismatch, and j == 0, then start comparing the first character of the
                    // pattern with the next character to the mismatched character in the String 's'
                    i++;
                }
                else{
                    // If case of mismatch, and j is greater than 0, we move j to LPS[j - 1]
                    // In order to find a "prefix" which is same as "suffix" ending with index 'j-1'
                    // When a mismatch occurs, check the LPS value of the character previous to the
                    // mismatched character in the pattern
                    // Then start comparing the character which is at an index value equal to the LPS
                    // value of the previous character to the mismatched character in pattern
                    // with the mismatched character in the Text.
                    j = LPS_Array[j - 1];
                }
            }
            // If j == M, then we have found complete ‘pattern’ match in the string so return true
            if (j == m)
                return true;
        }
        // Else if 'i' reaches 'n' (i == n), but j hasn't reached 'm' (j != m)
        // This implies, pattern not found. Hence, return false
        return false;
    }



    // Find the LPS Array (The Longest Prefix Suffix) of the String
    // Time Complexity: O(2 * m)        We may end up doing two traversal of String (via "prev_LPS_Length")
    // Space Complexity: O(m)
    // https://youtu.be/JoF0Z7nVSrA
    private int[] get_LPS_Array(String pattern, int m){
        // LPS value mapped to a character (i.e, LPS[i]) represents the length of the longest proper
        // prefix that is also a suffix
        int[] LPS_Array = new int[m];

        // variable for length of the previous longest prefix which is also the suffix (excluding the entire string)
        int prev_LPS_Length = 0, i = 1;

        while (i < m){
            // If the characters are matching, then length of the longest prefix also a suffix increases
            // for current character (current index 'i')
            if (pattern.charAt(i) == pattern.charAt(prev_LPS_Length)){
                prev_LPS_Length++;
                LPS_Array[i] = prev_LPS_Length;
                i++;
            }
            else{
                // If the characters aren't matching & length of previous LPS is 0.
                // Then for current character there is no LPS too, mark it's LPS (LPS[i] = 0) as 0, and move ahead
                if (prev_LPS_Length == 0){
                    LPS_Array[i] = 0;
                    i++;
                }
                // If the characters aren't matching & length of previous LPS is not 0.
                // Then for current character there can't exist a LPS with size greater than or equal to
                // previous LPS size (bcoz character are no more matching) (think...).
                // So, for current character, find a LPS of smaller size by moving to LPS[prev_LPS - 1]
                else{
                    prev_LPS_Length = LPS_Array[prev_LPS_Length - 1];
                }
            }
        }
        return LPS_Array;
    }
}
