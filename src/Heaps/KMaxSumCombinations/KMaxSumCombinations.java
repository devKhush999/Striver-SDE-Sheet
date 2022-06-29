package Heaps.KMaxSumCombinations;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class KMaxSumCombinations {
    /*
    ************************************* BRUTE FORCE Solution *************************************
    * We use ArrayList here, we add all n^2 Sum combinations into that ArrayList, then sort it in
      descending order. And return the first 'k' values in that list (as we need only first 'k'
      maximum Sum Combinations)

    * Time Complexity: O(n^2) + O(n^2 * log(n^2)) + O(k)  =  O(n^2 * log(n^2))
      Reason: We add all n^2 Sum combinations into the ArrayList (this takes n^2 time) & sort it
              Sorting takes O(n^2 * log(n^2)), as there are 'n^2' Sum combinations into ArrayList
    * Space Complexity: O(n^2)
      Reason: ArrayList will have n^2 Sum Combinations
     */
    public static ArrayList<Integer> kMaxSumCombination(ArrayList<Integer> listA, ArrayList<Integer> listB, int n, int k){
        ArrayList<Integer> sumCombinationHeap = new ArrayList<>();

        for (int valueA : listA)
            for (int valueB : listB)
                sumCombinationHeap.add(valueA + valueB);

        sumCombinationHeap.sort((a, b) -> (b - a));

        ArrayList<Integer> kMaxSumCombination = new ArrayList<>();
        for (int i = 0; i < k; i++)
            kMaxSumCombination.add(sumCombinationHeap.get(i));

        return kMaxSumCombination;
    }

    /*
    ************************************* MAX-Heap Solution *************************************
    * Idea is to add all the sum combinations (sum of each element in list-A with every element in
    * list-B) in a Max-Heap. And finally return the first 'k' largest sum combinations from MaxHeap.
    *
    * Time Complexity: O(n^2 * log(n^2)) + O(k * log(n^2))   =   O(n^2 * log(n^2))
                Where ‘n’ is the number of elements in the given arrays/lists.
      Reason: Total sum combinations are n^2 (sum of each element in list-A with every element in list-B)
              Size of max heap is n^2. Building a Heap takes O(m * log(m)) where 'm' is size of Max-heap.
              Then we remove first 'k' values of maxHeap to add it to answer array list,
              delete operations take log(m) time, where 'm' is size of Max-heap
    * Space Complexity: O(n^2)
      Reason: Max heap will have n^2 Sum Combinations
     */
    public static ArrayList<Integer> kMaxSumCombination_MaxHeap(ArrayList<Integer> listA, ArrayList<Integer> listB, int n, int k){
        PriorityQueue<Integer> sumCombinationHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                sumCombinationHeap.add(listA.get(i) + listB.get(j));

        ArrayList<Integer> kMaxSumCombination = new ArrayList<>();
        for (int i = 0; i < k; i++)
            kMaxSumCombination.add(sumCombinationHeap.remove());
        return kMaxSumCombination;
    }


    /*
    ************************************* Min-Heap Efficient Solution *************************************
    * Approach is similar to "Kth Largest Element Using Min-Heap"
    * In this solution we maintain the "SIZE OF THE MIN-HEAP TO BE 'K' "
    * Idea is to add only first 'k' the sum combinations in a Min-Heap.
    * After 'k' sum combinations, whenever the current sum combination becomes greater than min.
    * value of Sum combination in Min-Heap, we simply remove that min. sum combination and add the
    * new greater sum combination.
    * At the end, the Min-heap will only contain the largest 'k' sum combinations.
    * Similar to 'Kth Largest Element Using Min-Heap'
    *
    * Time Complexity: O(n^2 * log(k)) + O(k * log(k))   =   O(n^2 * log(k))
                Where ‘n’ is the number of elements in the given arrays/lists.
      Reason: Size of Min heap is 'k'. Addition & Removal from Min-heap will take only log(k) time
              We traverse via for loop to generate every sum combinations, and replace it into
              the Min-Heap if found greater than peek value of Min-heap. This takes O(n^2 * log(k)) time
              Then we remove all 'k' values of minHeap to add it to answer array list,
              Deleting all 'k' elements from Minheap will take O(k * log(k)) time
    * Space Complexity: O(k)
      Reason: Min heap will have only 'k' largest Sum Combinations
     */
    public static ArrayList<Integer> kMaxSumCombination_MinHeap(ArrayList<Integer> listA, ArrayList<Integer> listB, int n, int k){
        PriorityQueue<Integer> sumCombinationHeap = new PriorityQueue<>();

        int heapSize = 0;
        for (int i = 0; i < n; i++){
            int valueA = listA.get(i);
            for (int j = 0; j < n; j++ ){
                int valueB = listB.get(j);

                if (heapSize < k){
                    sumCombinationHeap.add(valueA + valueB);
                    heapSize++;
                }
                else if (heapSize >= k  &&  sumCombinationHeap.peek() < valueA + valueB){
                    sumCombinationHeap.remove();
                    sumCombinationHeap.add(valueA + valueB);
                }
            }
        }

        // add values only at 0th index, as Heap is MinHeap
        ArrayList<Integer> kMaxSumCombination = new ArrayList<>();
        for (int i = 0; i < k; i++)
            kMaxSumCombination.add(0, sumCombinationHeap.remove());
        return kMaxSumCombination;
    }
}
