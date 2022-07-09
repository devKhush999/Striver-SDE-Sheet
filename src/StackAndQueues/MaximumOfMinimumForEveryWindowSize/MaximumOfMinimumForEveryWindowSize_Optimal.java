package StackAndQueues.MaximumOfMinimumForEveryWindowSize;
import java.util.Stack;
import java.util.Arrays;

// PRE-REQUISITE: "NEXT SMALLER ELEMENT", "PREVIOUS SMALLER ELEMENT", "LARGEST AREA IN A HISTOGRAM"
// https://youtu.be/CK8PIAF-m2E
// https://www.geeksforgeeks.org/find-the-maximum-of-minimums-for-every-window-size-in-a-given-array/

public class MaximumOfMinimumForEveryWindowSize_Optimal {
    /*
    * Time Complexity: O(n)
    * Space Complexity: O(n)
     */
    public int[] maxMinWindow(int[] arr) {
        int n = arr.length;

        // Next Smaller element indices
        int[] NSE = nextSmallerElementIndices(arr, n);
        // Previous Smaller element indices
        int[] PSE = previousSmallerElementIndices(arr, n);

        // maximum Of All Minimum Sliding Windows
        int[] answer = new int[n];
        Arrays.fill(answer, Integer.MIN_VALUE);

        for (int i = 0; i < n; i++){
            int width = NSE[i] - PSE[i] - 1;

            // Lengths of window for which the element is minimum (same as 'width' in Largest are in histogram)
            // ‘ARR[i]’ will be the minimum element of the window of size -> size = next[i] - prev[i] - 1.
            answer[width - 1] = Math.max(arr[i], answer[width -1]);
        }

        // The answer for some window having size ‘L’ would always be greater than or equal to
        // the answer for a window having size greater than L i.e, L+1, L+2 .... so on.
        for (int i = n - 2; i >= 0; i--)
            answer[i] = Math.max(answer[i], answer[i + 1]);

        return answer;
    }

    // **************************** Next Smaller Element ****************************
    private int[] nextSmallerElementIndices(int[] arr, int n){
        int[] NSE = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n-1; i >= 0; i--){
            while (!stack.isEmpty()  &&  arr[i] <= arr[stack.peek()])
                stack.pop();

            NSE[i] = !stack.isEmpty() ? stack.peek() : n;
            stack.push(i);
        }
        return NSE;
    }

    // **************************** Previous Smaller Element ****************************
    private int[] previousSmallerElementIndices(int[] arr, int n){
        int[] PSE = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++){
            while (!stack.isEmpty()  &&  arr[stack.peek()] >= arr[i])
                stack.pop();

            PSE[i] = !stack.isEmpty() ? stack.peek() : -1;
            stack.push(i);
        }
        return PSE;
    }
}


/******************************************** Monotonic Stack ******************************************
 * We will follow some reverse strategy for building our final solution instead of finding minimums
   for every possible window. We will find how many windows our current element can be the answer.
 * To find that we will calculate the indexes of the next smaller and previous smaller element for
   every element given in the array. The next smaller element is some number that is smaller than the
   current element and lies on nearest on the right-hand side of the current element.
 * Similarly, the previous smaller is some number that is smaller than the current element and
   lies on nearest on the left-hand side of the current element.
 * If there is no next smaller element for the current number then we will assume its index having
   value 'N' and if there is no previous smaller element for the current number then we will assume
   its index having value -1.
 * The above two arrays of the next smaller and previous smaller can be done in linear time with the
  help of Monotonic Stack.

 * Suppose ‘next[i]’ = index of next smaller element, ‘prev[i]’ = index of previous smaller element.
 * Now, we know the ‘ARR[i]’ will be the minimum element of the window of size -> size = next[i] - prev[i] - 1.
 * So, we will directly use the value of ‘ARR[i]’ for updating the answer of window having
   size = next[i] - prev[i] - 1.
 * After doing this for every element we can update our answer for windows of some different
   lengths i.e, we are still left with some window sizes for which the answer is not calculated yet.

 * So to fill the remaining entries we will use some observations listed below:-
 * The answer for some window having size ‘L’ would always be greater than or equal to the answer
   for a window having a length greater than L i.e, L+1, L+2 .... so on.
 * Hence, we will update the remaining uncalculated answer for some windows having length ‘L’ with
   the maximum suffix starting from length ‘L+1’.

 * Time Complexity:  O(N), where ‘N’ is the number of elements present in the array.
     The Time Complexity for creating the next array and the prev array from Monotonic Stack takes
     linear time. For each element, we update our answer using next and prev in constant time.
     Hence, the overall Time Complexity is O(N).

 * Space Complexity:  O(N), where ‘N’ is the number of elements present in the array.
     We are using stack and two extra arrays, next and prev of size N.
     Hence, the overall Space Complexity is O(N).
 */
