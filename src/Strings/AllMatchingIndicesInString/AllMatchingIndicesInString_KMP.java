package Strings.AllMatchingIndicesInString;
import java.util.ArrayList;

// PRE-REQUISITE: "KMP Algorithm : Pattern Matching" Check out
// MUST Watch Great Video with Deep Intuition: https://youtu.be/BXCEFAzhxGY
// LPS Array: https://youtu.be/JoF0Z7nVSrA

// Problem: https://www.codingninjas.com/codestudio/problems/1115738
// Great Article: https://www.scaler.com/topics/data-structures/kmp-algorithm/

public class AllMatchingIndicesInString_KMP {
    /******************************* KMP Pattern Matching Algorithm **********************************
     * For more details see KMP Algorithm
     * Time Complexity: O(m + n)
     * Space Complexity: O(1)
     */
    // KMP Algorithm
    public ArrayList<Integer> findAllOccurrenceInString(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        // Store the starting indices where the pattern is found each time
        ArrayList<Integer> startingIndices = new ArrayList<>();

        // No. of times pattern appears in the string
        int occurrenceOfPattern = 0;

        int[] lpsArray = getLPSArray(pattern, m);
        int i = 0, j = 0;

        while (i < n){
            if (text.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            }
            else{
                if (j == 0)
                    i++;
                else
                    j = lpsArray[j - 1];
            }
            // This is something new as in KMP algorithm
            // Since, when j reaches m (j == m), both text & pattern matched fully.
            // So, "i - m" will be the starting index of pattern.
            // But, pattern can be repeating more than one time in the text. For eg (text = "AAAAAAA", pattern = "AA")
            // When j becomes m, we can consider this too as a "mismatch case" in text & pattern
            // (though actually, it is not a mismatch, as we ran out of characters in pattern).
            // So, in this case, we do the same thing as we did in mismatch condition (j != 0), we try to
            // look for suffix same as prefix, at the index (j = m-1) before the mismatch happens (j = m).
            // And in this way, we can continue our progress of matching the text & pattern from the index just
            // after the prefix (same as suffix) in the same way as we did in mismatch condition with j != 0.
            if (j == m){
                startingIndices.add(i - m);
                occurrenceOfPattern++;
                j = lpsArray[j - 1];
            }
        }
        return startingIndices;
    }


    // Find the LPS Array (The Longest Prefix Suffix) of the String
    public int[] getLPSArray(String pattern, int m){
        int[] lpsArray = new int[m];

        int prevLPSLength = 0;
        int i = 1;

        while (i < m){
            if (pattern.charAt(i) == pattern.charAt(prevLPSLength)){
                lpsArray[i++] = ++prevLPSLength;
            }
            else{
                if (prevLPSLength == 0){
                    lpsArray[i++] = 0;
                }
                else{
                    prevLPSLength = lpsArray[prevLPSLength - 1];
                }
            }
        }
        return lpsArray;
    }
}
