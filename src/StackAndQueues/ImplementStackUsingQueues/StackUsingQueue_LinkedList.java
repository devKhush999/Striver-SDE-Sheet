package StackAndQueues.ImplementStackUsingQueues;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Both "Queue<>" and "Deque<>" in Java are implemented using "LinkedList<>()"
 */

public class StackUsingQueue_LinkedList {
    private final Deque<Integer> deque;

    public StackUsingQueue_LinkedList() {
        deque = new LinkedList<>();
    }

    public void push(int data) {
        deque.addLast(data);
    }

    public int pop() {
        return deque.removeLast();
    }

    public int top() {
        return deque.getLast();
    }

    public boolean empty() {
        return deque.isEmpty();
    }
}
