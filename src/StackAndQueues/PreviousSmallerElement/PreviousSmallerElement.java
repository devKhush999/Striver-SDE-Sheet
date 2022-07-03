package StackAndQueues.PreviousSmallerElement;
import java.util.Stack;

// PRE-REQUISITE: "NEXT GREATER ELEMENT" Both versions by Striver
// For Brute Force & Optimal Solution See:
// https://www.geeksforgeeks.org/find-the-nearest-smaller-numbers-on-left-side-in-an-array/

// GREAT READING:
// https://medium.com/algorithms-digest/previous-smaller-element-e3996fb8be3c
// https://youtu.be/_RtghJnM1Qo

/* ************************* Brute Force *************************
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)

 ************************* Stack Solution *************************
 * Time Complexity: o(n)
 * Space Complexity: o(n)
 */

public class PreviousSmallerElement {
    // ***************************** Approach 1 ********************************************
    public int[] prevSmaller_StackSolution_1(int[] arr) {
        int[] prevSmallerElement = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = arr.length - 1; i >= 0; i--){
            while (!stack.isEmpty()  &&  arr[i] < arr[stack.peek()])
                prevSmallerElement[stack.pop()] = arr[i];

            stack.push(i);
        }
        while (!stack.isEmpty())
            prevSmallerElement[stack.pop()] = -1;

        return prevSmallerElement;
    }

    // ***************************** Approach 2 ********************************************
    // This problem is similar to next greater element.
    // Here we maintain items in decreasing order in the stack (instead of increasing in next
    // greater element problem).
    public int[] prevSmaller_StackSolution_2(int[] arr) {
        int[] prevSmallerElement = new int[arr.length];

        // Create an empty stack
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++){
            // Keep removing top element from stack while the top element is greater than or equal to arr[i]
            while (!stack.isEmpty()  &&  stack.peek() >= arr[i])
                stack.pop();

            prevSmallerElement[i] = !stack.isEmpty() ? stack.peek() : -1;

            // Push this element into stack, as it can be prev smaller for next elements
            stack.push(arr[i]);
        }
        return prevSmallerElement;
    }
}
