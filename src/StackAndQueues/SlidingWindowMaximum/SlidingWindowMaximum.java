package StackAndQueues.SlidingWindowMaximum;
import java.util.ArrayDeque;
import java.util.Deque;

// Great Video for Intuition:
// https://youtu.be/CZQGRp93K4k
// https://takeuforward.org/data-structure/sliding-window-maximum/

public class SlidingWindowMaximum {
    /** ********************************** BRUTE FORCE SOLUTION ****************************************
     * Brute Force Approach
     * Our intuition here is to go through each sliding window and keep track of the maximum element in
       each sliding window.
     * To implement the same we run two nested loops, where the outer loop which will mark the starting
       point of the subarray of length "k", the inner loop will run from the Starting index, "INDEX"
       to "INDEX + K", iterate "K" elements from starting index and store the maximum element among
       these K elements into the answer.
     * STEPS :
        * Use two nested loops.
        * The outer loop from starting "INDEX" = "0" to "N - Kth" elements.
        * The inner loop will run for ‘K’ iterations starting from "INDEX" for the window size of ‘K’.
        * Now create a variable to store the maximum of K elements that are traversed in the inner loop.
        * Find the maximum of K elements traversed by the inner loop.
        * Store the maximum element in every iteration of the outer loop which is nothing but the
          sliding window maximum of the current window.
     * Time Complexity:  O((n - k) * k)  =  O(n*k - k*k)  =  O(n * k)
            * where 'N' is the number of elements in the given array and ‘K’ is the given window size.
            * Two nested for loops, outer for loop runs for "n-k" iterations & another nested one
              runs for "k" iterations
     * Space Complexity: O(1)
            * Neglecting Space required for Output Array
     */
    public int[] maxSlidingWindow_BruteForce(int[] arr, int k) {
        int n = arr.length;
        int[] maxSlidingWindow = new int[n - k + 1];

        for (int i = 0; i <= n - k; i++){
            int max = Integer.MIN_VALUE;

            for (int j = i; j < i + k; j++)
                max = Math.max(arr[j], max);

            maxSlidingWindow[i] = max;
        }
        return maxSlidingWindow;
    }


    /** ********************************* Efficient Deque Solution *************************************
     * Time Complexity: O(n) + O(n)  =  O(n)
        * Since we will be adding and removing every element of the array in the window at most once
          during the whole process and in the deque, push and the pop operation from the front or back
          operation takes O(1) time. So the overall time complexity will be O(N).
     * Space Complexity: O(k)
        * At any point of time, Deque will have O(K) elements because size of sliding window is 'k'
          and we are removing out of bound indices for every sliding window.
     */
    public int[] maxSlidingWindow(int[] arr, int k) {
        int n = arr.length;

        Deque<Integer> deque = new ArrayDeque<>();
        int[] maxSlidingWindow = new int[n - k + 1];
        int index = 0;

        for (int i = 0; i < n; i++){
            // Every time before entering a new element, we check whether the element
            // present at the front is out of bounds of our present window size, i.e, beyond [i-k+1, i]
            if (!deque.isEmpty()  &&  deque.peekFirst() == i - k)
                deque.removeFirst();

            // we check from the rear that the element present in deque is smaller than the incoming element.
            // If yes, there’s no point storing them (because we want maximum) and hence we pop them out.
            // remove smaller numbers in k range as they are useless
            while (!deque.isEmpty()  &&  arr[i] >= arr[deque.peekLast()])
                deque.removeLast();

            // Insert the index of current element at the back of the deque.
            deque.addLast(i);

            // Finally, the element present at the front would be our largest element.
            // Get the maximum of the current window i.e. maximum of subarray [i-k+1, i].
            if (i >= k - 1)
                maxSlidingWindow[index++] = arr[deque.peekFirst()];
        }
        return maxSlidingWindow;
    }
}

/*
************************************ Deque Based Approach ************************************

* The basic idea of this approach is to use a deque to store the elements of the window.
* We will maintain a deque that will store elements in non-increasing order such that the maximum
  element of the window is at the front of the deque.
* We will store the index of the elements of the given array/list.
*
* Steps:
    * Start traversing the array from the front using an index 'i' which denotes the end
      of the current window such that 0 <= i <= N - 1.
    * Remove the front element from the deque, if its index lies outside the boundary of the current
      window because the deque is used to store the index of the current window.
    * Now, remove the elements from the back of the deque until the last element is greater than
      the current element because we want to maintain a non-increasing deque.
    * Insert the index of the current element at the end of the deque.
    * Get the maximum element of the current window which is at the front of the deque.
      Store the maximum element of the current window.
 */
