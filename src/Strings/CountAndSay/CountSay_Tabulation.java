package Strings.CountAndSay;

public class CountSay_Tabulation {
    /********************************** Efficient Solution 1 *****************************************
     * This Solution is similar to the Tabulation Version as in DP
     * Basically from "Bottom to Up Solution"

     * Time Complexity: O(n)
        * There will be 'n' states/sub-problems in the solution
     * Space Complexity: O(1)
        * We ar using Constant Space to solve
     */
    public String countAndSay(int n) {
        // Answer of Sub-Problem (Base case)
        String subProblemSolution = "1";

        // Go from "Bottom to Up" as in DP
        // Use the answer of previous Sub-Problem to get the answer of next Sub-Problem
        for (int i = 2; i <= n; i++)
            subProblemSolution = say(subProblemSolution);

        // Return the answer
        return subProblemSolution;
    }

    // Another version of say() function as in Memoization approach
    // It is pretty easy to convert/say a String of digits (for eg, "1233" to get "111223")
    public String say(String s){
        int len = s.length();
        StringBuilder result = new StringBuilder();

        // Maintain the count for each character
        int repetition = 1;                     // Include the count for first character
        char previousChar = s.charAt(0);        // Include the first character

        for (int i = 1; i < len; i++){
            char ch = s.charAt(i);

            // If previous character is not same as current character, this implies
            // the character is no more repeating, add it to the result
            if (previousChar != ch){
                result.append(repetition);
                result.append(s.charAt(i - 1));
                previousChar = ch;
                repetition = 1;
            }
            // else if character is repeating, increment its count
            else
                repetition++;
        }
        // Add the count & answer for last character
        result.append(repetition);
        result.append(s.charAt(len - 1));

        return result.toString();
    }
}
