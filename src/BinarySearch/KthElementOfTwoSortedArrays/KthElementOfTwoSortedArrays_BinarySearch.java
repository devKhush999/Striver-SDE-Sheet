package BinarySearch.KthElementOfTwoSortedArrays;

// https://youtu.be/nv7F4PiLUzo
// https://takeuforward.org/data-structure/k-th-element-of-two-sorted-arrays/
// https://www.geeksforgeeks.org/k-th-element-two-sorted-arrays/
// PRE-REQUISITE: MEDIAN OF TWO SORTED ARRAYS

/*
 ************************************* Intuition *************************************
* We can part it in such a way that our kth element will be at the end of the left half array.
* Let’s make some observations:
    1) The left portion of the array is made of some elements of both array1 and array2.
       So, divide the merge sorted array into two halves, one of indices from [1, k] and other
       from indices [k+1, m+n]  (1-based indexing!!!)
    2) We can see that all elements of the right half of the array are always larger than the left ones.
    3) So, with help of binary search, we will divide arrays into partitions with keeping k elements
       in the left half. We have to keep in mind that l1 <= r2 and l2 <= r1. Why so?
       This ensures that left-half elements are always lesser than right elements.
 */

public class KthElementOfTwoSortedArrays_BinarySearch {

    /*
    ******************************* Binary Search Solution **************************************
    * Time Complexity : log(min(m,n))
        Reason: We are applying binary search in the array with minimum size among the two.
        Thus, the time complexity of this approach is log(min(m,n)), where m,n are the sizes of two arrays.
    * Space Complexity: O(1)
        Reason: Since no extra data structure is used, making space complexity to O(1).
     */
    public long kthElement( int[] A, int[] B, int k) {
        int m = A.length, n = B.length;

        // We can Simply swap the larger array with smaller array or just reverse the way of
        // finding answer, if A.length > B.length (Binary Search on smaller array)
        if (m > n)
            return kthElement(B, A, k);

        // IMPORTANT: Recall that m <= n (Only difference)
        // Case 1: k <= m, "high = k and low = 0" bcoz we really don't need to search in index after
        // 'k' in smaller array A
        // Case 2: n < k >= m, "high = m and low = k-n" bcoz as k > n, we need to select at least "k-n"
        // elements from smaller array A. So, 'low' becomes 'k-n'. See video for more clarification
        // Case 3: m < k < n, "high = m and low = 0"
        int low = Math.max(0, k-n),  high = Math.min(k, m);

        // ************************** Same as Median of Two Sorted Arrays **************************
        // We divide the sorted combines array into two halves such that left-half always contains
        // the kth Element. This Question is infact, the general case of Median of Two Sorted arrays
        while (low <= high){
            // Cut point for Smaller Array 'A'
            int cutA = low + (high - low)/2;
            // Cut point for Larger Array 'B'
            int cutB = k - cutA;

            // Boundary Conditions
            int leftA = cutA == 0 ? Integer.MIN_VALUE : A[cutA - 1];
            int rightA = cutA == m ? Integer.MAX_VALUE : A[cutA];
            int leftB = cutB == 0 ? Integer.MIN_VALUE : B[cutB - 1];
            int rightB = cutB == n ? Integer.MAX_VALUE : B[cutB];

            // Checking if the partitioning is valid. This is the break point for finding the two
            // completely sorted halves (these two halves will be same as if we were just merging two
            // sorted arrays)
            if (leftA <= rightB  &&  leftB <= rightA)
                return Math.max(leftA, leftB);      // kth element is maximum of element in left-half

            // If partitioning is not valid, move ranges of binary search
            // When ALeft > BRight, move left and perform the above operations again.
            if (leftA > rightB)
                high = cutA - 1;
            // When BLeft > ARight, move right and perform the above operations again.
            else if (leftB > rightA)
                low = cutA + 1;
        }
        return -1;
    }
}
