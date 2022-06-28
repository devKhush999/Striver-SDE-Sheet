package Heaps.KthSmallestAndLargestElementOfArray;
import java.util.Arrays;
import java.util.PriorityQueue;

// https://www.youtube.com/watch?v=yAs3tONaf3s

public class KthLargestElementInArray {
    /*
   ****************************** Min Heap Solution ******************************
   * Time complexity:  O(n * log(n))   n = size of array
     Reason:
     1) We build min-heap using first 'k' elements of array in O(n * log(n)) Time complexity in worst case
        Adding n elements into heap, each addition take log(n) time
     2) Then we add only those values (after first 'k' elements), that are greater than min. value
        present at the peek of min heap
   * Space complexity: O(k) for Min-Heap which has only k elements
    */
    public int findKthLargest_ByMinHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i=0; i<k; i++)
            minHeap.add(arr[i]);
        
        for (int i=k; i<arr.length; i++){
            if (arr[i] > minHeap.peek()){
                minHeap.remove();
                minHeap.add(arr[i]);
            }
        }
        
        return minHeap.peek();
    }


    /*
    ****************************** Max Heap Easy Solution ******************************
    * Time complexity:  O(n * log(n))  +  O(k * log(n))   n = size of array
      Reason:
      1) We build max-heap using elements of array in O(n * log(n)) Time complexity
         Adding n elements into heap, each addition take log(n) time
      2) Then, we keep on iterating & removing elements from heap till the k-1 numbers
         Each deletion takes log(n) time. Hence, it takes O(k * log(n)) time
    * Space complexity: O(n) for Max-Heap
     */
    public int findKthLargest_ByMaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> (b-a));
        
        for (int val : arr)
            maxHeap.add(val);
        
        int i = 1;
        while (i < k) {
            maxHeap.remove();
            i++;
        }
        
        return maxHeap.peek();
    }


    /*
     *************************************** Sorting **********************************************
     * The most naive approach is to sort the given array in increasing order.
     * The index of kth the Largest element = n-k
     * Time Complexity: O(n * log(n))
     * Space Complexity: O(1)
     */
    public int findKthLargest_BySorting(int[] arr, int k) {
        Arrays.sort(arr);
        return arr[arr.length-k];
    }
}