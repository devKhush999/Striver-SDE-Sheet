package StackAndQueues.ImplementQueuesUsingStacks;
import java.util.Stack;

// https://youtu.be/3Et9MrMc02A
// See PPT in TUF Website:
// https://takeuforward.org/data-structure/implement-queue-using-stack/
// See PPT in leetcode submission:
// https://leetcode.com/problems/implement-queue-using-stacks/

/**
 * ********************************* Approach 1: Costly enQueue **************************************

 * ******************** Push Operation ********************
 * We can make the enQueue operation costly, and perform the deQueue and all other operations in
   constant time.
 * We use two instances of the stack. Whenever we need to insert an element in the queue, we transfer
   all elements from stack 1 to stack 2, push the element in stack 2, and then again push all elements
   from stack 2 to stack 1.
 * As the latest entered element is supposed to be at the back of the queue, this method ensures that
   this element is at the bottom of the stack.

 * ******************** Pop & peek Operation ********************
 * The above strategy ensures that the oldest entered element will always remain at the top of
   stack 1 so that to perform the deQueue or the peek operation, we simply return the top element
   of stack 1.

 * The time complexities of individual operations are:
 * enQueue : O(N), where ‘N’ is the number of elements in the stack.
 * deQueue : O(1)
 * peek : O(1)
 * isEmpty : O(1)
 *
 * Space Complexity: O(n)
 * where ‘n’ is the maximum number of elements which are in the stack at a time.
 * Because we are using two stacks
 */

public class ImplementQueuesUsingStacks_CostlyEnQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public ImplementQueuesUsingStacks_CostlyEnQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void enQueue(int val) {
        while (!stack1.isEmpty())
            stack2.push(stack1.pop());

        stack2.push(val);

        while (!stack2.isEmpty())
            stack1.push(stack2.pop());
    }

    public int deQueue() {
        return !stack1.isEmpty() ? stack1.pop() : -1;
    }

    public int peek() {
        return !stack1.isEmpty() ? stack1.peek() : -1;
    }

    public boolean isEmpty() {
        return stack1.isEmpty();
    }
}
