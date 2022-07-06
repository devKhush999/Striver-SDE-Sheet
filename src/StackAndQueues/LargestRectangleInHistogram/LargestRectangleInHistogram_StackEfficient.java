package StackAndQueues.LargestRectangleInHistogram;
import java.util.Stack;

// https://youtu.be/X0X6G-eWgQ8
// https://youtu.be/jC_cWLy7jSI
// https://takeuforward.org/data-structure/area-of-largest-rectangle-in-histogram/

/** ********************************** INTUITION **********************************
 * Intuition: The intuition behind the approach is taking different bars and finding the maximum
   width possible of each bar. Then find area of each bar (height * width) & take maximum.
 * While finding the "Previous smaller Element" of every bar/height, we can find the Area for each &
   every bar/height, and take maximum among them.
 * So, we can solve this question in one pass only.
 * We are able to do this because while finding the "Previous Smaller Element" for each bar, the Stack
   will contain the various heights in "increasing order" from bottom to top
 */

public class LargestRectangleInHistogram_StackEfficient {
    /*
    * Time Complexity: O(n) + O(n)  =  O(n)
      * O(2n) to find the previous smaller element (as we in worst case we will have to push & pop all the
        elements from the stack, making time complexity as O(2n))
    * Space Complexity:  O(n)
        * O(n) for Previous Greater Element Indices Array
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxAreaInHistogram = 0;

        // Below code is same as that of finding "Previous Smaller Element"
        // The Stack will contain the various heights in "increasing order" from bottom to top
        //
        for (int i = 0; i <= n; i++){
            while (!stack.isEmpty()  &&  (i == n  ||  heights[stack.peek()] >= heights[i])){
                int height = heights[stack.pop()];
                int width = !stack.isEmpty() ? i - stack.peek() - 1 : i;

                // Area of the current bar/height will be "Area = height * width"
                maxAreaInHistogram = Math.max(height * width, maxAreaInHistogram);
            }
            stack.push(i);
        }
        return maxAreaInHistogram;
    }
}
