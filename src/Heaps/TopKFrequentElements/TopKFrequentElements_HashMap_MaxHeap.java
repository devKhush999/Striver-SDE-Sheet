package Heaps.TopKFrequentElements;
import java.util.HashMap;
import java.util.PriorityQueue;

// https://www.geeksforgeeks.org/find-k-numbers-occurrences-given-array/
// https://youtu.be/Wh3A29psE_Y
// https://youtu.be/7VoJn544QrM

/** *********************************** INTUITION ***********************************
 * How we will get the k most frequent element?
 * Think Brute Force
 * First we should know the frequency of each element in the array, a HashMap can be used for this.
 * Then, we should "arrange the frequencies of the elements" in the HashMap in 'decreasing order'.
   A MaxHeap can be used for this. (We can maintain an object having "array's value" and "its frequency
   in the array")
 * Finally, we will select the first 'k' elements with maximum frequency from that MaxHeap
 * We are done
 */

public class TopKFrequentElements_HashMap_MaxHeap {
    /* ******************************** APPROACH 1 ***********************************************
    * Complexity Analysis:
        * Time Complexity: O(n) + O(d * log(d)) + O(k * log(d)) =  O(n * log(n))
          where 'n' is the total number of elements
          where 'd' is the count of distinct elements in the array.
          NOTE: 'd' can be 'n' in worst case (when all elements are unique)
          Addition & removal of elements from MaxHeap takes log(d) time
          Size of HashMap as well as Max-Heap will be 'd'
          * O(n) for adding frequencies in HashMap
          * O(d * log(d)) for adding "values" along with their "frequency" inside max-Heap
          * O(k * log(d)) for removing first 'k' Objects (with values & frequencies) from MaxHeap.
          * In worst case (n = d), building hash map takes O(n) and building heap will
            take O(n * log(n)) time as adding an element to heap takes log(n) time.
            Hence, the total time complexity is O(N * log(N)).

        * Space Complexity: O(d) + O(d) + O(d) = O(n)
          The Hash map and the heap can have at most 'n' elements. Hence, the total space complexity is O(n)
          where 'd' is the count of distinct elements in the array.
          * To store the elements in HashMap O(d) space complexity is needed.
          * We Store pairs as Objects of {values, frequencies}, this takes O(d) space.
          * To store the elements in MaxHeap O(d) space complexity is needed.
     */
    public int[] topKFrequent_HashMap_MaxHeap_1(int[] arr, int k) {
        // Put count of all the elements in Map with element as the key & count as the value
        HashMap<Integer, Integer> frequencies = new HashMap<>();
        for (int num : arr)
            frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);

        // Create a Max-Heap from Elements of HashMap of pairs as {Value, Frequency}
        // Max-Heap will be ordered by the frequencies of elements
        PriorityQueue<Element> maxHeap = new PriorityQueue<>();

        for (int value : frequencies.keySet())
            maxHeap.add(new Element(value, frequencies.get(value)));

        // To store the 'k' frequent element
        int[] kFrequentElement = new int[k];

        // Retrieve the first 'k' elements values with the highest frequencies from the Max-Heap.
        for (int i = 0; i < k; i++)
            kFrequentElement[i] = maxHeap.remove().value;

        // return the 'k' frequent elements
        return kFrequentElement;
    }

    // Element Class to store the array's values along with their frequencies
    static class Element implements Comparable<Element>{
        int value, frequency;
        public Element(int value, int frequency){
            this.value = value;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Element other){
            return other.frequency - this.frequency;
        }
    }



    /* ******************************** APPROACH 2 ***********************************************
        * Complexity Analysis:
            * Time Complexity: O(n) + O(d * log(d)) + O(k * log(d)) =  O(n * log(n))
              where 'n' is the total number of elements
              where 'd' is the count of distinct elements in the array.
              NOTE: 'd' can be 'n' in worst case (when all elements are unique)
              Addition & removal of elements from MaxHeap takes log(d) time
              Size of HashMap as well as Max-Heap will be 'd'
              * O(n) for adding frequencies in HashMap
              * O(d * log(d)) for adding "values" inside max-Heap
              * O(k * log(d)) for removing first 'k' values with maximum frequencies from MaxHeap.

            * Space Complexity: O(d) + O(d) = O(n)
              where 'd' is the count of distinct elements in the array.
              * To store the elements in HashMap O(d) space complexity is needed.
              * To store the elements in MaxHeap O(d) space complexity is needed.
    */
    public int[] topKFrequent_HashMap_MaxHeap_2(int[] arr, int k) {
        // Put count of all the elements in Map with element as the key & count as the value
        HashMap<Integer, Integer> frequencies = new HashMap<>();
        for (int num : arr)
            frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);

        // Create a Max-Heap from Elements of HashMap with elements {Array's Value}
        // Max-Heap will be ordered by the frequencies of Elements (in array) determined by HashMap
        // Carefully see the Comparator/Lambda-Function passed into the Max-Heap
        // Using this we don't even need to create separates objects for Storing Array's elements
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((key1, key2) -> (frequencies.get(key2) - frequencies.get(key1)));

        for (int key : frequencies.keySet())
            maxHeap.add(key);

        // To store the 'k' frequent element
        int[] kFrequentElement = new int[k];
        // Retrieve the first 'k' elements values with the highest frequencies from the Max-Heap
        for (int i = 0; i < k; i++)
            kFrequentElement[i] = maxHeap.remove();

        return kFrequentElement;
    }
}
