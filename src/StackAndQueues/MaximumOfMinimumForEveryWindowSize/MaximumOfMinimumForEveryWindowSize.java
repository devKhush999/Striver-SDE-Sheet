package StackAndQueues.MaximumOfMinimumForEveryWindowSize;
import java.util.ArrayDeque;
import java.util.Deque;

// PRE-REQUISITE: "MAXIMUM SLIDING WINDOW"
// This problem is "MINIMUM SLIDING WINDOW"

/** ****************************** Brute And Efficient Solution ***********************************
 * "Intuition" says that we try to create/partition the array into each & every sliding window size.
 Sliding window size varies from "1" to "N".
 * For each Sliding window size, find out the "Maximum value" of all Minimum value in Sliding Window.
 * For a given Window size 'k' ==> there are "n - k + 1" Sliding windows
 So, there are "n - k + 1" Minimum values in Sliding Windows (one minimum value in each Sliding Window)
 So, for "Maximum value" of all Minimum value in Sliding Window,  we take maximum values of all
 "n - k + 1" Minimum values.
 * Now, we repeat this procedure for all "n" Sliding window size
 */


public class MaximumOfMinimumForEveryWindowSize {
    /** ****************************** Brute And Efficient Solution ***********************************
     * A simple brute force approach to solve this problem can be to generate all the windows
       possible of a particular length say ‘L’ and find the minimum element in all such windows (of size 'L').
     * Then find the maximum of all such minimum elements (in the sliding windows) and store it.
     * Now the length of window is 1<=L<=N. So we have to generate all possible windows of size ‘1’ to ‘N’
       and for generating each such window we have to mark the end-points of that window.
     * So for that, we have to use a triple nested loop
     *
     * Another Approach to find all the minimum elements of a "Sliding window" (of size, 'L') can be done
       using "Deque" (Similar to Maximum_Sliding_Window)

     * Complexity:
        * Time Complexity:
            * Efficient way (Deque) for finding Minimum Sliding Window: O(n * 2n) = O(n^2)
                * We loop 'n' times for every Window Size, O(n)
                * Efficient way for finding "Minimum Sliding Window" takes O(2n) time
            * Brute way (Nested loops) for finding Minimum Sliding Window: O(n * n^2) = O(n^3)
                * We loop 'n' times for every Window Size, O(n)
                * Brute way for finding "Minimum Sliding Window" takes O(n^2) time
        * Space Complexity:
            Ignoring output array space
            * Efficient way (Deque) for finding Minimum Sliding Window: O(n)        due to deque
            * Brute way (Nested loops) for finding Minimum Sliding Window: O(1)
     */
    public int[] maxMinWindow(int[] arr) {
        int n = arr.length;
        int[] maximumOfMinimumSlidingWindows = new int[n];

        // Consider all windows of different sizes from size '1' to 'N'
        for (int slidingWindowSize = 1; slidingWindowSize <= n; slidingWindowSize++){
            int maximumOfCurrentWindowSize = maximumOfMinimumSlidingWindow(arr, slidingWindowSize, n);

            maximumOfMinimumSlidingWindows[slidingWindowSize - 1] = maximumOfCurrentWindowSize;
        }
        return maximumOfMinimumSlidingWindows;
    }


    // ******************* Brute Force Approach for finding "Minimum Sliding Window" *******************
    // Same as Brute Force Solution for "MAXIMUM SLIDING WINDOW"
    public int maximumOfMinimumSlidingWindow_BruteForce(int[] arr, int windowSize, int n){
        // Initialize maximum of minimums for current "window size"
        int maximumOfAllSlidingWindow = Integer.MIN_VALUE;

        // Traverse through all windows of current 'Window_size'
        for (int i = 0; i <= n - windowSize; i++){
            // Find minimum of current window
            int currMinSlidingWindow = Integer.MAX_VALUE;

            for (int j = i; j < i + windowSize; j++)
                currMinSlidingWindow = Math.min(arr[j], currMinSlidingWindow);

            // Update the maximum of minimum for current "window size"
            maximumOfAllSlidingWindow = Math.max(currMinSlidingWindow, maximumOfAllSlidingWindow);
        }
        return maximumOfAllSlidingWindow;
    }


    // ******************* Efficient Approach for finding "Minimum Sliding Window" *******************
    // Same as Efficient Solution for "MAXIMUM SLIDING WINDOW" using "Deque"
    // Deque will store the array elements in increasing order since we want "Minimum Sliding Window"
    public int maximumOfMinimumSlidingWindow(int[] arr, int windowSize, int n){
        int maximumOfAllSlidingWindows = Integer.MIN_VALUE;

        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < arr.length; i++){
            if (!deque.isEmpty()  &&  deque.peekFirst() == i - windowSize)
              deque.removeFirst();

            while (!deque.isEmpty()  &&  arr[deque.peekLast()] >= arr[i])
                deque.removeLast();

            deque.addLast(i);

            if (i >= windowSize - 1) {
                int currMinimumSlidingWindow = arr[deque.peekFirst()];
                maximumOfAllSlidingWindows = Math.max(currMinimumSlidingWindow, maximumOfAllSlidingWindows);
            }
        }
        return maximumOfAllSlidingWindows;
    }
}
