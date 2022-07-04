package StackAndQueues.MinStack.MinStack;

/*
 * ******************************** Time Complexity ********************************
 * Push: O(1)
 * Pop: O(1)
 * Peek: O(1)
 * GetMinimum: O(1)
 *
 * ******************************** Space Complexity ********************************
 * Total Space Complexity: O(n)
 * O(n) for storing pairs/nodes of values inside stack
 */

public class MinStack {
    private Node top;
    public MinStack() {
        this.top = null;
    }

    // Function to add another element equal to num at the top of stack.
    public void push(int num) {
        if (top == null)
            top = new Node(num, num);
        else{
            Node newTop = new Node(num, Math.min(top.minValue, num));
            newTop.next = top;
            top = newTop;
        }
    }

    // Function to remove the top element of the stack.
    public int pop() {
        if (top == null)
            return -1;
        Node topOfStack = top;
        top = top.next;
        topOfStack.next = null;
        return topOfStack.data;
    }

    // Function to return the top element of stack if it is present. Otherwise, return -1.
    public int top() {
        return top != null ? top.data : -1;
    }

    // Function to return minimum element of stack if it is present. Otherwise, return -1.
    public int getMin() {
        return top != null ? top.minValue : -1;
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
