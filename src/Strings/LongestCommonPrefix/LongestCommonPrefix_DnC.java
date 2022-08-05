package Strings.LongestCommonPrefix;

/**
 * Divide and Conquer Approach

 * Time Complexity: O(N*M)
    * In the worst case, we are making the N comparison, and in each comparison, we are traversing
        through the length of the string that takes O(M) time. Hence, the overall time complexity is O(N*M).

 * Space Complexity: O(M * log(N))
    * There is log(N) recursive calls, and in each recursive call, we need O(M) space to store the
        longest prefix string. Hence, the overall space complexity is O(M*log(N)).

 Where N is the number of strings in the array and M is the length of the shortest string present in
 the array.
*/

public class LongestCommonPrefix_DnC {
    /****************************** Another Solution using Divide and Conquer ****************************
     */
    public String longestCommonPrefix(String[] arr, int n) {
        // A variable to store longest common prefix for the range from 0 to n-1.
        String longestPrefix = commonPrefix_DivideAndConquer(arr, 0, n - 1);

        return longestPrefix;
    }

    // Function to find the Common Prefix in Two Strings
    public String commonPrefix(String str1, String str2) {
        // Storing the minimum length
        int minLength = Math.min(str1.length(), str2.length());

        // A string to store the longest common prefix
        String ans = "";

        // Find the common prefix in two Strings
        for (int idx = 0; idx < minLength; ++idx) {
            if (str1.charAt(idx) == str2.charAt(idx)) {
                ans += str1.charAt(idx);
            } else {
                break;
            }
        }
        return ans;
    }


    // In this approach, we will divide the array of strings into halves, and we will find
    // the common prefix for both parts separately.
    // Then, we will find the common prefix of these obtained strings.
    public String commonPrefix_DivideAndConquer(String[] arr, int start, int end) {
        // If range contains only one string, return that string.
        if (start == end) {
            return arr[start];
        }

        // mid value
        int mid = (start + end) / 2;

        // The string left to store the longest common prefix for the left half of the range.
        String left = commonPrefix_DivideAndConquer(arr, start, mid);

        // The string right to store the longest common prefix for the right half of the range.
        String right = commonPrefix_DivideAndConquer(arr, mid + 1, end);

        // The variable "ans" to store the longest common prefix for the range from start to end.
        String ans = commonPrefix(left, right);

        return ans;
    }
}
