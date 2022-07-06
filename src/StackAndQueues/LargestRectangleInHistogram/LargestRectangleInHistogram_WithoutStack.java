package StackAndQueues.LargestRectangleInHistogram;

// https://youtu.be/X0X6G-eWgQ8
// https://youtu.be/jC_cWLy7jSI
// https://takeuforward.org/data-structure/area-of-largest-rectangle-in-histogram/

/**
 * Intuition: The intuition behind the approach is taking different bars and finding the maximum
    width possible of each bar. Then find area of each bar (height * width) & take maximum.
 */

public class LargestRectangleInHistogram_WithoutStack {
    /** *********************************** Efficient Array Solution **************************************
     * Time Complexity: 3 * O(n) = O(n)
        * O(n) to find the next smaller element (here we use array to find the Next Smaller element, in this
            process we will use the calculated answer (,i.e, Next Smaller element) of next array elements to find
            answer of current array element)
        * O(2n) to find the previous smaller element (here we use array to find the Previous Smaller element, in this
            process we will use the calculated answer (,i.e, Previous Smaller element) of previous array elements to find
            answer of current array element)
        * O(n) to traverse through all the heights & calculate the Maximum area.

     * Space Complexity: O(2 * n) = O(n)
        * O(n) for Next Greater Element Indices Array
        * O(n) for Previous Greater Element Indices Array
     *
     * Clearly this approach is much faster than previous "Stack Solution" approach which uses O(5n) Time O(3n) Space
     */

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] prevSmallerIndex = new int[n];
        int[] nextSmallerIndex = new int[n];


        // Similar to the problem of "Previous Greater element", this is the code for problem of
        // Previous Smaller Element using "Array" (without array)
        prevSmallerIndex[0] = -1;
        for (int i = 1; i < n; i++){
            int j = i - 1;
            while (j >= 0  &&  heights[j] >= heights[i])
                j = prevSmallerIndex[j];

            prevSmallerIndex[i] = j;
        }

        // Similar to the problem of Next Greater element, this is the code for problem of
        // Next Smaller Element using "Array" (without array)
        nextSmallerIndex[n - 1] = n;
        for (int i = n - 2; i >= 0; i--){
            int j = i + 1;
            while (j < n  &&  heights[i] <= heights[j])
                j = nextSmallerIndex[j];

            nextSmallerIndex[i] = j;
        }

        // Calculating the maximum Area in histogram
        int maxAreaInHistogram = 0;

        for (int i = 0; i < n; i++) {
            int area = (nextSmallerIndex[i] - prevSmallerIndex[i] - 1) * heights[i];
            maxAreaInHistogram = Math.max(area, maxAreaInHistogram);
        }
        return maxAreaInHistogram;
    }
}
