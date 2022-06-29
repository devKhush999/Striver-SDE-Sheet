package Heaps.KMaxSumCombinations;
import java.util.*;

// https://www.codingninjas.com/codestudio/problems/k-max-sum-combinations_975322?topList=striver-sde-sheet-problems&utm_source=striver&utm_medium=website&leftPanelTab=1
// https://www.youtube.com/watch?v=btjG9eLNYcg
// https://www.geeksforgeeks.org/k-maximum-sum-combinations-two-arrays/

// SOME READINGS FOR HASHING:
// https://www.geeksforgeeks.org/equals-hashcode-methods-java/
// https://www.geeksforgeeks.org/override-equalsobject-hashcode-method/?ref=lbp
// https://stackoverflow.com/questions/11597386/objects-hash-vs-objects-hashcode-clarification-needed

public class KMaxSumCombinations_EfficientSolution {
    /* ********************************** EFFICIENT SOLUTION ***************************************
    * Time Complexity: 2*O(n * log(n)) + O(k * log(k))  =  O(n * log(n))
      where 'n' is the number of elements the given arrays.
      We are sorting two arrays (takes 2*O(n * log(n)) time) and iterating until 'k' reaches '0'.
      In each iteration, we add & remove from the MaxHeap. Size of max-heap will be of O(k) nearly.
      So, addition to & removal from Max-heap will take O(log(k)) time.

    * Space Complexity: O(k) + O(k) + O(k) = O(k) = O(n)        assuming K <= N
      Reason: One O(k) for size of Max-Heap, it will be of O(k) nearly.
      Another O(k) for HashSet used
      Another O(k) for Objects being made of various classes.
    */
    public ArrayList<Integer> kMaxSumCombination(ArrayList<Integer> listA, ArrayList<Integer> listB, int k){
        int n = listA.size();
        // sort both arrays A and B
        Collections.sort(listA);
        Collections.sort(listB);

        // ArrayList to store the 'k' maximum sum combinations
        ArrayList<Integer> kMaxSumCombinations = new ArrayList<>();

        // Max heap which contains stores various "sum of pairs" in the format (sum, (i, j)) in
        // the decreasing order of 'pairSum', where i and j are the indices of the elements
        // from array A and array B which make up the sum.
        PriorityQueue<PairSum> sumCombinationMaxHeap = new PriorityQueue<>(new PairSumComparator());
        // This can also be done, as PairSum implements 'Comparable' interface
        // PriorityQueue<PairSum> sumCombinationMaxHeap = new PriorityQueue<>();

        // Initialize the 'sumCombinationMaxHeap' with the maximum sum combination, i.e.
        // (A[N - 1] + B[N - 1]), and also add the pair of indices (N - 1, N - 1) along
        // with sum.
        IndexPair maxSumIndexPair = new IndexPair(n-1, n-1);
        PairSum maximumSum = new PairSum(listA.get(n - 1) + listB.get(n - 1), maxSumIndexPair);
        sumCombinationMaxHeap.add(maximumSum);

        // Set is used to store the indices of the IndexPair(i, j). We use set to make sure
        // the indices does not repeat inside max heap.
        HashSet<IndexPair> indexPairSet = new HashSet<>();

        // iterate upto K
        while (k > 0){

            // Poll the maximum Sum combination present from the MaxHeap the format (sum, (i,j))
            PairSum sumCombination = sumCombinationMaxHeap.remove();
            kMaxSumCombinations.add(sumCombination.pairSum);
            int i = sumCombination.indices.i;
            int j = sumCombination.indices.j;

            // Check if the indices (i-1, j) are present in the set.
            // If not, then the next Pair of (i-1,j) can be one of the possible Sum Combinations
            // It pair (i-1,j) is present, skip that pair. Otherwise, we will get duplicates.
            if (!indexPairSet.contains(new IndexPair(i - 1, j))  &&  i > 0){
                IndexPair nextIndexPair = new IndexPair(i - 1, j);
                indexPairSet.add(nextIndexPair);
                sumCombinationMaxHeap.add(new PairSum(listA.get(i - 1) + listB.get(j), nextIndexPair));
            }
            // Check if the indices (i, j-1) are present in the set.
            // If not, then the next Pair of (i,j-1) can be one of the possible Sum Combinations
            // It pair (i,j-1) is present, skip that pair. Otherwise, we will get duplicates.
            if (!indexPairSet.contains(new IndexPair(i, j - 1))  && j > 0){
                IndexPair nextIndexPair = new IndexPair(i, j - 1);
                indexPairSet.add(nextIndexPair);
                sumCombinationMaxHeap.add(new PairSum(listA.get(i) + listB.get(j - 1), nextIndexPair));
            }
            k--;
        }
        return kMaxSumCombinations;
    }


