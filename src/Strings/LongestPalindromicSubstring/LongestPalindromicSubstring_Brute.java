package Strings.LongestPalindromicSubstring;

public class LongestPalindromicSubstring_Brute {

    /*
     ********************************Approach 1 : Brute Force ****************************************
     * Gives TLE
     * Generate all substrings & check if it is a Palindrome or Not
     * O(n^2) for generating all substrings & O(n) for checking whether each substrings is palindrome or not
     * TC -> O(n^3)
     * SC -> O(n)    for every substrings used in the form of char array
     *
     * NOTE: If we use "string.charAt(i)" instead of making a char Array, then TC would be O(n^4), coz
     * string.charAt(i) takes O(n) too. So, we used array to reduce indexing time
     * we used char array for O(1) for character retrieval at a given index
     */
    private String longestPalindromeSubstring_BruteForce(String s) {
        int longestPalindromeLength = 0;
        char[] str = s.toCharArray();

        // stores the start & ending index of our longest palindrome
        int palindromeStartIndex = 0, palindromeEndIndex = 0;

        for (int i = 0; i < str.length; i++) {
            for (int j = i; j < str.length; j++) {

                // This below code is same as checking for palindrome
                int low = i;
                int high = j;
                boolean isPalindrome = true;

                while (low <= high) {
                    if (str[low] != str[high]){
                        isPalindrome = false;
                        break;
                    }
                    low++;
                    high--;
                }
                // Recall: "j - i + 1" is the length of our current palindrome
                if (isPalindrome  &&  (j - i + 1) > longestPalindromeLength) {
                    longestPalindromeLength = j - i + 1;
                    palindromeStartIndex = i;
                    palindromeEndIndex = j;
                }
            }
        }
        // Instead of looping over start index till ending index to find the longest palindrome substring,
        // we return using substring method
        String longestPalindrome = s.substring(palindromeStartIndex, palindromeEndIndex + 1);
        return longestPalindrome;
    }



    // This one doesn't give TLE
    public String longestPalindrome(String str) {
        char[] s = str.toCharArray();
        int n = s.length;

        int longestPalindromeLength = 0;
        int start = 0;

        for (int i = 0; i < n; i++){
            for (int j = i; j < n; j++){
                if (isPalindrome(s, i, j)){
                    if (j - i + 1 > longestPalindromeLength){
                        longestPalindromeLength = j - i + 1;
                        start = i;
                    }
                }
            }
        }
        return str.substring(start, start + longestPalindromeLength);
    }

    public boolean isPalindrome(char[] str, int low, int high){
        while (low <= high){
            if (str[low] != str[high])
                return false;
            low++;
            high--;
        }
        return true;
    }
}
