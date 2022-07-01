package StackAndQueues.ImplementStackUsingQueues;
import java.util.LinkedList;
import java.util.Queue;

// Great Video:
// https://youtu.be/jDZQKzEtbYQ
// https://leetcode.com/problems/implement-stack-using-queues/solution/
// https://takeuforward.org/data-structure/implement-stack-using-single-queue/

/**
 * ****************************** Approach 3 : Using One Queue ******************************
 * We will be using a single queue ‘q1’.
 *
 * ************ Push Operations ************
 * In a push operation, we can calculate the size of the queue ‘q1’.
   Hence, we enqueue new data to the queue.
 * Now suppose before inserting new data size of the queue is ‘x’, Hence we dequeue ‘x’ elements
   from the queue and push it back again into the same queue.
 * This would push the new element to the front of the queue.
 * During each push operation, we re-transfer all the elements of ‘q1’ back to ‘q1’.
 *
 * ************ Pop Operation **************
 * The element we are searching for is the front of the queue.
 * Hence, during pop operation simply access the front of the queue and remove the element.
 *
 * ************ Top Operation **************
 * During top operation simply access the front element of the queue.
 *
 * Hence, assuming we know the size of the queue at any instance we can solve this problem using a single queue.
 */

/**
 *********** Time Complexity ***********:
 * Push: O(n)      During each push operation, we re-transfer all the elements of ‘q1’ back to ‘q1’.
 * Pop:  O(1)
 * Top: O(1)
 * Empty: O(1)
 *
 * Space Complexity:  O(n)
 * We are using only one queue.
 */
public class StackUsingOneQueue {
    private final Queue<Integer> queue;

    public StackUsingOneQueue() {
        queue = new LinkedList<>();
    }

    public void push(int data) {
        queue.add(data);

        int size = queue.size();

        for (int i = 1; i < size; i++)
            queue.add(queue.remove());
    }

    public int pop() {
        return queue.remove();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
