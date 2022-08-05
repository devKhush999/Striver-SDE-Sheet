package Strings.LongestPalindromicSubstring;

// https://leetcode.com/problems/longest-palindromic-substring/solution/
// https://youtu.be/y2BD4MJqV20

public class LongestPalindromeSubstring_Best {
    /*
     ********************************* Approach 3: Expand Around Center ******************************
     * We observe that a palindrome mirrors around its center. Therefore, a palindrome can be expanded
     * from its center, and there are only 2n−1 such centers.
     * You might be asking why there are 2n-1 but not n centers?
     *  The reason is the center of a palindrome can be in between two letters.
     * Such palindromes have even numbers of letters (such as "abba") and its center are between the two 'b's.
     *
     * Since expanding a palindrome around its center could take O(n) time, the overall complexity is O(n^2)
     * If we have used string.charAt(i), TC would become O(n^3)
     * Time complexity : O(n^2)
     * Space complexity : O(1)      If we ignore char array & output string
     */
    public String longestPalindrome(String string) {
        char[] s = string.toCharArray();

        int longestPalindromeLength = 0;
        int palindromeStartIndex = 0, palindromeEndIndex = 0;

        for (int i = 0; i < s.length; i++){
            // checks for odd length palindrome substrings
            int len1 = expandFromMiddle(s, i, i);

            // checks for even length palindrome substrings
            int len2 = expandFromMiddle(s, i, i + 1);

            // Take maximum of both these substrings length
            int currPalindromeExpandedLength = Math.max(len1, len2);

            // update the max. length, start index & end inddex
            if (currPalindromeExpandedLength > longestPalindromeLength){
                longestPalindromeLength = currPalindromeExpandedLength;
                palindromeStartIndex = i - (currPalindromeExpandedLength - 1)/2;
                palindromeEndIndex = i + currPalindromeExpandedLength/2;
            }
        }
        return string.substring(palindromeStartIndex, palindromeEndIndex + 1);
        // This would also work, we explicitly don't need ending index of palindrome
        // return string.substring(palindromeStartIndex, longestPalindromeLength);
    }


    private int expandFromMiddle(char[] str, int index1, int index2){
        int left = index1, right = index2;

        // Expand the current palindrome, till its characters at left & right pointers are equal
        while (left >= 0  &&  right < str.length  &&  str[left] == str[right]){
            left--;
            right++;
        }
        // we need to return the length of Expanded palindrome, so length will be "right-left+1"
        // coz if last previous two characters were equal & next ones are not, then also left-- & right++
        // will occur
        return right - left - 1;
    }
}
