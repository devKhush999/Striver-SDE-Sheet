package StackAndQueues.ImplementStackUsingQueues;
import java.util.LinkedList;
import java.util.Queue;

// Great Video:
// https://youtu.be/jDZQKzEtbYQ
// https://leetcode.com/problems/implement-stack-using-queues/solution/

/** ****************************** Approach 1 : Using Two Queues ******************************
 * This method ensures that every new element entered the queue ‘q1’ is always at the front of 'q1'.
 * Hence, during pop operation, we just dequeue from ‘q1’.
 * For this, we need another queue ‘q2., which is used to keep every new element to the front of ‘q1’.
 *
 * During push operation :
    * Enqueue new element ‘x’ to queue ‘q2’.
    * One by one, dequeue everything from ‘q1’ and enqueue to ‘q2’.
    * Swap the names of ‘q1’ and ‘q2’.
    * During each push operation, we transfer all the elements of ‘q1’ to ‘q2’.
 * During pop operation :
 * Dequeue an element from ‘q1’ and return it.
 * For the size function, return the size of queue ‘q1’
 * For the empty function, check if ‘q1’ is empty.
 *
 */

/**
 *********** Time Complexity ***********:
 * Push: O(n)         During each push operation, we transfer all the elements of ‘q1’ to ‘q2’.
 * Pop:  O(1)
 * Top: O(1)
 * Empty: O(1)
 *
 * Space Complexity:  O(n)
 * We use two queues for this purpose.
 */

public class StackUsingQueue_CostlyPush {
    private Queue<Integer> q1;
    private Queue<Integer> q2;

    public StackUsingQueue_CostlyPush() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public void push(int data) {
        q2.add(data);

        while (!q1.isEmpty())
            q2.add(q1.remove());

        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    public int pop() {
        return q1.remove();
    }

    public int top() {
        return q1.peek();
    }

    public boolean empty() {
        return q1.isEmpty();
    }

    public int getSize(){
        return q1.size();
    }
}