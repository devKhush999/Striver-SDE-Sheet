package Heaps.KthSmallestAndLargestElementOfArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

// https://www.codingninjas.com/codestudio/problems/kth-smallest-and-largest-element-of-array_1115488
// https://takeuforward.org/data-structure/kth-largest-smallest-element-in-an-array/

public class KthSmallestAndLargestElementOfArray {
    /*
    *************************************** Sorting **********************************************
    * The most naive approach is to sort the given array in increasing order.
    * The index of kth the Largest element = n-k
    * The index of kth the Smallest element = k-1 ( zero based indexing )
    * Time Complexity: O(n * log(n))
    * Space Complexity: O(1)
     */
    public static ArrayList<Integer> kthSmallLarge_BruteForce(ArrayList<Integer> arr, int n, int k) {
        Collections.sort(arr);
        ArrayList<Integer> kthSmallerAndLarger = new ArrayList<>();

        kthSmallerAndLarger.add(arr.get(k - 1));        // kth Smallest element
        kthSmallerAndLarger.add(arr.get(n - k));        // kth Largest element
        return kthSmallerAndLarger;
    }


    /*
    *********************************** Using Heaps ***************************************
    * The idea is to construct a min-heap of elements.
    *  Since the top element of the min-heap is the smallest element of the heap,
    * We find the index at which kth-smallest element & kth-largest element would be located/
    * We iterate over the min-heap & check is reached the desired position and add it into the
      arraylist accordingly.
    * We keep on remove elements from the heap.

    * Time complexity: 2 * O(n * log(n))    n = size of array
      Reason:
      1) We build min-heap using elements of array in O(n * log(n)) Time complexity
         Adding n elements into heap, each addition take log(n) time
      2) Then, we keep on iterating & removing elements from heap until we found answer
         Each deletion takes log(n) time.
    * Space complexity: O(n) for Min-Heap
     */
    public static ArrayList<Integer> kthSmallLarge(ArrayList<Integer> arr, int n, int k) {
        // Indices of kth smallest & largest element
        int kthSmallerIndex = k - 1;
        int kthLargerIndex = n - k;

        // Building a min-heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(arr);

        // Find the kth smallest & largest element
        int kthSmallerElement = 0, kthLargerElement = 0;
        int i = 0;
        while (i < n){
            if (i == kthSmallerIndex  &&  i == kthLargerIndex)
                kthLargerElement = kthSmallerElement = minHeap.remove();
            else if (i == kthSmallerIndex)
                kthSmallerElement = minHeap.remove();
            else if (i  == kthLargerIndex)
                kthLargerElement = minHeap.remove();
            else
                minHeap.remove();
            i++;
        }

        ArrayList<Integer> kthSmallerAndLarger = new ArrayList<>();
        kthSmallerAndLarger.add(kthSmallerElement);
        kthSmallerAndLarger.add(kthLargerElement);
        return kthSmallerAndLarger;
    }
}
