package Strings.CountAndSay;

public class CountAndSay_Memoization {
    /********************************** Efficient Solution 1 *****************************************
     * This Solution is similar to the Memoization Version as in DP
     * Basically from "Top to down Solution"
     *
     * Time Complexity: O(n)
        * There will be 'n' states/sub-problems in the solution
     * Space Complexity: O(n)
        * Due to 'n' Recursion calls
     */
    public String countAndSay(int n) {
        // Base case
        if (n == 1)
            return "1";

        // Get the answer of Sub-Problem
        String subProblem = countAndSay(n - 1);

        // Say the digits present in the Solution
        return say(subProblem);
    }

    // Another version of say() function as in Tabulation approach
    // It is pretty easy to convert/say a String of digits (for eg, "1233" to get "111223")
    public String say(String s){
        int len = s.length();
        StringBuilder result = new StringBuilder();

        // Maintain the count for each character
        int repetition = 0;

        for (int i = 0; i < len; i++){
            char ch = s.charAt(i);

            // Once the character is no more repeating, add it to the result
            if (i != 0  &&  s.charAt(i - 1) != ch){
                result.append(repetition);
                result.append(s.charAt(i - 1));
                repetition = 0;
            }
            // Increment the count of each & every character
            repetition++;
        }
        // Add the count & answer for last character
        result.append(repetition);
        result.append(s.charAt(len - 1));

        return result.toString();
    }
}