package BinarySearch.KthElementOfTwoSortedArrays;

// https://youtu.be/nv7F4PiLUzo
// https://takeuforward.org/data-structure/k-th-element-of-two-sorted-arrays/
// https://www.geeksforgeeks.org/k-th-element-two-sorted-arrays/

public class KthElementOfTwoSortedArrays_BruteForce {
    /*
    ******************************** Brute Force Solution 1 ************************************
    * Time Complexity: O(m + n)
    * Space Complexity: O(m + n)
    */
    public long kthElement_BruteForce1(int[] a, int[] b, int k){
        int m = a.length, n = b.length;
        int[] arr = new int[m + n];
        int i = 0,  j = 0, index = 0;

        // same as merging two sorted arrays
        while (i < m || j < n){
            int valA = i < m ? a[i] : Integer.MAX_VALUE;
            int valB = j < n ? b[j] : Integer.MAX_VALUE;

            if (valA <= valB)
                arr[index++] = a[i++];
            else if (valA > valB)
                arr[index++] = b[j++];
        }
        return arr[k - 1];  // arr[k-1] because 1-based indexing is given
    }


    /*
     ******************************** Brute Force Solution 2 ************************************
     * Time Complexity: O(k)         bcoz we return from the while loop when 'index == k'
     * Space Complexity: O(1)
     */
    public long kthElement_BruteForce2(int[] a, int[] b, int k){
        int m = a.length, n = b.length;
        int i = 0,  j = 0, k_index = 0;

        // Same as merging two sorted arrays, but we actually don't merge into a new array
        // We just move indices according to the sorted array, as if merged array was present
        // If the index in merged array becomes equal to 'k', then we returned the element at 'k-1'
        // index in the merged sorted array
        while (i < m || j < n){
            int valA = i < m ? a[i] : Integer.MAX_VALUE;
            int valB = j < n ? b[j] : Integer.MAX_VALUE;

            if (valA <= valB){
                i++;
                k_index++;
                if (k_index == k)       // return from the while loop when 'index == k'
                    return a[i - 1];
            }
            else if (valA > valB){
                j++;
                k_index++;
                if (k_index == k)       // return from the while loop when 'index == k'
                    return b[j - 1];
            }
        }
        return -1;
    }


    /*
     ******************************** Brute Force Solution 3 ************************************
     * Time Complexity: O(k)             We iterate at total k times. This makes time complexity to O(k)
     * Space Complexity: O(1)            We do not use any extra data structure
     *
     * Approach:
        * Iterate till only the first 'k' elements in the merge sorted array
        * Save all the values in the array one by one
        * Once the index/counter reaches 'k', we return 'k-1' in merge sorted array
     */
    public long kthElement_BruteForce3(int[] a, int[] b, int k){
        int m = a.length, n = b.length;
        int i = 0,  j = 0;
        int counter = 0;
        int kthElement = -1;

        // It is guaranteed that 'k' will lie in the range [1, m+n] both inclusive
        // Iterate till only the first 'k' elements as per the merged sorted array
        while (counter < k){
            int valA = i < m ? a[i] : Integer.MAX_VALUE;
            int valB = j < n ? b[j] : Integer.MAX_VALUE;

            if (valA <= valB)
                kthElement = a[i++];        // Save all the values in the array one by one
            else if (valA > valB)
                kthElement = b[j++];        // Save all the values in the array one by one
            counter++;
        }
        // Once the counter reaches 'k', we return 'k-1' in merge sorted array saved earlier
        return kthElement;
    }
}
