package BinarySearch.SingleElementInSortedArray;
import java.util.HashMap;

// https://www.youtube.com/watch?v=nMGL2vlyJk0
// https://youtu.be/PzszoiY5XMQ
// https://takeuforward.org/data-structure/search-single-element-in-a-sorted-array/
// MUST READ: https://www.geeksforgeeks.org/find-the-element-that-appears-once-in-a-sorted-array/

public class SingleElementInSortedArray_BruteForce {
    /*
    ******************************************* Brute Force *******************************************
    * Time Complexity: O(n) + O(n) = O(n)
    * Reason: We doing One Traversal for adding count of all elements & another traversal to find
      single element in the array using HashMap

    * Space Complexity: O(n/2) = O(n)
    * Reason: Size of HashMap will be half of array's size as there are duplicate elements
     */
    public int singleNonDuplicate_BruteForce(int[] arr) {
        HashMap<Integer,Integer>  count = new HashMap<>();
        for (int val : arr)
            count.put(val, count.getOrDefault(val, 0) + 1);

        for (int valueInArray : count.keySet())
            if (count.get(valueInArray) == 1)
                return valueInArray;
        return -1;
    }


    /*
    ********************************** Solution using Traversal ****************************
    * Simple Solution is to traverse the array from left to right.
    * Since the array is sorted, we can easily figure out the required element.
    * Time Complexity: O(n)
    * Auxiliary Space: O(1)
     */
    public int singleNonDuplicate_ForLoop(int[] arr) {
        int n = arr.length;

        // Traverse the array by steps of '2', & keep on checking whether arr[i} & arr[i+1] are
        // equal or not
        for (int i = 0; i < n - 1; i += 2)
            if (arr[i] != arr[i + 1])
                return arr[i];
        // If we don't find the single element, then last element in the array will be single (think)
        return arr[n-1];
    }


    /*
    ************************************* XOR: Bit Manipulation *************************************
    * Approach: As every number in the array repeats twice and only one number occurs once,
              we can take advantage of the XOR(^) operator. These are two properties of
              the XOR operator which will be helpful.
    1) x ^ 0 = x    2) x ^ x = 0
    *  XOR of Duplicate numbers will be 0

    * Time Complexity: O(N)
    * Space Complexity: O(1)
     */
    public int singleNonDuplicate_XOROperations(int[] arr) {
        int xor = 0;
        for (int val : arr)
            xor ^= val;
        return xor;
    }
}
