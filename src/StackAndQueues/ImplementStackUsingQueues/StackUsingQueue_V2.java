package StackAndQueues.ImplementStackUsingQueues;
import java.util.LinkedList;
import java.util.Queue;

// Approach Link:
// https://leetcode.com/problems/implement-stack-using-queues/solution/

/** ****************************** Approach 2 : Using Two Queues ******************************
 * Push Operation:
 * The new element is always added to the rear of queue 'q1', and it is kept as top of the stack
   element.

 * Pop Operation:
 * We need to remove the element from the top of the stack. This is the last inserted element in q1.
   Because queue is FIFO (first in - first out) data structure, the last inserted element could be
   removed only after all elements, except it, have been removed.
   For this reason we need to maintain additional queue q2, which will serve as a temporary storage
   to enqueue the removed elements from q1. The last inserted element in q2 is kept as top.
   Then the algorithm removes the last element in q1. We swap q1 with q2 to avoid copying
   all elements from q2 to q1.

 * Top Operation:
 * Its will be the top() of 'q1'
 */

/**
 *********** Time Complexity ***********:
 * Push: O(1)
 * Pop:  O(n)       During each pop operation, we transfer all the elements of ‘q1’ to ‘q2’.
 * Top: O(1)
 * Empty: O(1)
 *
 * Space Complexity:  O(n)
 * We use two queues for this purpose.
 */


public class StackUsingQueue_V2 {
    private Queue<Integer> q1;
    private Queue<Integer> q2;
    private int topOfStack;

    public StackUsingQueue_V2() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
        topOfStack = -1;
    }

    public void push(int data) {
        topOfStack = data;
        q1.add(data);
    }

    public int pop() {
        int size = q1.size();

        for (int i = 1; i < size; i++) {
            topOfStack = q1.peek();
            q2.add(q1.remove());
        }

        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
        return q2.remove();
    }

    public int top() {
        return topOfStack;
    }

    public boolean empty() {
        return q1.isEmpty();
    }
}
