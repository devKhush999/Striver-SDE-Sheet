package Stack.ImplementStack;

public class StackUsingArray {
    private int maxSize;
    private int[] stack;
    private int index;
    private int size;

    public StackUsingArray(int maxSize){
        this.maxSize = maxSize;
        this.index = 0;
        this.size = 0;
        this.stack = new int[maxSize];
    }

    void push(int num) {
        if (size == maxSize)
            return;
        stack[index] = num;
        index++;
        size++;
    }

    int pop() {
        if (size <= 0)
            return -1;

        size--;
        return stack[--index];
    }

    int top() {
        if (size <= 0)
            return -1;
        return stack[index - 1];
    }

    int isEmpty() {
        return size == 0 ? 1 : 0;
    }

    int isFull() {
        return size == maxSize ? 1 : 0;
    }
}
