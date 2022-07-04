package StackAndQueues.MinStack.MinStack_Compact;
import java.util.Stack;

// https://youtu.be/V09NfaGf2ao
// https://takeuforward.org/data-structure/implement-min-stack-o2n-and-on-space-complexity/
// https://www.geeksforgeeks.org/design-a-stack-that-supports-getmin-in-o1-time-and-o1-extra-space/

/*
* ******************************** Time Complexity ********************************
* Push: O(1)
* Pop: O(1)
* Peek: O(1)
* GetMinimum: O(1)
*
* ******************************** Space Complexity ********************************
* Total Space Complexity: O(2n)
* One O(n) Due to stack used
* Another O(n) for storing pairs/nodes of values inside stack
*
* This is slight variation of previous approach
 */

public class MinStack {
    private Stack<Node> stack;
    public MinStack() {
        this.stack = new Stack<>();
    }

    public void push(int num) {
        if (stack.isEmpty())
            stack.push(new Node(num, num));
        else {
            int minValue = Math.min(num, stack.peek().minValue);
            stack.push(new Node(num, minValue));
        }
    }

    public int pop() {
        return !stack.isEmpty() ? stack.pop().data : -1;
    }

    public int top() {
        return !stack.isEmpty() ? stack.peek().data : -1;
    }

    public int getMin() {
        return !stack.isEmpty() ? stack.peek().minValue : -1;
    }

    static class Node{
        int data, minValue;
        public Node(int data, int minValue) {
            this.data = data;
            this.minValue = minValue;
        }
    }
}
