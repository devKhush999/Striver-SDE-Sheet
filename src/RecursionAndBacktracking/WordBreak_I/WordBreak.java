package RecursionAndBacktracking.WordBreak_I;
import java.util.List;

// Pre-Requisite: WORD BREAK-II  & PALINDROME PARTITIONING
// https://youtu.be/th4OnoGasMU
// MUST READ: https://www.geeksforgeeks.org/word-break-problem-dp-32/

public class WordBreak {
    /*
    ********************************* Brute Force : BACKTRACKING *************************************
    *  Time Complexity: O(2^n * n)  ~  O(n * 2^n)
      Reason: O(2^n) to generate every substring. Because there are 2n combinations in The Worst Case.
      At every Index, we can either choose to partition or not. For string of length 'n', there will be
      2^n combinations of partition & non-partition. So, TC is O(2^n)
      O(n) due to generating substrings & checking whether that substring is present in the dictionary or not

    * Space Complexity: O(n)
      Reason: The depth of the recursion tree is O(n), so the auxiliary recursion stack
      space required is equal to the O(n).
     */
    // Same as "Word Break - II"
    public boolean wordBreak(String s, List<String> wordDict) {
        return canBreakWord(0, s, s.length(), wordDict);
    }
    private boolean canBreakWord(int index, String s, int n, List<String> wordDict){
        if (index == n)
            return true;

        for (int i = index; i < n; i++){
            if (wordDict.contains(s.substring(index, i + 1))){
                if (canBreakWord(i + 1, s, n, wordDict))
                    return true;
            }
        }
        return false;
    }


    /*
    ************************************* DP : MEMOIZATION **************************************
    * Code same as previous approach, just stores the answer of previously solved sub-problems
    * Create a dp[] array, each index of the dp array will store whether from that index, substring
      (in the original string) can be partitioned into words of dictionary or not
    * Simply, dp[i] will store whether the String starting from the index 'i' (till the length) can be
      partitioned into the words of dictionary or not.

    * Time Complexity = O(n^2 * n) = O(n^3)
    * O(n^2) because in every iteration & at every Index, we can either choose to partition or not.
    * If we choose to partition (means left substring is present in word dictionary), we again start a
      new iteration (using for loop) from just after that index (where partition took place) in a
      new Recursive call
    * O(n) due to generating substrings & checking whether that substring is present in the dictionary or not

    * Space Complexity: O(n) + O(n)
      Reason: The depth of the recursion tree is O(n), so the auxiliary recursion stack
      space required is equal to the O(n). Another O(n) SPace due to DP array
     */
    public boolean wordBreak_Memoization(String s, List<String> wordDict){
        int n = s.length();

        // DP array: dp[i] will store whether the String starting from the index 'i' (till the length) can be
        // partitioned into the words of dictionary or not.
        Boolean[] dp = new Boolean[n];

        canBreakWord(0, n, s, wordDict, dp);
        // return canBreakWord(0, n, s, wordDict, dp);
        return dp[0];
    }
    public boolean canBreakWord(int index, int n, String s, List<String> dictionary, Boolean[] dp){
        if (index == n)
            return true;
        // If this sub-problem has been solved previously, then return it
        if (dp[index] != null)
            return dp[index];

        for (int i = index; i < n; i++){
            String subString = s.substring(index, i + 1);
            // Before returning store the result into DP array
            if (dictionary.contains(subString)  &&  canBreakWord(i + 1, n, s, dictionary, dp)){
                return dp[index] = true;
            }
        }
        // Since we were unable to partition tha string into words of dictionary,
        // so return false along with storing answer in DP array
        return dp[index] = false;
    }



    /*
    ************************************* DP : TABULATION **************************************
    * This is the tabulation version of Memoization
    * Recall steps to convert a Memoization Solution into a Tabulation One
    * Declare DP array, Handle Base-Cases, Solve problem in reverse way/direction to that of memoization
      Copy-paste rest of code in Memoization into Tabulation Solution

    * Code same as previous approach, just stores the answer of previously solved sub-problems
    * Create a dp[] array, each index of the dp array will store whether from that index, substring
      (in the original string) can be partitioned into words of dictionary or not
    * Simply, dp[i] will store whether the String starting from the index 'i' (till the length) can be
      partitioned into the words of dictionary or not.

    * Time Complexity = O(n^2 * n) = O(n^3)
    * O(n^2) because in every iteration & at every Index, we can either choose to partition or not.
    * If we choose to partition (means left substring is present in word dictionary), we again start a
      new iteration (using for loop) from just after that index (where partition took place) in a new For loop
    * O(n) due to generating substrings & checking whether that substring is present in the dictionary or not

    * Space Complexity: O(n)     Space required due to the DP array
     */
    public boolean wordBreak_Tabulation(String s, List<String> wordDict){
        int n = s.length();
        boolean[] dp = new boolean[n + 1];

        // Base case when "i == n"
        dp[n] = true;

        // Solving problem in reverse way/direction to that of memoization (from n-1 to 0 in tabulation)
        // In memoization we solved in index 0 -> n-1
        for (int index = n-1; index >= 0; index--){
            // Same Code as Memoization
            for (int i = index; i < n; i++){
                String subString = s.substring(index, i + 1);

                if (wordDict.contains(subString)  &&  dp[i + 1]) {
                    dp[index] = true;
                    break;
                }
            }
        }
        // dp[0] implies whether the String starting from the index '0' (till the length) can be
        // partitioned into the words of dictionary or not.
        return dp[0];
    }
}
