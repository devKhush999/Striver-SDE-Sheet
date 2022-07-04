package StackAndQueues.MinStack.MinStack_Efficient;
import java.util.Stack;

// FOR INTUITION, DO WATCH THIS:
// https://youtu.be/V09NfaGf2ao
// https://takeuforward.org/data-structure/implement-min-stack-o2n-and-on-space-complexity/
// GREAT READING WITH INTUITION (MUST SEE):
// https://www.geeksforgeeks.org/design-a-stack-that-supports-getmin-in-o1-time-and-o1-extra-space/

/*
 * ******************************** Time Complexity ********************************
 * Push: O(1)
 * Pop: O(1)
 * Peek: O(1)
 * GetMinimum: O(1)
 *
 * ******************************** Space Complexity ********************************
 * Total Space Complexity: O(n)
 * O(n) for storing numerical values inside stack
 */

public class MinStack {
    private Stack<Long> stack;
    private long minimum;

    public MinStack() {
        this.stack = new Stack<>();
    }

    public void push(int value) {
        if (stack.isEmpty()){
            stack.push((long) value);
            minimum = value;
        }
        else{
            if (value >= minimum)
                stack.push((long) value);
            else if (value < minimum){
                stack.push(2L *value - minimum);
                minimum = value;
            }
        }
    }

    public int pop() {
        if (stack.isEmpty())
            return -1;
        long topValue = stack.pop();

        if (topValue >= minimum)
            return (int) topValue;
        else{
            int top = (int) minimum;
            minimum = (int) (2*minimum - topValue);
            return top;
        }
    }

    public int top() {
        if (stack.isEmpty())
            return -1;

        long top = stack.peek();
        // If 'top' >= 'minimum' means "top of stack" is present at the top.
        if (top >= minimum)
            return (int) top;
        // If 'top' < 'minimum' means 'minimum' stores the value of "top of stack"
        else
            return (int) minimum;
    }

    // Get the minimum number in the entire stack
    // variable "minimum" stores the minimum element in the stack.
    public int getMin() {
        if (stack.isEmpty())
            return -1;
        return (int) minimum;
    }

    static class Node{
        public int data, minValue;
        public Node next;
        public Node(int data, int minValue){
            this.data = data;
            this.minValue = minValue;
        }
    }
}

/*
************************************* How does this approach work? **************************************
* When element to be inserted (say 'x') is less than "minElement", we insert “2x – "minElement"”.
* The important thing to notes is, "2x – minElement" will always be less than 'x' (proved below),
  and 'x' will become the new "minElement" in the stack. And while popping out this element we will
  see that something unusual has happened as the popped element is less than the "minElement".
  So we will be updating "minElement".

* ***************************** PROVE 1 ****************************************
  * How "2*x - minElement" is less than x in push()?
    "x < minElement"  which means "x - minElement" < 0

        Adding x on both sides
        "x - minElement + x  <  0 + x"
        "2*x - minElement < x"

We can conclude "2*x - minElement <  x = new_minElement"
* *********************************************************************************


* While popping out, if we find the element(y) less than the current "minElement", we find the
  new "minElement" = "2 * minElement – y"


* ****************************** PROVE 2 ****************************************
    * How previous minimum element, previousMinElement is, "2*minElement - y"
      in pop() is y the popped element?

      We pushed y as "2*x - previousMinElement" (when minElement > x).
      Here, "previousMinElement" is "minElement" before y was inserted.
      y = 2*x - previousMinElement

    * Value of "minElement" was made equal to x, "minElement" = x (while pushing, when when minElement > x)
        "previousMinElement = 2 * minElement - y"
                            = 2*x - (2*x - previousMinElement)
                            = previousMinElement              // This is what we wanted

* Similar approach can be used to find the maximum element as well. Implement a stack that supports
  getMax() in O(1) time and constant extra space.
 */
