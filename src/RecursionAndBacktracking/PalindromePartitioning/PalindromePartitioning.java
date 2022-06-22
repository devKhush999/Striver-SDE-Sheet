package RecursionAndBacktracking.PalindromePartitioning;
import java.util.ArrayList;
import java.util.List;

// https://youtu.be/WBgsABoClE0
// https://takeuforward.org/data-structure/palindrome-partitioning/

/*
Approach: The initial idea will be to make partitions to generate substring and check if the
substring generated out of the partition will be a palindrome. Then we again partition the
remaining un-partitioned substring in the string via recursive call
Partitioning means we would end up generating every substring and checking for palindrome at every step.

In every recursive call, we partition the string into two substring such that; If partitioned substring
on left side (left substring) is a palindrome, then we partition the right substring via recursive call.
Else if left partitioned substring is not palindrome, then we simply skip that partition and continue
partition at next index.

When the base case (index == string.length()) has reached, this means all the left substring have been
partitioned, and they are palindrome
Thus, we have partitioned the entire string into a palindromic substring.
then the list of palindromes generated during that recursion call is inserted in a list of list.
*/

public class PalindromePartitioning {

    /*
    * Time Complexity: O(2^n * n/2)  ~  O(n * 2^n)
      Reason: O(2^n) to generate every substring
      O(n/2)  to check if the substring generated is a palindrome or not (nearly O(n) in worst case).

    * Space Complexity: O(k * x) + O(n)
      Reason: The space complexity can vary depending upon the length of the answer.
      'k' is the average length of the list of palindromes and if we have x such list of palindromes
      in our final answer. The depth of the recursion tree is O(n), so the auxiliary space
      required is equal to the O(n).
    */

    public List<List<String>> partition(String s) {
        // ArrayList to store all the palindromic partition substrings of a string
        List<List<String>> allPalindromePartitions = new ArrayList<>();

        palindromePartitioning(0, s, s.length(), allPalindromePartitions, new ArrayList<>());
        return allPalindromePartitions;
    }

    private void palindromePartitioning(int index, String s, int n, List<List<String>> answer, ArrayList<String> list){
        // If index == n, this means all the left substring have been partitioned, and they are palindrome
        // So, include the current palindromic partitioned substring sequence into answer list
        if (index == n){
            answer.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < n; i++){
            // Partitioning the string into two strings one from [index, i] & other from [i+1, s.length()]
            // Checking if the left partitioning the string is palindrome or not
            // If Yes, then partition the right substring too. Else, skip that partition & continue
            // partition at next index.
            if (isPalindrome(s, index, i)){
                String substringFrom_index_To_i = s.substring(index, i + 1);
                // add current left partition substring (which is palindrome) into a list
                list.add(substringFrom_index_To_i);
                palindromePartitioning(i + 1, s, n, answer, list);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String str, int startIndex, int endIndex){
        int low = startIndex, high = endIndex;

        while (low <= high){
            if (str.charAt(low) != str.charAt(high))
                return false;
            low++;
            high--;
        }
        return true;
    }
}
