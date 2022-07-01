package StackAndQueues.ImplementQueue;

// https://youtu.be/M6GnoUDpqEE
// https://takeuforward.org/data-structure/implement-queue-using-array/

public class ImplementQueueUsingArray {
    int[] queue;
    int front, rear;
    int size, maxSize;

    public ImplementQueueUsingArray(int maxSize) {
        this.maxSize = maxSize;
        this.front = this.rear = this.size = 0;
        this.queue = new int[maxSize];
    }

    public void enqueue(int data) {
        if (this.isFull())
            return;

        queue[rear] = data;
        rear = rear + 1 == maxSize ? 0 : rear + 1;
        size++;
    }

    public int dequeue() {
        if (this.isEmpty())
            return -1;

        int frontElement = queue[front];
        front = front + 1 == maxSize ? 0 : front + 1;
        size--;
        return frontElement;
    }

    public int front() {
        if (this.isEmpty())
            return -1;
        return queue[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == maxSize;
    }

    public int size(){
        return size;
    }
}
