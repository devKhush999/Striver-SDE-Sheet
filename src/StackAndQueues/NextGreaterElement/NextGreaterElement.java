package StackAndQueues.NextGreaterElement;
import java.util.Stack;

// https://youtu.be/Du881K7Jtk8     // STRIVER
// https://youtu.be/8BDKB2yuGyg     // NICK WHITE
// https://takeuforward.org/data-structure/next-greater-element-using-stack/
// https://www.geeksforgeeks.org/next-greater-element/
// https://www.geeksforgeeks.org/next-greater-element-in-same-order-as-input/

public class NextGreaterElement {
    /* ******************************** Solution 1: Brute Force ********************************
        * Simple logic
        * Find the solution using two nested for loops
        * For each element in array, arr[i] (using a for loop 'i'), run a loop from i+1 to N, and
          check whether the current element, arr[j] is greater than arr[i]. If so, update next greater
          of current element, i.e, arr[i] to arr[j] and break the loop.
        *
        * Time Complexity: O(n^2)
          In the worst case, we will be searching for the next greater element of each element.
          For searching the next greater element in array it can take O(n) time, thus total time
          would be O(N^2).
        * Space Complexity: O(1)
          * Ignoring Output Array, we aren't using any extra space
     */
    public int[] nextGreater_BruteForce(int[] arr, int n) {
        int[] nextGreaterElement = new int[n];

        main:
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i]) {
                    nextGreaterElement[i] = arr[j];
                    continue main;
                }
            }
            nextGreaterElement[i] = -1;
        }
        return nextGreaterElement;
    }


    /* ******************************** Solution 2: Using Stack ********************************
    * ************** Algorithm **************:
    * Iterate the elements of array one by one and follow the following steps in loop.
    * If stack is not empty, compare "top element of stack" with "current element".
    * If "current element" is greater than the top element, pop index (of element) from stack.
      "current element" is the next greater element for the popped element.
    * Keep popping from the stack while the popped element is smaller than "current element".
      "current element" becomes the next greater element for all such popped elements.
    * Finally, push the index of "current element" into the index.
    * If Stack is non-empty, then next greater element of all such element is -1, as we couldn't find
      the next greater element for them.
    *
    * Time Complexity: O(n) + O(n) = O(n)
      * We are linearly traversing the array using for loop, this takes O(n).
      * In each iteration, we are running a while loop, in which, we push and pop the indices of array
        elements. Entire while can run only O(n) times.
      * Understand using a test case like arr = [5,4,3,2,1]
      * Both while loop & for loop runs only 'n' times, even though while loop is inside for loop
      * In the worst case, we will be pushing and popping all the elements into the stack only once.
      This sum up to O(2n) = O(n) Time complexity
    * Space Complexity: O(n)    Space for Stack used
     */
    public int[] nextGreater_StackSolution_1(int[] arr, int n){
        int[] nextGreaterElement = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++){
            while (!stack.isEmpty()  &&  arr[stack.peek()] < arr[i]){
                nextGreaterElement[stack.pop()] = arr[i];
            }
            stack.push(i);
        }
        while (!stack.isEmpty())
            nextGreaterElement[stack.pop()] = -1;

        return nextGreaterElement;
    }


    /* ******************************** Solution 3: Using Stack ********************************
     * INTUITION: The stack will be arranged in the increasing order of array's element (WATCH VIDEO)
     * How? See below process.
     * In this approach we have started iterating from the last element(nth) to the first(1st) element
     * The benefit is that when we arrive at a certain index, its next greater element will be
       already in stack, and we can directly get this element while at the same index.
     * For every element at index 'i',
        * We will pop the elements from the stack till we get an element "greater" than the "current element"
          on top of the stack and that element will be the answer for the current element.
        * The main intuition behind popping them is that these elements can never be the
        * "next greater element" for any element present at the left of arr[i], because arr[i] is
          greater than these elements.
        * If the stack gets empty while doing the pop operation then the answer would be -1, as we
          couldn't find the next  greater element for current element.
        * Store the next greater element in the array and push the current element of the array
          into the stack. (as it can be the next greater element for some previous element).
     *
     * Time Complexity:  O(N),   where N is the length of the given array.
       In the worst case, we will be pushing and popping all the elements into the stack only once.
       Thus, complexity would be O(N).
     * Space Complexity: O(N)   due to the stack
     */

    public int[] nextGreater_StackSolution_2(int[] arr, int n){
        int[] nextGreaterElement = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--){
            // We will pop till we get the greater element on top or stack gets empty
            // Remove all the elements in Stack that are less than arr[i], as they can't be NGE for "current element"
            while (!stack.isEmpty()  &&  arr[i] >= stack.peek())
                stack.pop();

            // If stack gets empty means there is no element on right which is greater than the
            // current element. If not empty then the next greater element is on top of stack
            nextGreaterElement[i] = !stack.isEmpty() ? stack.peek() : -1;

            // Push the current element into the stack, as it can be the next greater element for some element
            stack.push(arr[i]);
        }
        return nextGreaterElement;
    }
}
