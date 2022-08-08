package Strings.RepeatedStringMatch;

// PRE-Requisite: "Pattern Searching : KMP Algorithm & Rabin Karp Algorithm"
// https://www.geeksforgeeks.org/minimum-number-of-times-a-has-to-be-repeated-such-that-b-is-a-substring-of-it/

public class RepeatedStringMatch {
    /********************************** Efficient Solution: KMP Algorithm *****************************
     * Approach:
        * Idea is to iterate until String "b" is not contained in repetition of String "a"
        * In the best case, String "b" can be present in the repetition of String "a", when the
            length(repetition of a) becomes just greater than length(b).
        * So, we continuously repeat string "a" till the length(repetition of a) becomes greater
            than length(b).
        * Then, we check for presence of "b" in Repeated string of "a" using Pattern Searching
            Algorithms (KMP or Rabin-Karp), if found we return true.
        * If not found, then we repeat string "a" once more (for eg, a = "abcd", b = "cdabcdab").
        * Then, we again check for presence of "b" in Repeated string of "a" using Pattern Searching
            Algorithms (KMP or Rabin-Karp), if found we return true.

     * Repeated String of "a" will be "Text String" in KMP
     * "b" = Pattern
     * n -> length(a)
     * m -> length(b)

     * Time Complexity : O(m) + 2 * O(m + n)  ~=  O(m + n)
        * O(m) to generate a Repeated String of "a" that contains "b".
        * 2 * O(m + n) due to two Pattern searching calls of KMP Algorithm in worst case.
     * Space Complexity: O(m) + O(m)  ~  O(m)
        * O(m) Space required to generate a Repeated String (text) of "a" that contains "b"
        * O(m) for LPS/Pi array (of size pattern, "b") required for KMP Algorithm
     */
    // Return the minimum number of times you should repeat string 'a' so that string 'b' is a substring
    // of it.
    public int repeatedStringMatch(String a, String b) {
        int n = a.length();
        int m = b.length();

        // StringBuilder for storing the Repetition of String "a"
        StringBuilder repeatedString = new StringBuilder();

        // Output: Min. Number of times String "a" is repeated to have String "b" in it
        int repetitionCount = 0;

        // Appending String "a" until its length is greater or equal to B
        while (repeatedString.length() < m){
            repeatedString.append(a);
            repetitionCount++;
        }

        // Check for presence of "b" in Repeated string of "a" using Pattern Searching Algorithm (KMP)
        if (containsPattern_KMP(repeatedString.toString(), b))
            return repetitionCount;
        // This can also be used, but this string.contains() has a time complexity of O(m*n)
        // if (repeatedString.toString().contains(b))
        //    return repetitionCount;

        // If not found, then we repeat string "a" once more (for cases like eg, a = "abcd", b = "cdabcdab").
        repeatedString.append(a);
        repetitionCount++;

        // Again check for presence of "b" in Repeated string of "a" using Pattern Searching Algorithm (KMP)
        if (containsPattern_KMP(repeatedString.toString(), b))
            return repetitionCount;
        // if (repeatedString.toString().contains(b))
        //   return repetitionCount;

        // If still "b" is not present in Repeated string of "a" (after repeating "a" once more).
        // Then it is not possible to do so.
        return -1;
    }


    // This code is same as Searching of the "Pattern String" in a "Text String" using KMP Algorithm
    // Searching of the "Pattern String" in "Text String" can also be done using Rabin Karp Algorithm
    public boolean containsPattern_KMP(String text, String pattern){
        int n = text.length();
        int m = pattern.length();

        int[] lpsArray = getLPSArray(pattern, m);
        int i = 0, j = 0;

        while (i < n){
            if (text.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            }
            else{
                if (j != 0)
                    j = lpsArray[j - 1];
                else
                    i++;
            }
            if (j == m)
                return true;
        }
        return false;
    }

    public int[] getLPSArray(String pattern, int m){
        int[] LPS = new int[m];
        int prev_LPS_Length = 0, i = 1;

        while (i < m){
            if (pattern.charAt(i) == pattern.charAt(prev_LPS_Length)){
                prev_LPS_Length++;
                LPS[i++] = prev_LPS_Length;
            }
            else{
                if (prev_LPS_Length == 0){
                    LPS[i] = 0;
                    i++;
                }
                else
                    prev_LPS_Length = LPS[prev_LPS_Length - 1];
            }
        }
        return LPS;
    }
}
