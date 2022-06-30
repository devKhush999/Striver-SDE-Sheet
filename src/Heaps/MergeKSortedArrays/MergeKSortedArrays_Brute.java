package Heaps.MergeKSortedArrays;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class MergeKSortedArrays_Brute {
    /*
    ************************************ BRUTE FORCE 1 ****************************************
    * Naive Approach: The very naive method is to create an output array of size 'n * k' and then
      copy all the elements into the output array followed by sorting.
    Algorithm:
    1) Create a output array of size n * k.
    2)Traverse the matrix from start to end and insert all the elements in output array.
    3) Sort and return the output array.

    * Time Complexity  :  O(n*k) + O(n*k * log(n*k))     Addition of elements + Sorting
        since resulting array is of N*k size.
    * Space Complexity : O(n*k), The output array is of size n*k.
                       : O(1),   If we ignore output array size
     */
    public static ArrayList<Integer> mergeKSortedArrays_BruteForce(ArrayList<ArrayList<Integer>> kArrays, int k) {
        ArrayList<Integer> sortedList = new ArrayList<>();

        for (ArrayList<Integer> list : kArrays)
            for (int value : list)
                sortedList.add(value);

        sortedList.sort((a, b) -> (a - b));
        return sortedList;
    }


    /*
    ************************************ Min Heap Solution ****************************************
    * Another Approach is to use a MinHeap to get sorted numbers (for storing elements) &
      later add it to an array-list
    * Time Complexity  :  O(n*k * log(n*k)) + O(n*k * log(n*k)) =  O(n*k * log(n*k))
        This is due to Addition of elements & deletion from MinHeap
        where 'n' is the average number of elements present in 'ith' list
        Since, MinHeap and output array is of N*k size. Addition & Deletion from MinHeap will take log(n*k) time.
        Though the time taken will be less than previous approach, as elements are not added into
        MinHeap of size 'n*k' initially. Heap size of 'n*k' is reached at the end.
        This is the worst case Time Complexity
    * Space Complexity : O(n*k), The MinHeap is of size n*k.
     */
    public static ArrayList<Integer> mergeKSortedArrays_MinHeap(ArrayList<ArrayList<Integer>> kArrays, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (ArrayList<Integer> list : kArrays)
            for (int value : list)
                minHeap.add(value);

        ArrayList<Integer> sortedList = new ArrayList<>();

        while (minHeap.size() > 0)
            sortedList.add(minHeap.remove());
        return sortedList;
    }


    /*
    ************************************** Better Approach ***************************************
    * In previous Approach we were not taking advantage of the sorted List/Arrays.
    * Another approach is to use "Merge Operation in Merge Sort". We merge two arrays/lists at a time.

    * Time Complexity:  O(k * n * k)
      Since merging of two sorted arrays can be done in O(a + b) ~ O(n) time, assuming 'n'
      is the average no. of elements in each list.
      To merge 1st & 2nd sorted list we need:  O(n)  + O(n) = O(2n)  Time.
      To merge 2nd & 3rd sorted list we need:  O(2n) + O(n) = O(3n)  Time.
      To merge 3rd & 4th sorted list we need:  O(3n) + O(n) = O(4n)  Time.
      To merge 4th & 5th sorted list we need:  O(4n) + O(n) = O(5n)  Time.
      To merge (k-2)th & (k-1)th sorted list we need:  O((k-2)*n) + O(n) = O(n * (k-1))  Time.
      To merge (k-1)th & k-th sorted list we need:  O((k-1)*n) + O(n) = O(n * k)  Time.
      * Since we are doing it inside a 'for loop' of k iterations, we require O(k * n * k) Time Complexity

    * Space Complexity:  O(n * k)
      We are also using An Extra Space of O(k * n) to store all the sorted list (excluding output)
      Size of each array/list  -> 'n'
      Size of all 'k' array/list  -> 'n * k'
     */
    public static ArrayList<Integer> mergeKSortedArrays_MergeSort(ArrayList<ArrayList<Integer>> kArrays, int k){
        // Output ArrayList
        ArrayList<Integer> mergedKSortedList = new ArrayList<>();

        // External arrayList to store the Sorted list
        ArrayList<Integer> arr1 = new ArrayList<>(kArrays.get(0));

        for (int ind = 1; ind < k; ind++){
            ArrayList<Integer> arr2 = kArrays.get(ind);

            // Same as Merge Operation in Merge Sort
            int i = 0;
            int j = 0;
            while (i < arr1.size() || j < arr2.size()){
                int valA = i < arr1.size() ? arr1.get(i) : Integer.MAX_VALUE;
                int valB = j < arr2.size() ? arr2.get(j) : Integer.MAX_VALUE;

                if (valA <= valB) {
                    mergedKSortedList.add(valA);
                    i++;
                }
                else {
                    mergedKSortedList.add(valB);
                    j++;
                }
            }
            // We need to swap 'mergedKSortedList' & 'arr1', so that 'arr1' becomes ready for
            // next iteration (arr1 should contain previously merged sorted values)
            arr1.clear();
            ArrayList<Integer> temp = arr1;
            arr1 = mergedKSortedList;
            mergedKSortedList = temp;
        }

        mergedKSortedList = arr1;
        return mergedKSortedList;
    }

}