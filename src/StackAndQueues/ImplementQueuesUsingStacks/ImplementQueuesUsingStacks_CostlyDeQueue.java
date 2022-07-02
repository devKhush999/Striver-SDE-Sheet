package StackAndQueues.ImplementQueuesUsingStacks;
import java.util.Stack;

// https://youtu.be/3Et9MrMc02A
// See PPT in TUF Website:
// https://takeuforward.org/data-structure/implement-queue-using-stack/
// See PPT in leetcode submission:
// https://leetcode.com/problems/implement-queue-using-stacks/

/**
 * ********************************* Approach 1: Costly deQueue **************************************

 * ***************** Push Operation *****************************
 * In this approach we assume top of the 'stack 1' stores the last element, i.e, the last element of the
   Queue are added to the top of the stack1
 * Push Operation is done in O(1) time, simply push it into the stack.
 * As the latest entered element is supposed to be at the back of the queue, this method ensures that
    this element is at the 'top' of the stack.

 * ******************** Pop/DeQueue Operation ********************
 * We can make the deQueue operation costly, and perform the enQueue and all other operations in
 constant time.
 * We use two instances of the stack. Whenever we need to delete an element from the queue, we transfer
   all elements from 'stack 1' to 'stack 2', pop the top element of 'stack 2', and then again push
   all elements from 'stack 2' to 'stack 1'.
 * As the first entered element is supposed to be at the front of the queue, this method ensures that
   this element is at the 'bottom' of the stack. Hence, we are removing it from bottom

 * ******************** Peek Operation ********************
 * The above strategy ensures that the oldest entered element will always remain at the bottom of
 'stack 1' so that to perform the deQueue or the peek operation, we can maintain a "front element"
  of 'Queue', which will hold the front element inserted into it.

 * The time complexities of individual operations are:
 * enQueue : O(1)
 * deQueue : O(N),   where ‘N’ is the number of elements in the stack.
 * peek : O(1)
 * isEmpty : O(1)
 *
 * Space Complexity: O(n)
 * where ‘n’ is the maximum number of elements which are in the stack at a time.
 * Because we are using two stacks
 */

public class ImplementQueuesUsingStacks_CostlyDeQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    private int frontOfQueue = -1;

    public ImplementQueuesUsingStacks_CostlyDeQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    void enQueue(int val) {
        if (stack1.isEmpty())
            frontOfQueue = val;

        stack1.push(val);
    }

    int deQueue() {
        if (stack1.isEmpty())
            return -1;

        while (!stack1.isEmpty())
            stack2.push(stack1.pop());

        int front = stack2.pop();
        this.frontOfQueue = !stack2.isEmpty() ? stack2.peek() : -1;

        while (!stack2.isEmpty())
            stack1.push(stack2.pop());

        return front;
    }

    int peek() {
        return frontOfQueue;
    }

    boolean isEmpty() {
        return stack1.isEmpty();
    }
}