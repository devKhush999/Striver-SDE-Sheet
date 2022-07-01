package Heaps.TopKFrequentElements;
import java.util.HashMap;
import java.util.PriorityQueue;

// PRE_REQUISITE: "Kth Largest Element using Min-Heap"
// https://youtu.be/Wh3A29psE_Y
// https://youtu.be/7VoJn544QrM

/** *********************************** INTUITION ***********************************
 * How we will get the k most frequent element?
 * Think Brute Force
 * First we should know the frequency of each element in the array, a HashMap can be used for this.
 * Then, we should "arrange the frequencies of the elements" in the HashMap in 'decreasing order'.
    A MinHeap too can be used for this. (We can maintain an object having "array's value" and
    "its frequency in the array")
 * Finally, we will select the first 'k' elements with maximum frequency from that MinHeap
 * We are done
 */

public class TopKFrequentElements_HashMap_MinHeap {
    /* ******************************** APPROACH 1 ***********************************************
     * Complexity Analysis:
       * Time Complexity: O(n) + O(d * log(k)) + O(k * log(k)) =  O(n * log(k))
         where 'n' is the total number of elements
         where 'd' is the count of distinct elements in the array.
         NOTE: 'd' can be 'n' in worst case (when all elements are unique)
         Addition & removal of elements from MinHeap takes log(k) time
         Size of HashMap as well as Min-Heap will be 'k' at most
         * O(n) for adding frequencies in HashMap
         * O(d * log(k)) for adding "values" along with their "frequency" inside min-Heap
         * O(k * log(k)) for removing first 'k' Objects (with values & frequencies) from MinHeap.
         * In worst case (n = d), building hash map takes O(n) and building heap will
           take O(n * log(k)) time as adding an element to heap takes log(k) time.
           Hence, the total time complexity is O(n * log(k)).

       * Space Complexity: O(d) + O(k) + O(d) = O(n)
         The Hash map can have at most 'n' elements.
         Min-heap can have at most 'k' elements
         Hence, the total space complexity is O(n)
         where 'd' is the count of distinct elements in the array.
         * To store the elements in HashMap O(d) space complexity is needed.
         * We Store pairs as Objects of {values, frequencies}, this takes O(d) space.
         * To store the elements in MinHeap O(k) space complexity is needed.
    */
    public int[] topKFrequent_HashMap_MinHeap_1(int[] arr, int k) {
        // Put count of all the elements in Map with element as the key & count as the value
        HashMap<Integer, Integer> frequencies = new HashMap<>();
        for (int value : arr)
            frequencies.put(value, frequencies.getOrDefault(value, 0) + 1);

        // Create a Min-Heap from Elements of HashMap of pairs as {Value, Frequency}
        // Min-Heap will be ordered by the frequencies of elements
        PriorityQueue<Element> minHeap = new PriorityQueue<>();

        // If the numbers of elements in Min-Heap is less than 'k' then simply add it.
        // Else, if the "Current's elements frequency" is greater than "frequency of Element" at
        // the peek of MinHeap, then remove the top element of MinHeap & add the Current Element with
        // its frequency.
        int i = 0;
        for (int value : frequencies.keySet()){
            if (i < k) {
                minHeap.add(new Element(value, frequencies.get(value)));
                i++;
            }
            else if (i >= k  &&  frequencies.get(value) > minHeap.peek().frequency){
                minHeap.remove();
                minHeap.add(new Element(value, frequencies.get(value)));
            }
        }

        // To store the 'k' frequent element
        i = 0;
        int[] kFrequentElements = new int[k];

        // Retrieve all the 'k' elements values in MinHeap with the highest frequencies from the MinHeap.
        // MinHeap will always have the 'k' element (objects) with the highest frequencies in
        // increasing order (bcoz it is Min-Heap)
        while (!minHeap.isEmpty())
            kFrequentElements[i++] = minHeap.remove().value;

        // return the 'k' frequent elements
        return kFrequentElements;
    }

    static class Element implements Comparable<Element>{
        int value, frequency;
        public Element(int value, int frequency) {
            this.value = value;
            this.frequency = frequency;
        }
        @Override
        public int compareTo(Element other){
            return this.frequency - other.frequency;
        }
    }


    /* ******************************** APPROACH 2 ***********************************************
        * Complexity Analysis:
            * Time Complexity: O(n) + O(d * log(k)) + O(k * log(k)) =  O(n * log(k))
              where 'n' is the total number of elements
              where 'd' is the count of distinct elements in the array.
              NOTE: 'd' can be 'n' in worst case (when all elements are unique)
              Addition & removal of elements from Min-Heap takes log(k) time
              Size of HashMap as well as Min-Heap will be 'k' at most
              * O(n) for adding frequencies in HashMap
              * O(d * log(k)) for adding "values" inside min-Heap
              * O(k * log(k)) for removing all the 'k' values with maximum frequencies from MinHeap.

            * Space Complexity: O(d) + O(k) = O(n)
              where 'd' is the count of distinct elements in the array.
              * To store the elements in HashMap O(d) space complexity is needed.
              * To store the elements in MinHeap O(k) space complexity is needed.
    */
    public int[] topKFrequent_HashMap_MinHeap_2(int[] arr, int k) {
        // Put count of all the elements in Map with element as the key & count as the value
        HashMap<Integer, Integer> frequency = new HashMap<>();
        for (int value : arr)
            frequency.put(value, frequency.getOrDefault(value, 0) + 1);

        // Create a Min-Heap from Elements of HashMap with elements {Array's Value}
        // Min-Heap will be ordered by the frequencies of Elements (in array) determined by HashMap
        // Carefully see the Comparator/Lambda-Function passed into the Min-Heap
        // Using this we don't even need to create separates objects for Storing Array's elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((key1, key2) -> (frequency.get(key1) - frequency.get(key2)));

        // If the numbers of elements in Min-Heap is less than 'k' then simply add it.
        // Else, if the "Current's elements frequency" is greater than "frequency of Element" at
        // the peek of MinHeap, then remove the top element of MinHeap & add the Current Element with
        // its frequency.
        int i = 0;
        for (int value : frequency.keySet()){
            if (i < k) {
                minHeap.add(value);
                i++;
            }
            else if (i >= k  && frequency.get(value) > frequency.get(minHeap.peek())){
                minHeap.remove();
                minHeap.add(value);
            }
        }

        // Retrieve all the 'k' elements values in MinHeap with the highest frequencies from the MinHeap.
        // MinHeap will always have the 'k' element (objects) with the highest frequencies in
        // increasing order (bcoz it is Min-Heap)
        i = 0;
        int[] kFrequentElements = new int[k];
        while (!minHeap.isEmpty())
            kFrequentElements[i++] = minHeap.remove();

        return kFrequentElements;
    }
}
