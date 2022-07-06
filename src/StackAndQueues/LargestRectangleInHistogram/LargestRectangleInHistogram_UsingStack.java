package StackAndQueues.LargestRectangleInHistogram;
import java.util.Stack;

// https://youtu.be/X0X6G-eWgQ8
// https://youtu.be/jC_cWLy7jSI
// https://takeuforward.org/data-structure/area-of-largest-rectangle-in-histogram/

// Based on Previous smaller element & Next smaller element

/** *************************************** Intuition ***************************************
 * Intuition: The intuition behind the approach is taking different bars and finding the maximum
   width possible of each bar. Then find area of each bar (height * width) & take maximum.
 * Find Next Smaller Element & Previous Smaller Element for each bar/height
 * Find width of each bar/height using its "Next Smaller Element" & "Previous Smaller Element"
 * Find area of each bar/height & take maximum among them
 */

public class LargestRectangleInHistogram_UsingStack {
    /** *********************************** Brute Force Solution **************************************
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public int largestRectangleArea_BruteForce(int[] heights) {
        int n = heights.length;
        int maxArea = 0;

        for (int i = 0; i < n; i++){
            int low = i - 1;
            while (low >= 0  &&  heights[low] >= heights[i])
                low--;

            int high = i + 1;
            while (high < n  &&  heights[i] <= heights[high])
                high++;

            int width = high - low -1;
            int height = heights[i];
            int area = height * width;
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }



    /** *********************************** Efficient Stack Solution **************************************
     * Time Complexity: 5 * O(n) = O(n)
        * O(2n) to find the next smaller element (as we in worst case we will have to push & pop all the
          elements from the stack, making time complexity as O(2n))
        * O(2n) to find the previous smaller element (as we in worst case we will have to push & pop all the
          elements from the stack, making time complexity as O(2n))
        * O(n) to traverse through all the heights & calculate the Maximum area
     * Space Complexity: O(3 * n) = O(n)
        * O(n) for Next Greater Element Indices Array
        * O(n) for Previous Greater Element Indices Array
        * O(n) for stack used
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] NSE = nextSmallerElementIndices(heights, n);
        int[] PSE = previousSmallerElementIndices(heights, n);

        int maxAreaInHistogram = 0;

        for (int i = 0; i < n; i++){
            int height = heights[i];
            int width = NSE[i] - PSE[i] - 1;
            int area = height * width;
            maxAreaInHistogram = Math.max(area, maxAreaInHistogram);
        }
        return maxAreaInHistogram;
    }

    /* Same as Next Smaller Element using Stack */
    private int[] nextSmallerElementIndices(int[] arr, int n){
        Stack<Integer> stack = new Stack<>();
        int[] NSE = new int[n];

        for (int i = n - 1; i >= 0; i--){
            while (!stack.isEmpty()  &&  arr[i] <= arr[stack.peek()])
                stack.pop();

            NSE[i] = !stack.isEmpty() ? stack.peek() : n;
            stack.push(i);
        }
        return NSE;
    }

    /* Same as Previous Smaller Element using Stack */
    private int[] previousSmallerElementIndices(int[] arr, int n){
        Stack<Integer> stack = new Stack<>();
        int[] PSE = new int[n];

        for (int i = 0; i < n; i++){
            while (!stack.isEmpty()  &&  arr[stack.peek()] >= arr[i])
                stack.pop();

            PSE[i] = !stack.isEmpty() ? stack.peek() : -1;
            stack.push(i);
        }
        return PSE;
    }
}
