package Heaps.FindMedianFromDataStream;
import java.util.PriorityQueue;

// Great Intuition in VIDEO:
// https://youtu.be/Yv2jzDzYlp8
// https://www.geeksforgeeks.org/median-of-stream-of-integers-running-integers/

/*
 **************************************** INTUITION ****************************************
 * Min And Max Heap Approach

 * The idea is to maintain two equal halves of the current stream:
   the "right half" containing greater valued elements (Min-Heap), and the left half
   containing lower and equal valued element wrt median (Max-Heap).
   Maximum element of MaxHeap (left/left half) will always contribute to the median.
 * We arrange Max-Heap (to track smaller numbers) so that it can have size equal to OR one
   greater than Min-Heap (to track greater numbers).
 * So, we can say that "LEFT HALF (MAX-HEAP) WILL ALWAYS HAVE A MEDIAN" (BOTH IN CASE OF ODD & EVEN ELEMENTS)

   For each new addition, the transition between elements in the two halves goes this way:
        1) If the new element is less than or equal to the maximum element of the left half (Max-Heap),
           then this element must be present before the median. So, we add it into the
           left part (Max-Heap).
           After the addition, if the size of MaxHeap is greater than size of MinHeap by '2'.
           Then to maintain the balance, the maximum element is removed from the Maxheap and added
           to the MinHeap.
        2) If the new element is greater than the maximum element of the left half (Max-Heap), then
           this element must be present after the median. So, we add it into the right half (Min-Heap)
           Now, if the size of MinHeap is greater than size of MinHeap, then to maintain the balance,
           the minimum element is removed from the Minheap and added to the MaxHeap.
 * By maintaining the halves in this way, we assure that the elements of the stream are equally
   distributed between the two halves.

 * Hence, after each addition the maximum element of the left half and the minimum element
   of the right half become the middle-most elements, which we can use to compute the median.
 * Considering the odd elements case, MaxHeap (left-half) has more elements,
   the maximum of MaxHeap will be the median.
 * Considering the even elements case, average of maximum of MaxHeap (left half) and minimum of
   (MinHeap) right half will be the median.
 */

public class MedianFinder {
    /* ********************************* Efficient Heap Solution *************************************8
    * heap.size() & heap.peek() takes O(1) time
    * heap.add() & heap.remove() take O(log(n)) time
    * Time Complexity:
        1) O(log(n))                     for Add operation
           We are either adding element into the Heap and removing values to balance the Heaps.
           Maximum of three operations of add()/remove() are done in the method
           This takes O(log(n)) time only
        2) O(1)                          for Finding Median
           We just need peak values of Both the Heaps to find the Median
    * Space Complexity:   O(n)      for Two Heaps Used

    */
    // Max-Heap to store elements that are less or equal to the median
    private final PriorityQueue<Integer> leftNumbersMaxHeap;
    // Min-Heap to store elements that are greater than the median
    private final PriorityQueue<Integer> rightNumbersMinHeap;

    // Max-Heap (to track smaller numbers) can have size equal to OR one greater than Min-Heap (to
    // track greater numbers)
    public MedianFinder() {
        leftNumbersMaxHeap = new PriorityQueue<>((a, b) -> (b - a));
        rightNumbersMinHeap = new PriorityQueue<>((a, b) -> (a - b));
    }

    public void addNum(int num) {
        if (leftNumbersMaxHeap.size() == 0)
            leftNumbersMaxHeap.add(num);

        else if (num <= leftNumbersMaxHeap.peek()){
            leftNumbersMaxHeap.add(num);

            if (leftNumbersMaxHeap.size() > rightNumbersMinHeap.size() + 1)
                rightNumbersMinHeap.add(leftNumbersMaxHeap.remove());
        }
        else{
            rightNumbersMinHeap.add(num);

            if (rightNumbersMinHeap.size() > leftNumbersMaxHeap.size())
                leftNumbersMaxHeap.add(rightNumbersMinHeap.remove());
        }
    }

    public double findMedian() {
        if (leftNumbersMaxHeap.size() == rightNumbersMinHeap.size())
            return (leftNumbersMaxHeap.peek() + rightNumbersMinHeap.peek()) / 2.0;

        if (leftNumbersMaxHeap.size() == rightNumbersMinHeap.size() + 1)
            return leftNumbersMaxHeap.peek();

        return -1;
    }
}
