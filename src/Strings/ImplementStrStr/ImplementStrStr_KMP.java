package Strings.ImplementStrStr;

// PRE-REQUISITE: "KMP Algorithm : Pattern Matching"
// MUST Watch Great Video with Deep Intuition: https://youtu.be/BXCEFAzhxGY
// LPS Array: https://youtu.be/JoF0Z7nVSrA
// https://youtu.be/V5-7GzOfADQ
// https://youtu.be/ziteu2FpYsA

// Great Article: https://www.scaler.com/topics/data-structures/kmp-algorithm/
// http://www.btechsmartclass.com/data_structures/knuth-morris-pratt-algorithm.html
// https://www.geeksforgeeks.org/naive-algorithm-for-pattern-searching/
// https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/

public class ImplementStrStr_KMP {
    /******************************* KMP Pattern Matching Algorithm **********************************
     * For more details see KMP Algorithm
     * Time Complexity: O(m + n)
     * Space Complexity: O(1)
     */
    // KMP Algorithm
    public int strStr(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        int[] lps = get_LPS_Array(pattern, m);
        int i = 0, j = 0;

        while (i < n){
            if (text.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            }
            else{
                if (j != 0)
                    j = lps[j - 1];
                else
                    i++;
            }
            // Instead of returning true, we have to return starting index. Since, when j reaches m
            // (j == m), both text & pattern matched fully, so, "i - m" will be the starting index of
            // pattern
            if (j == m)
                return i - m;
        }
        return -1;
    }

    // Find the LPS Array (The Longest Prefix Suffix) of the String
    public int[] get_LPS_Array(String pattern, int m){
        int[] LPS_Array = new int[m];
        int prev_LPS_Length = 0, i = 1;

        while (i < m){
            if (pattern.charAt(i) == pattern.charAt(prev_LPS_Length)){
                prev_LPS_Length++;
                LPS_Array[i] = prev_LPS_Length;
                i++;
            }
            else{
                if (prev_LPS_Length == 0){
                    LPS_Array[i] = 0;
                    i++;
                }
                else{
                    prev_LPS_Length = LPS_Array[prev_LPS_Length - 1];
                }
            }
        }
        return LPS_Array;
    }
}
