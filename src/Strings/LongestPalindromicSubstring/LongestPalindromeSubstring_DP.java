package Strings.LongestPalindromicSubstring;

// https://youtu.be/UflHuQj6MVA
// https://www.geeksforgeeks.org/longest-palindrome-substring-set-1/

public class LongestPalindromeSubstring_DP {
    /*
        ************************************ Efficient DP Solution ******************************
        * This DP Solution is Optimization of Previous BruteForce Approach.
        * Instead of calling isPalindrome() for each substring, we can just use the answer of sub-problems
            to determine whether the current string is substring or not. To achieve this, we must fill
            DP in bottom up manner.
        * Instead of asking each substring with indices (i, j) whether you are palindrome or not,
          we can ask just next characters in the substrings (i+1, j-1) are you palindrome or not?
        * Approach: The time complexity can be reduced by storing results of sub-problems.
        * Maintain a boolean dp[n][n] that is filled in bottom up manner.
        * The value of dp[i][j] is true, if the substring from "i to j" is palindrome, otherwise false.
        * To calculate dp[i][j], check the value of dp[i+1][j-1], if the value is true and str[i] is same as str[j],
          then we make dp[i][j] true.
        * Otherwise, the value of dp[i][j] is made false.

        * TC -> O(n^3)  Two for loops & one string.charAt(i) that takes O(n) time too
        * SC -> O(n^2)  DP array (Ignoring output/answer substring)
     */
    public String longestPalindrome_DP1(String s) {
        int n = s.length();
        int maxPalindromeLength = 0;
        int palindromeStartIndex = 0, palindromeEndIndex = 0;

        boolean[][] dp = new boolean[n][n];

        // we loop here from i-1 to 0, because to determine whether s[i:j] is palindrome or not
        // we need to determine whether s[i+1:j-1] is palindrome or not.
        // So, we need next row (i+1) & previous column (j-1) value to determine dp[i][j]
        // So, loop must run from i = [n-1,0]  &  j  [i,n-1]
        for (int i = n-1; i >= 0; i--){
            for (int j = i; j < n; j++){

                // This below condition (j-i+1<=2) is for substring of length 1 & 2. For eg, "a", "cd", "cx"
                dp[i][j] =  (s.charAt(i) == s.charAt(j))  &&  (j - i + 1 <= 2 || dp[i+1][j-1]);

                if (dp[i][j]  &&  j - i + 1 > maxPalindromeLength){
                    maxPalindromeLength = j - i + 1;
                    palindromeStartIndex = i;
                    palindromeEndIndex = j;
                }
            }
        }
        return s.substring(palindromeStartIndex, palindromeEndIndex + 1);
    }


    /*
        ************************************ Efficient DP Solution ******************************
        * Same Approach
        * Just difference is to maintain a char array for fastest retrieval of characters at given index
        * TC -> O(n^2)  Two for loops only & O(1) for char retrieval
        * SC -> O(n^2) + O(n)  DP array + char array (Ignoring output/answer substring)
     */
    public String longestPalindrome_DP2(String str) {
        char[] s = str.toCharArray();
        int n = s.length;

        boolean[][] dp = new boolean[n][n];
        int longestPalindromeLength = 0;
        int start = 0;

        for (int i = n - 1; i >= 0; i--){
            for (int j = i; j < n; j++){
                dp[i][j] = (s[i] == s[j])  &&  (j - i + 1 <= 2  ||  dp[i + 1][j - 1]);

                if (dp[i][j]  &&  (j - i + 1) > longestPalindromeLength){
                    longestPalindromeLength = j - i + 1;
                    start = i;
                }
            }
        }
        return str.substring(start, start + longestPalindromeLength);
    }
}
