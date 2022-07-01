package StackAndQueues.ImplementQueue;

// https://youtu.be/M6GnoUDpqEE
// https://takeuforward.org/data-structure/implement-queue-using-array/

/**
 * This is the Queue implementation (using Nodes) of limited size.
 * Similarly, we can implement Queue without any Size restriction.
 */

public class Queue<E> {
    private Node<E> front;
    private Node<E> rear;
    private int size;
    private final int capacity;

    public Queue(int capacity) {
        this.front = null;
        this.rear = null;
        this.size = 0;
        this.capacity = capacity;
    }

    public void enqueue(E data) {
        if (this.isFull())
            return;

        if (this.isEmpty()){
            front = rear = new Node<>(data);
            size++;
            return;
        }

        rear.next = new Node<>(data);
        rear = rear.next;
        size++;
    }

    public E dequeue() {
        if (this.isEmpty())
            return null;
        Node<E> temp = front;
        front = front.next;
        size--;
        temp.next = null;
        return temp.data;
    }

    public E front() {
        if (this.isEmpty())
            return null;
        return front.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int getSize(){
        return size;
    }

    static class Node<E>{
        E data;
        Node<E> next;

        public Node(E data){
            this.data = data;
        }
    }
}
