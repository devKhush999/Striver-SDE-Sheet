package StackAndQueues.NextSmallerElement;
import java.util.Stack;

// PRE-REQUISITE: "NEXT GREATER ELEMENT" Both versions by Striver
// For Brute Force & Optimal Solution See:
// https://www.geeksforgeeks.org/next-smaller-element/
// https://www.codingninjas.com/codestudio/problem-details/next-smaller-element_1112581
// https://youtu.be/_RtghJnM1Qo

/* ************************* Brute Force *************************
* Time Complexity: O(n^2)
* Space Complexity: O(1)

   ************************* Stack Solution *************************
* Time Complexity: o(n)
* Space Complexity: o(n)
 */

public class NextSmallerElement {
    // ***************************** Approach 1 ********************************************
    private int[] nextSmallerElement_V1(int[] arr, int n){
        Stack<Integer> stack = new Stack<>();
        int[] nextSmallerElement = new int[n];

        for (int i = 0; i < n; i++){
            while (!stack.isEmpty()  &&  arr[stack.peek()] > arr[i])
                nextSmallerElement[stack.pop()] = arr[i];

            stack.push(i);
        }
        while (!stack.isEmpty())
            nextSmallerElement[stack.pop()] = -1;

        return nextSmallerElement;
    }


    // ***************************** Approach 2 ********************************************
    // This problem is similar to next greater element.
    // Here we maintain items in decreasing order in the stack (instead of increasing order in next
    // greater element problem).
    private int[] nextSmallerElement_V2(int[] arr, int n){
        Stack<Integer> stack = new Stack<>();
        int[] nextSmallerElement = new int[n];

        for (int i = n-1; i >= 0; i--){
            while (!stack.isEmpty()  &&  arr[i] <= stack.peek())
                stack.pop();

            nextSmallerElement[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(arr[i]);
        }
        return nextSmallerElement;
    }
}
