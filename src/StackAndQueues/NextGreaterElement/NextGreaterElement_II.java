package StackAndQueues.NextGreaterElement;
import java.util.Stack;

// https://youtu.be/Du881K7Jtk8
// https://takeuforward.org/data-structure/next-greater-element-using-stack/
// SEE SLIDES: https://leetcode.com/problems/next-greater-element-ii/solution/
// https://www.geeksforgeeks.org/find-the-next-greater-element-in-a-circular-array/
// https://www.geeksforgeeks.org/find-the-next-greater-element-in-a-circular-array-set-2/
// https://medium.com/@shubhamkr/find-next-greater-element-in-an-array-d373f8c37f44
// PRE-REQUISITE: "NEXT GREATER ELEMENT: BOTH STACK METHODS"

/** ******************************** Approach *****************************************
 * Main Approach is same as "Next greater element 1"
 *
 * Now we need to make this algorithm work for a circular array.
 * The only difference between a circular and non-circular array is that while searching for the
   next greater element in a non-circular array we don’t consider the elements left to the concerned
   element. This can be easily done by inserting the elements of the array 'A' again at the end of 'A'.
 * Thus making its size double. But we actually don’t require any extra space. We can just traverse
   the array twice. We actually run a loop 2*N times, where N is the size of the given array.
 * We access the array elements by "arr[i % n]"
 *
 * Time Complexity: O(2N) + O(2N) = O(N)
 * Auxiliary Space: O(N)
 */

public class NextGreaterElement_II {
    /* ********************************* APPROACH 1 : STACK NGE-I *************************************
    * Efficient Approach: The idea is to use the concept of "Next Greater Element" which uses a Stack
      data structure. Follow the steps below to solve the problem:
        * Initialize a stack to store the indices of the array and an array nge[] of size 'N' which
          stores the next greater element for each array element.
        * Traverse the array and for each index, perform the following:
            * If the stack is non-empty, and the current 'ith array element' is greater than the top
              element of the stack, then pop the top element of the stack, and update nge[st.top()] by
              storing "arr[i % N]" in it.
              Repeat this until the stack is empty or the current element is less '<=' than the element
              at the top of the stack.
            * If the stack is empty or the current element is less than the top of the stack, push 'i'
              into the stack. Only if 'i' < 'N'.
            * After a single traversal of N elements, the stack contains the elements which do not
              have a next greater element till the (N – 1)th index.
            * As the array is circular, consider all the elements from the 0th index again and find
              the next greater element of the remaining elements.
            * Since the array is traversed 2 times, it is better to use i % N instead of i.
            * After completing the above steps, print the array nge[].

     * Time complexity : O(2n) + O(2n) = O(n)
     * Only two traversals of the array are done. O(2n) for two traversals.
     * Further, atmost 2n elements are pushed and popped from the stack.

     * Space complexity : O(n)
     * A stack of size n is used.
     */
    public int[] nextGreaterElements_StackSolution1(int[] arr) {
        // Same as NGE-I
        // Just Circular Array is looping for '0' to '2n-1'
        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        int[] nextGreaterElement = new int[n];

        for (int i = 0; i < 2*n; i++){
            // If stack is not empty and current element is greater than top element of the stack
            while (!stack.isEmpty()  &&  arr[stack.peek()] < arr[i % n])
                // Assign next greater element for the top element of the stack, and pop it too
                nextGreaterElement[stack.pop()] = arr[i % n];

            // We can do something better here, as we are traversing from start. We either already have calculated
            // NGE or stored index for NGE calculation, so we don't need to insert the indices beyond 'n'
            // As we have already pushed all indices from i = '0' to 'n-1' into the stack once
            if (i < n)
                stack.push(i);
        }
        while (!stack.isEmpty())
            nextGreaterElement[stack.pop()] = -1;

        return nextGreaterElement;
    }


    /*
    * Time complexity : O(2n) + O(2n) = O(n)
        * Only two traversals of the array are done. O(2n) for two traversals.
        * Further, atmost 2n elements are pushed and popped from the stack.

    * Space complexity : O(n)
        * A stack of size n is used.
     */
    public int[] nextGreaterElements_StackSolution2(int[] arr) {
        // Same as NGE-I
        // Just Circular Array is looping for '0' to '2n-1'
        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        int[] nextGreaterElement = new int[n];

        for (int i = 2*n -1; i >= 0; i--){
            // Remove all the elements in Stack that are less than arr[i%n], as they can't be NGE for "current element"
            while (!stack.isEmpty()  &&  arr[i % n] >= stack.peek())
                stack.pop();

            nextGreaterElement[i % n] = !stack.isEmpty() ? stack.peek() : -1;
            stack.push(arr[i % n]);
        }

        return nextGreaterElement;
    }
}
