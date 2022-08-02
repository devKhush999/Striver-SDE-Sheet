package Trie.MaximumXOROfTwoNumbersInArrays;

// https://youtu.be/EIhAwfHubE8
// https://takeuforward.org/data-structure/maximum-xor-of-two-numbers-in-an-array/

public class MaximumXOROfTwoNumbersInArrays {
    /********************************* Simple Brute Force *****************************************
     * Naive Approach: A Simple Solution is to generate all pairs of the given array and compute
        the XOR of the every pair. Finally, return the maximum XOR value.
     * Time Complexity: O(n^2) = O(n * n)
     * Space Complexity: O(1)
     */
    public int findMaximumXOR_BruteForce(int[] arr) {
        int XOR = 0;

        for (int a : arr)
            for (int b : arr)
                XOR = Math.max(XOR, a ^ b);

        return XOR;
    }


    /********************************** Efficient Trie Solution **************************************
     * We use a Trie structure to store the bit representation of the numbers
     * And for each N values (in array), compute the maximum XOR value each can produce by going
       through elements in the Trie.

     * Intuition: After inserting all the numbers from array into the Trie, when the call for
        getMaximumXORWithNumber() with an element (from array) is made.
        * Consider 32 Bit representation of numbers
        * To maximize the XOR value, we need to set the Bits starting from the leftmost positions to 1
            (starting from index 31 down to 1)
        * To find the Maximum XOR of that number with all numbers present in the Trie, we look always
            look for numbers (in Trie) that give us opposite bit at any positions
        * We eliminate choices for numbers (in Trie) that can't give us opposite bit for finding
            Maximum XOR (at any position) since they won't maximize the XOR value.
        * If we have no choices for numbers (in Trie) that give us opposite bit at that positions. Then
            we are bound to take such numbers (there is no option)

     * Time Complexity  : O(32 * n) + O(32 * n)  =  O(2 * 32 * n)  =  O(n)
     * Space Complexity : O(n)
        * Actual Space Complexity can't be predicted, but it is of O(n)
     */
    public int findMaximumXOR(int[] arr){
        // Initialize a Trie Data Structure
        Trie trie = new Trie();

        // Insert all the numbers in array into the Trie
        for (int number : arr)
            trie.insert(number);

        // Max value of XOR : Output
        int max_XOR = 0;

        // One by one consider all elements from the array (as 'x') and find the maximum value
        // of XOR with all the numbers present in the Trie (i.e, x ^ arr])
        for (int number : arr){
            int XOR = trie.getMaximumXORWithNumber(number);
            max_XOR = Math.max(max_XOR, XOR);
        }
        return max_XOR;
    }
}
