package Strings.MinimumCharacterInsertionForPalindrome;

// Minimum characters to be added at front to make string palindrome

public class MinimumCharacterForPalindrome_Brute {
    /*************************************** Brute Force **********************************************
     * The idea is simple, as we can add only at the front, thus, we need to work on the prefix of the
        string.
     * So, we need to find the largest prefix that is a palindrome. For example, in case of “abbac”,
        the largest prefix that is a palindrome is “abba”
     * So, after finding the longest prefix which is the palindrome, we can just add the remaining
        characters from the end of string (not in the palindrome prefix) in reverse order to the front.
     * So, the problem reduces to finding the longest prefix which is palindrome.
     * If we can find the longest prefix which is palindrome,
        then "length(string) - length(longest_prefix_palindrome)" will be the answer. Think...
     * So, we try to delete the characters from the end of string so that resulting string is palindrome.

     * No. of characters to be inserted at front    ==   No. of characters deleted from end to obtain
                to make it palindrome                             the largest palindrome prefix
     * Think about this...

     * Time Complexity: O(n^2)
        * We remove each character from the consideration (starting from the last) one by one. This
            takes O(n) time.
        * For each character we remove, we check whether string is palindrome or not. This too takes O(n)
            time.
        * In the worst case, for n number of times, we are checking if the string is a palindrome of not
            taking O(N) time.
     * Space Complexity: O(1)
        * No extra space used
     */
    public int minCharInsertionForPalindrome(String s) {
        int n = s.length();

        // Minimum character required to insert at front to make the string palindrome
        int minCharRequired = 0;

        // Exclude the last characters from the string, until the string becomes a palindrome
        for (int i = n - 1; i >= 0; i--){

            // Once the string becomes a palindrome, subtract the current length of string (from 0 to i)
            // with the actual length of the string (from 0 to n-1) OR maintain a count of removed characters
            if (isPalindrome(s, 0, i)) {
                return minCharRequired;

                // This can also be done
                // return (n - 1) - i;
            }
            else{
                minCharRequired++;
            }
        }
        return 999;  // Garbage stuff
    }


    // Checking whether a string (from index "low" to index "high") is palindrome or not
    // Time Complexity: O(n)
    private boolean isPalindrome(String s, int low, int high){
        int i = low, j = high;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}

/* Dry Run
In the case of “xxaxxxr” of length 7:
As, “xxaxxxr” is not a palindrome, remove the last character, the new string will be “xxaxxx”
As, “xxaxxx” is not a palindrome either, remove the last character, the new string will be “xxaxx”.
Now, as, “xxaxx” is a palindrome:
Answer = actual length - current length
Answer = 7 - 5 i.e. 2.
 */