    class IndexPair {
        int i, j;
        public IndexPair(int i, int j){
            this.i = i;
            this.j = j;
        }

        // NOTE: Overriding equals() & hashCode() is a must for Hashing Operations with "Key as Objects"
        @Override
        public boolean equals(Object otherPair){
            if (otherPair == null || this.getClass() != otherPair.getClass())
                return false;
            IndexPair indexPair = (IndexPair) otherPair;
            return this.i == indexPair.i  && this.j == indexPair.j;
        }

        @Override
        public int hashCode(){
            return Objects.hash(i, j);
        }
    }

    // Comparators/Comparable are always necessary for greater/minimum operations with objects
    class PairSum implements Comparable<PairSum>{
        int pairSum;
        IndexPair indices;
        public PairSum(int sum, IndexPair indices){
            this.pairSum = sum;
            this.indices = indices;
        }

        @Override
        public int compareTo(PairSum pair){
            return pair.pairSum - this.pairSum;
        }
    }

    class PairSumComparator implements Comparator<PairSum>{
        @Override
        public int compare(PairSum a, PairSum b){
            return b.pairSum - a.pairSum;
        }
    }
}

/*
******************************************** READING ************************************************
Using Max-Heap

* In this approach, instead of exploring all the possible sum combinations,
  we will only use the sum-combinations that can be the possible candidates for the ‘K’ max
  sum combinations.
* The idea is to sort both arrays and insert the possible candidates of max sum combinations
  into the max-heap. We will also use a set to make sure that we are using a sum-combination only once. Now, we will keep removing the max element(max sum combination) and keep inserting the next possible candidate in the max heap until we get ‘K’ max sum combinations. We will also store the indices corresponding to the value so that we will be able to get the next possible candidate pair.

The Steps are as follows :

* Sort both the arrays/lists, array/list ‘A’ and array/list ‘B’.
* Create a max-heap to store the sum combinations along with the indices of the elements from
  both arrays/lists ‘A’ and ‘B’ which make up the sum. The max heap is ordered by the sum,
  the max sum will be the root of the heap.
* Initialize the heap with the maximum possible sum combination, i.e
  ('A[N – 1]' + ‘B[N – 1]’ where ‘N’ is the size of array) and with the indices of elements from
  both the arrays ('N' – 1, 'N' – 1) because this will be maximum sum possible pair sum.
  The tuple inside the max heap will be ('A[N-1]' + 'B[N – 1]', ('N' – 1, ‘N’ – 1)).
* The max heap is ordered by the first value i.e, sum of both elements/pairs.
* Create an array/list ‘result’ of size ‘K’ that will store the ‘K’ max sum combinations.

* We will keep processing the below steps until we do not get ‘K’ sum combinations.
    * Remove the root of the max-heap to get the current largest sum combination and
      along with the indices of the element that make up the sum. Let the tuple be (sum, ('i', ‘j’)).
    * Add the ‘sum’ to the output array ‘RESULT’.
    * If the indices ('i' – 1, ‘j’) and ('i', ‘j’ – 1) are valid and not present in the set,
      insert ('A[i – 1]' + ‘B[j]’, ('i' – 1, ‘j’)) and ('A[i]' + ‘B[j – 1]’,('i', ‘j’ – 1)) into
      the max heap. This will make sure that the sum combinations are not redundant.
    * Insert the indices ('i' – 1, ‘j’) and ('i', ‘j’ – 1) into the set.

* Finally, return the output array ‘RESULT’.

** Time Complexity:     O(N * log(N)),   Where ‘N’ is the number of elements in the array.
     Since we are sorting the given arrays/lists which takes O(N * log(N)) time.
     Then, we are using a max-heap which, in the worst case, can contain ‘N’ elements. (bcoz K <= N)
     The operations on heap will require O(N * log(N)) time.
     The look-up in the set can be done in log(1) time in average case (Recall HashSet).
     Thus, the time complexity will be O(N * log(N)).

** Space Complexity:    O(N), Where ‘N’ is the number of elements in the given arrays/lists.
     Since we are using a max-heap and a set.
     In the worst case, the total additional space will be O(N) (bcoz K <= N)
     Thus, the space complexity will be O(N).
 */
