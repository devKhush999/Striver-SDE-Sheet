package Heaps.MergeKSortedArrays;
import java.util.ArrayList;
import java.util.PriorityQueue;

// MUST-WATCH FOR INTUITION:    🔥🔥🔥🔥🔥
// https://youtu.be/ptYUCjfNhJY
// https://www.codingninjas.com/codestudio/problems/merge-k-sorted-arrays_975379?leftPanelTab=0
// https://www.geeksforgeeks.org/merge-k-sorted-arrays/
// https://www.geeksforgeeks.org/merge-k-sorted-arrays-set-2-different-sized-arrays/
// https://medium.com/outco/how-to-merge-k-sorted-arrays-c35d87aa298e

/*  *********************************** INTUITION **************************************
*   It it the generalization of Question "MERGE TWO SORTED ARRAYS"
*   In that question we used two pointers for two arrays 'i' & 'j' pointing on arr1 & arr2 respectively.
    And we were figuring out the minimum of both the element in the array pointed by
    these pointers, i.e, arr1[i] & arr2[j]
*   In this question, we can't maintain 'k' numbers of pointers. So, we use a Min-Heap here to
    do this task, and we can find the minimum of these first 'k' values in O(1) time via MinHeap.
    We also store the indices in order to move next.
*   This is one of the great problem. FOR DETAILED INTUITION, WATCH VIDEO.
 */

public class MergeKSortedArrays_Efficient {
    /*
    * Time Complexity:   O((N * K) * log(K))
      Where ‘K’ is the number of arrays and ‘N’ is the average number of elements in every array.
      Min-Heap will have at most 'k' elements. Due to the insertion and the removal of elements in
      the heap, the final complexity of this approach is O(K * N * log(K)).
      This is because we also loop through all the elements present in the array, just like
      merging two arrays

    * Space Complexity:  O(K)
      where ‘K’ is the number of arrays
      At first we add minimum values of all 'k' arrays. And in each iteration, along with removing
      minimum element from the heap, we also add the next element from the same array (if next
      index is not out of bound)
    */

    public static ArrayList<Integer> mergeKSortedArrays(ArrayList<ArrayList<Integer>> kArrays, int k){
        // ArrayList to store all the sorted elements in 'k' sorted lists
        ArrayList<Integer> kSortedList = new ArrayList<>();

        // Create a min heap to store at most 'k' Minimum values at a time.
        // We can Comparator too. Have used Comparable here
        PriorityQueue<ArrayElement> kSizeMinHeap = new PriorityQueue<>();

        // Initially add only first column of elements. First element of every array, as they are the minimum
        for (int i = 0; i < k; i++) {
            ArrayElement minElementInIthArray = new ArrayElement(kArrays.get(i).get(0), i, 0);
            kSizeMinHeap.add(minElementInIthArray);
        }

        while (!kSizeMinHeap.isEmpty()){
            // Remove the minimum element from the Min-Heap
            ArrayElement minValue = kSizeMinHeap.remove();

            // Add the removed element to the output array
            kSortedList.add(minValue.value);

            // If the next element of the extracted element exists, add it to the heap
            if (minValue.indexInArray < kArrays.get(minValue.arrayNumber).size() - 1){
                int nextArrayElementIndex = minValue.indexInArray + 1;
                int nextArrayElementValue = kArrays.get(minValue.arrayNumber).get(nextArrayElementIndex);
                kSizeMinHeap.add(new ArrayElement(nextArrayElementValue, minValue.arrayNumber, nextArrayElementIndex));
            }
        }
        return kSortedList;
    }

    // Implements Comparable interface for min-heap.
    static class ArrayElement implements Comparable<ArrayElement>{
        int value, arrayNumber, indexInArray;
        public ArrayElement(int value, int arrayNumber, int i) {
            this.value = value;
            this.arrayNumber = arrayNumber;
            this.indexInArray = i;
        }

        @Override
        public int compareTo(ArrayElement a){
            return this.value - a.value;
        }
    }
}
/*
************************************** APPROACH **********************************************
*   The idea is to use the concept of min-heap. As we know, the root of the min-heap is always the
    minimum element in the heap. Thus, insert the first elements of all the ‘K’ arrays into the heap
    along with their indices, and then removing the minimum (root) element and adding it to the
    output array will give us the required result.
    We will store indices into the heap because we can get the next greater element from the same
    array where the current element has been popped.

*   Algorithm:
    * Create an output array ‘RESULT’.
    * Create a min-heap of size K and insert the first element of all the arrays, along with its
      indices, into the heap.
    * The heap is ordered by the element of the array/list.
    * While the min-heap is not empty, we will keep doing the following operations :
        1) Remove the minimum element from the min-heap (the minimum element is always at the root)
           and store it in the output array.
        2) Insert the next element from the array for which the current element is extracted.
            If the array doesn't have any more elements i.e. if the index of the above-removed
            component is equal to ‘LENGTH-1’ of the array from which the element is extracted,
            then do nothing. Because we are done with this array.

*   After the above process, 'RESULT' will contain all the elements of ‘K’ arrays in ascending order.
    Return the output array i.e. ‘RESULT’.
 */
