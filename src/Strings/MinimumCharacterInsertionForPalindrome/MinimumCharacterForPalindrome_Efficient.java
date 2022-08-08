package Strings.MinimumCharacterInsertionForPalindrome;

// PRE-REQUISITE: KMP ALGORITHM & LPS/Pi Array
// https://www.geeksforgeeks.org/minimum-characters-added-front-make-string-palindrome/

public class MinimumCharacterForPalindrome_Efficient {
    /****************************** Efficient Solution: LPS Array in KMP Algorithm ***********************
     * We will use LPS Array in KMP Algorithm here.

     * Intuition:
        * In Brute Force Solution, we were finding the longest prefix which is the palindrome
            using O(n^2) time.
        * Can we find the longest palindrome prefix in O(n) time?
        * Yes, we will improve the time Complexity of finding the longest palindrome prefix.
        * Idea is to use the LPS (The Longest Prefix which is also the suffix) array from KMP algorithm.
        * Here we are only interested in the last value of this LPS array, because it shows us the
            largest suffix of the reversed string that matches the palindrome prefix of the original
            string, i.e, these many characters already satisfy the palindrome property.
        * Finally minimum number of character needed to make the string a palindrome is length of
            the input string minus last value of our lps array.
        * Why concatenating with reverse?
            * Recall: Palindrome reads same both forwards and backwards.
            * On reversing the Input String, the prefix palindrome (in input string) will become the
                suffix palindrome in reversed string. Think...
                For eg, "abcbalmao"  will become "oamlabcba"
                Notice that palindrome
            * When we join the input string with reversed string (with a special char in b/w), the
                prefix palindrome & suffix palindrome both will be present in the same string.
            * The prefix palindrome & suffix palindrome will be exactly same (bcoz Palindrome reads
                same both forwards and backwards). For eg: "abcbalmao@oamlabcba"
            * As this palindrome prefix is same as palindrome suffix, this motivates the use of LPS Array
                in the concatenated String.
            * Using LPS[length(concatenated_string) - 1], we can find the length of the longest palindrome
                prefix (bcoz prefix & suffix are same) (think...)
            * So, "length(input_string) - length(longest_prefix_palindrome)" will be the required answer.
        * Why the use of a special character (in b/w) concatenation of input string & its reverse is
            necessary?
            * To handle the case of input string with all same characters.
            * For example, inp = "aaa" here required answer will be 0 (as it is already palindrome).
            * If we don't add a special character (in b/w) concatenation, we will get a wrong answer


     * Steps:
        * We take the input String AND concatenate it with its reverse with a special character between them.
        * Then we find the LPS Array of concatenated string.
        * The answer will be "length(string) - LPS[length(concatenated_string) - 1]"

     * Time Complexity: O(n) + O(2 * n)   ~   O(n)
        * Reverse the String will take O(n) time.
        * Finding the LPS array of Concatenated String will take O(2n) time (Concatenated String is twice
            of length of input string)
     * Space Complexity: O(2 * n)   ~   O(n)
     */
    public int minCharInsertionForPalindrome(String s) {
        int n = s.length();

        // Find the Reverse of the string
        String reverse = new StringBuilder(s).reverse().toString();

        // Find the resultant concatenated String
        String concatenated = s + "@" + reverse;

        // LPS Array for the Concatenated String
        int[] LPS = getLPSArray(concatenated);

        // Answer will be "length(input_string) - length(longest_prefix_palindrome)"
        return n - LPS[concatenated.length() - 1];
    }


    // Compute LPS array used in KMP String Matching Algorithm
    public int[] getLPSArray(String s){
        int n = s.length();

        int[] LPS = new int[n];
        int prev_LPS_Length = 0, i = 1;

        while (i < n){
            if (s.charAt(i) == s.charAt(prev_LPS_Length)){
                prev_LPS_Length++;
                LPS[i] = prev_LPS_Length;
                i++;
            }
            else if (prev_LPS_Length == 0){
                LPS[i] = 0;
                i++;
            }
            else
                prev_LPS_Length = LPS[prev_LPS_Length - 1];
        }
        return LPS;
    }
}

/*
************************************ Steps ************************************

* The idea is to find out the largest prefix which is a palindrome.
* This can be found out in an optimised way by updating the string by concatenating it with a special
  symbol along with reverse of the string.
  For example: for string BBA, after concatenating it with a special symbol ‘$’ and then with its
  reverse, we get the updated string as “BBA$ABB”.
* Now, we will find out the LPS array for the above updated string.
* It should be noted that the last index (LAST) of the LPS array is useful.
* As we have already concatenated the original string with its reverse, LPS[LAST] is the length of
  palindromic prefix of the original string (bcoz in LPS suffix is same as prefix).
  * In case of above string “BBA”:
  * Original string: “BBA”.
  * Updated string: “BBA$ABB”
        LPS = [0, 1, 0, 0, 0, 1, 2]
  * Last element of LPS array: 2, which is the length of the longest palindromic prefix of original
    string as, Original string: “BBA”. Palindromic prefix: “BB”
* The answer will be the difference between the length of the original string and LPS[LAST]
  i.e. (3-2)=1 for the above example.
* Note: We can easily find LPS array, using Algorithm for LPS(Prefix Function).

******* Time Complexity
    * O(N) per test case, where N is the size of the given string.
    * In the worst case, we are traversing the whole string once for finding out the LPS array.

******* Space Complexity
    * O(N) per test case, where N is the size of the given string.
    * In the worst case, (2 * N) extra space is used by the LPS array.
 */