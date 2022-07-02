package StackAndQueues.ImplementQueuesUsingStacks;
import java.util.Stack;

// https://youtu.be/3Et9MrMc02A
// See PPT in TUF Website:
// https://takeuforward.org/data-structure/implement-queue-using-stack/
// See PPT in leetcode submission:
// https://leetcode.com/problems/implement-queue-using-stacks/

/**
 * ***************************** Approach 3: Amortized O(1) Pop Operation *****************************
 *
 * ************ Amortized DeQueue & Peek Operation ************
 * We can make the deQueue operation amortized, i.e, average Time for Pop will be O(1) but worst case
   Time Complexity will be O(n)
 * And perform the enQueue operation in constant time.
 * Now to perform the deQueue operation, we know that if elements are inserted in the
   order let us say: a1 then a2 then a3, then the removal will also be in the same order
   i.e, a1 then a2 then a3.
 * Thus for removal operation, we push all elements of ‘s1’ to ‘s2’ (to reverse their order of removal)
   and pop the top element of ‘s2’, but we don;t push back elements of 's2' back to 's1'.
 * This is the main difference b/w Approach 2 & Approach 3
 * The advantage of this approach over the first one is that we don't need to transfer all elements
   from ‘s1’ to ‘s2’ every time. As long as ‘s2’ is not empty we don't need to transfer the elements
   from 's1' to 's2'.
 *
 *
 * ************ Push Operation ************
 * Use two stacks, let us say ‘s1’ and ‘s2’. To insert into the queue, simply push the element at
 the top of ‘s1’.

 * The time complexities of individual operations are:
 * enQueue : O(1)
 * deQueue : O(1) in average case or O(n) in worst case
 * peek : O(1) in average case or O(n) in worst case
 * isEmpty : O(1)
 *
 * Space Complexity: O(n)
 * where ‘N’ is the maximum number of elements which are in the stack at a time.
 */

public class ImplementQueueUsingStack_Amortized {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public ImplementQueueUsingStack_Amortized() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void enQueue(int val) {
        stack1.push(val);
    }

    public int deQueue() {
        if (stack2.isEmpty()){
            if (stack1.isEmpty())
                return -1;

            while (!stack1.isEmpty())
                stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    public int peek() {
        if (stack2.isEmpty()){
            if (stack1.isEmpty())
                return -1;

            while (!stack1.isEmpty())
                stack2.push(stack1.pop());
        }
        return stack2.peek();
    }

    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
