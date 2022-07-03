package StackAndQueues.SortAStack;
import java.util.ArrayList;
import java.util.Stack;

// https://youtu.be/MOGBRkkOhkY
// https://www.geeksforgeeks.org/sort-a-stack-using-recursion/
// https://www.geeksforgeeks.org/sort-stack-using-temporary-stack/

public class SortAStack {
    /* ********************************** Simple Brute Force Approach **********************************
    * Time Complexity: O(n * log(n))        Due to sorting
    * SPace Complexity: O(n)                Dut to arrayList used
     */
    private void sortStack_BruteForce(Stack<Integer> stack) {
        // Declare an "Arraylist" to hold the stack elements
        ArrayList<Integer> list = new ArrayList<>();

        // Add all elements present in the "stack" into a "ArrayList"
        while (!stack.isEmpty())
            list.add(stack.pop());

        // Sort the "ArrayList" list in descending order
        list.sort((a, b) -> (b - a));

        // Add elements into the "stack" from back of the ArrayList, since arraylist is sorted descending-ly
        for (int i = list.size() - 1; i >= 0; i--)
            stack.push(list.remove(i));
    }



    /* ***************************** Using Only Recursion & Stack Operations ****************************
     * INTUITION: The idea of the solution is to hold all values of the stack in "Function Call Stack"
                  until the stack becomes empty.
                  When the stack becomes empty, insert all held items one by one in sorted order.
                  Here sorted order is important

     * Time Complexity:     O(N^2), where N is the number of elements in the stack.
        * In the worst case (happens when the entire stack is sorted in increasing order) for every
          sortStack(), insertInTopOfSortedStack() is called for ‘N’ times recursively for putting
          element to the right place.
     * Space Complexity:    O(N), where N is the number of elements in the stack.
        * At max, there can be O(n) calls in Recursion stack space
        * This is the space used by the Recursion stack space to store the elements.
     */
    // Method to sort stack
    private void sortStack(Stack<Integer> stack) {
        // If stack is empty, simply return
        if (stack.isEmpty())
            return;
        // Remove the top item
        int top = stack.pop();

        // Recursion for sorting the remaining elements in the stack
        sortStack(stack);

        // Push the top item back in sorted stack
        insertInTopOfSortedStack(stack, top);
    }

    // Recursive Method to insert a value 'currentElement' in sorted way
    private void insertInTopOfSortedStack(Stack<Integer> stack, int currentElement){
        // Base case: Either stack is empty or newly inserted item is greater than top (more than all existing)
        if (stack.isEmpty() || currentElement >= stack.peek()){
            stack.push(currentElement);
            return;
        }
        // Else If top of stack is greater, remove the top item and call same function (recursion)
        // to insert the 'currentElement' into the stack
        int top = stack.pop();
        insertInTopOfSortedStack(stack, currentElement);

        // Put back the top element of stack item removed earlier
        stack.push(top);
    }



    /* ************************************ Using One More Stack ************************************
     * We follow this algorithm.
        * Create a temporary stack say "sortedStack", it will store the stack elements in sorted fashion
        * While input stack is NOT empty do this:
            * Pop an element from input stack call it 'top'. While "sortedStack" is NOT empty and
              top of "sortedStack" is greater than temp, pop from "sortedStack" and push it to
              the input stack
            * Push 'top' in "sortedStack"
        * The sorted numbers are in "sortedStack"

     * Time Complexity:     O(N^2), where N is the number of elements in the stack.
        * In the worst case (happens when the entire stack is sorted in decreasing order) for every
          'top' element in the stack, we might pop() all the elements in the temporary/sorted stack
          and push then back to the input stack
     * Space Complexity:    O(N), where N is the number of elements in the stack.
        * This is the space used by the one more Stack
     */
    private Stack<Integer> sortStack_Iterative(Stack<Integer> stack){
        Stack<Integer> sortedStack = new Stack<>();

        while (!stack.isEmpty()){
            // pop out the top element of stack
            int top = stack.pop();

            // while "Sorted Stack" is not empty and top of the "Sorted Stack" is greater than top
            // of the "given stack", push smaller elements in the "Sorted Stack" back to the "given stack"
            while (!sortedStack.isEmpty()  &&  sortedStack.peek() > top)
                stack.push(sortedStack.pop());

            // push top in "Sorted Stack"
            sortedStack.push(top);
        }
        return sortedStack;
    }
}
