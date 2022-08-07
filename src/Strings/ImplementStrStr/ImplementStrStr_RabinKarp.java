package Strings.ImplementStrStr;

// PRE-REQUISITE: "Rabin Karp Algorithm : Pattern Matching"
// Great Video with Intuition: https://youtu.be/qQ8vS2btsxI
// https://youtu.be/BQ9E-2umSWc

// Great Reading: https://www.geeksforgeeks.org/rabin-karp-algorithm-for-pattern-searching/

public class ImplementStrStr_RabinKarp {
    /*********************************************************************************************
     * Time Complexity:  O(m + n)    In Best and Average case
                       ~ O(m * n)    In extreme worst case (Actually lower than this)
        * where ‘N’ is the length of the "text" string and ‘M’ is the length of "pattern" string.
        * Worst case of Rabin-Karp algorithm occurs when all characters of pattern and text are same
          as the hash values of all the substrings of text[] match with hash value of patttern[].
          For example pat[] = “AAA” and txt[] = “AAAAAAA”.
     * Space Complexity: O(1)
        * No extra space used
     */
    private final int UNIQUE_CHAR = 26;
    private final int MOD = 500000;

    public int strStr(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        // 0 length substring (i.e, "") is always at the 0th index
        if (m == 0)
            return 0;
        // If substring length is greater than string length, then it is not the substring indeed
        if (m > n)
            return -1;

        // The value of "power" would be "Math.power(UNIQUE_CHAR, M-1) % MOD"
        int power = 1;
        int textHash = 0, patternHash = 0;

        // Calculate the Hash value of pattern and first window (of size pattern) in the text using Hash Function
        for (int i = 0; i < m; i++){
            patternHash = (patternHash * UNIQUE_CHAR  +  (pattern.charAt(i) - 'A' + 1)) % MOD;
            textHash = (textHash * UNIQUE_CHAR  +  (text.charAt(i) - 'A' + 1)) % MOD;

            if (i != m - 1)
                power = (power * UNIQUE_CHAR) % MOD;
        }

        // Slide the pattern string over the text string one by one
        // Check the Hash values of current window of text and pattern.
        // If the hash values match, then only check for each & every character in pattern one by one
        for (int i = 0; i <= n - m; i++){
            if (patternHash == textHash){
                int j = 0;
                for (; j < m; j++){
                    if (text.charAt(i + j) != pattern.charAt(j))
                        break;
                }
                if (j == m){
                    return i;
                }
            }
            // Calculate Hash value for next sliding window of text
            if (i != n - m){
                textHash = textHash - (text.charAt(i) - 'A' + 1) * power;
                textHash = (textHash * UNIQUE_CHAR  +  (text.charAt(i + m) - 'A' + 1)) % MOD;

                if (textHash < 0)
                    textHash = textHash + MOD;
            }
        }
        return -1;
    }
}
