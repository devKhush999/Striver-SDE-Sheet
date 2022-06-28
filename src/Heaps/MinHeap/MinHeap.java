package Heaps.MinHeap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

// https://www.codingninjas.com/codestudio/problems/min-heap_4691801

public class MinHeap {
    private static int[] minHeap(int n, int[][] q) {
        // For Max-Heaps
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // For Min-Heaps
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        ArrayList<Integer> removedItems = new ArrayList<>();

        for (int i = 0; i < n; i++){
            if (q[i][0] == 0)
                minHeap.add(q[i][1]);
            else
                removedItems.add(minHeap.remove());
        }

        int[] removedValues = new int[removedItems.size()];
        for (int i = 0; i < removedItems.size(); i++)
            removedValues[i] = removedItems.get(i);
        return removedValues;
    }
}
