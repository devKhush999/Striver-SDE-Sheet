package StackAndQueues.ImplementStack;

// https://youtu.be/GYptUgnIM_I

/**
 * Here is the Implementation of Stack Data Structure with a limited size using Arrays
 */

public class StackUsingArray {
    private final int capacity;
    private final int[] stack;
    private int index;
    private int size;

    public StackUsingArray(int capacity){
        this.capacity = capacity;
        this.index = 0;
        this.size = 0;
        this.stack = new int[capacity];
    }

    void push(int num) {
        if (isFull())
            return;
        stack[index] = num;
        index++;
        size++;
    }

    int pop() {
        if (isEmpty())
            return -1;
        size--;
        return stack[--index];
    }

    int top() {
        if (isEmpty())
            return -1;
        return stack[index - 1];
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean isFull() {
        return size == capacity;
    }
}
