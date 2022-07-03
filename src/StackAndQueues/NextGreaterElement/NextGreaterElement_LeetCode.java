package StackAndQueues.NextGreaterElement;
import java.util.HashMap;
import java.util.Stack;

// PRE-REQUISITE: NEXT GREATER ELEMENT

/** ********************************** APPROACH ***************************************
 * Idea is to calculate the next greater element for all elements in the array_2 (See
   "Next Greater element"). And put the next greater elements of each element in the array_2 in a
    HashMap.
 * Then get the NGE of all elements in array_1, as array_1 is a subset of array_2
 */

public class NextGreaterElement_LeetCode {
    public int[] nextGreaterElement_BruteForce(int[] arr1, int[] arr2) {
        int[] nextGreaterElement = new int[arr1.length];

        main:
        for (int i = 0; i < arr1.length; i++) {
            int j = 0;
            while (j < arr2.length  &&  arr2[j] != arr1[i])
                j++;

            for (j = j + 1; j < arr2.length; j++) {
                if (arr2[j] > arr1[i]){
                    nextGreaterElement[i] = arr2[j];
                    continue main;
                }
            }
            nextGreaterElement[i] = -1;
        }
        return nextGreaterElement;
    }



    public int[] nextGreaterElement_Stack1(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> nextGreaterMap = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr2.length; i++){
            while (!stack.isEmpty()  &&  stack.peek() < arr2[i])
                nextGreaterMap.put(stack.pop(), arr2[i]);

            stack.push(arr2[i]);
        }

        int[] nextGreaterElement = new int[arr1.length];

        for (int i = 0; i < nextGreaterElement.length; i++)
            nextGreaterElement[i] = nextGreaterMap.getOrDefault(arr1[i], -1);

        return nextGreaterElement;
    }



    public int[] nextGreaterElement_Stack2(int[] arr1, int[] arr2) {
        int[] nextGreaterElement = new int[arr1.length];

        HashMap<Integer, Integer> nextGreaterMap = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = arr2.length - 1; i >= 0; i--){
            while (!stack.isEmpty()  &&  arr2[i] >= stack.peek())
                stack.pop();

            int nge = !stack.isEmpty() ? stack.peek() : -1;
            nextGreaterMap.put(arr2[i], nge);
            stack.push(arr2[i]);
        }

        for (int i = 0; i < arr1.length; i++)
            nextGreaterElement[i] = nextGreaterMap.get(arr1[i]);

        return nextGreaterElement;
    }
}
