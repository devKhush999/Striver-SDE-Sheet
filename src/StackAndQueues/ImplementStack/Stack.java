package StackAndQueues.ImplementStack;

// https://youtu.be/GYptUgnIM_I

/**
 * Here is the Implementation of Stack Data Structure with a limited size
 * Using this we can implement Stack without any size restriction
 */

public class Stack<E> {
    int maxSize;
    int size;
    Node<E> top;

    public Stack(int maxSize){
        this.maxSize = maxSize;
        this.top = null;
        this.size = 0;
    }

    public void push(E num) {
        if (isFull())
            return;
        if (top == null){
            top = new Node<>(num);
            size++;
            return;
        }

        Node<E> temp = new Node<>(num);
        temp.next = top;
        top = temp;
        size++;
    }

    public E pop() {
        if (this.isEmpty())
            return null;

        Node<E> temp = top;
        top = top.next;
        size--;
        temp.next = null;
        return temp.data;
    }

    public E peek() {
        if (this.isEmpty())
            return null;
        return top.data;
    }

    boolean isEmpty() {
        // return top == null;
        return size == 0;
    }

    boolean isFull() {
        return size == maxSize;
    }


    public static class Node<E>{
        E data;
        Node<E> next;
        public Node(E data) {
            this.data = data;
        }
    }
}
