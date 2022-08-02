package Trie.MaximumXORWithAnElementFromArray;
import java.util.ArrayList;
import java.util.Arrays;

// Also, taught the concept of Offline Queries & Online Queries
// MUST See: https://youtu.be/Q8LhG9Pi5KM

public class MaximumXORWithAnElementFromArray {
    /**************************** Simple Brute Force Approach ***************************************
     * Time Complexity: O(n * q)
        * where 'n' is length of array
        * where 'q' is length of queries
     * Space Complexity: O(1)
        * Now extra space is used (ignore Output)
     */
    public int[] maximizeXor_BruteForce(int[] arr, int[][] queries) {
        int q = queries.length;

        int[] XORs = new int[q];

        for (int i = 0; i < q; i++){
            int x = queries[i][0];
            int m = queries[i][1];
            int XOR = -1;

            for (int num : arr)
                if (num <= m)
                    XOR = Math.max(XOR, num ^ x);

            XORs[i] = XOR;
        }
        return XORs;
    }


    /******************************** Efficient Trie Solution ***********************************
     * Intuition:
        * Main intuition is that for every Query [x, m] that is encountered.
        * Our Trie will contain the elements less than or equal to "m"
            (i.e, arr[i] <= m for every query)
        * To do this we will Arrange our queries in the Offline fashion along with storing query indices
        * After this to find the maximum XOR value for any query, we can use same Trie for previous
            question "Maximum XOR of two numbers from the array", because Trie contains the elements
            less than or equal to "m"
        * Advantage/Help is that the array elements (<= m) used in finding max. XOR for current query
            can be re-used again to find the max. XOR for the next query, since queries are sorted in
            increasing fashion of "m".

     * MUST MUST See for Intuition: https://youtu.be/Q8LhG9Pi5KM

     * Time Complexity:     O(q) + O(q * log(q)) + O(n * log(n)) + O(n * 32) + O(q * 32)
                        ~~  O(q) + 2*O(q * 32) + 2*O(n * 32)
                        ~~  O(q) + O(n)   = O(q + n)
        Not exactly O(q + n) but greater than that, though TC is much faster than O(n * q) solution
        * where 'n' is length of array
        * where 'q' is length of queries

     * Space Complexity:  O(3 * q) + O(n)  = O(q + n)
        * O(3 * q) for the ArrayList to store the queries (Offline query ArrayList)
        * O(n) for the Trie used
     */
    public int[] maximizeXor_Trie(int[] arr, int[][] queries) {
        int n = arr.length;
        int q = queries.length;

        // Prepare Offline Queries ArrayList
        // Offline queries are solving the queries not in the order given to us
        // But Do provide the final answer in terms of order of queries given to us
        // This can be done by storing the indices of Queries
        // This takes O(q) time AND O(3 * q) space
        ArrayList<Integer[]> offlineQueries = new ArrayList<>();
        for (int i = 0; i < q; i++){
            int x = queries[i][0];
            int m = queries[i][1];

            offlineQueries.add(new Integer[]{x, m, i});
        }

        // Sort the Offline Queries in terms of "m" of each query (increasing order)
        // This takes O(q * log(q)) time
        // offlineQueries.sort((a, b) -> a[1] - b[1]);
        offlineQueries.sort((a, b) -> a[1].compareTo(b[1]));

        // Sort the array. This takes O(n * log(n)) time
        Arrays.sort(arr);

        // Initialize the Trie Data Structure
        Trie trie = new Trie();

        // Output array
        int[] XORs = new int[q];
        int index = 0;

        // Process all Queries. This takes O(32 * q) time in total
        // "32 * q" because of finding maximum XOR with the elements in Trie
        for (Integer[] query : offlineQueries){
            int x = query[0];
            int m = query[1];
            int i = query[2];

            // Insert all the array elements into the Trie until the "arr[index] <= m" for each query
            // This takes O(32 * n) time in total
            // "32 * n" because of inserting all 'n' elements into the Trie that are of 32 bit each
            while (index < n  &&  arr[index] <= m)
                trie.insert(arr[index++]);

            // Find the answer using the previous Question "Maximum XOR Of Two Numbers In Arrays"
            // This step takes O(32) time
            int XOR = -1;       // Edge case when no elements have been added into the Trie
            if (index != 0)
                XOR = trie.getMaximumXORWithNumber(x);

            // Store the answer of query in its respective order
            XORs[i] = XOR;
        }
        return XORs;
    }
}
