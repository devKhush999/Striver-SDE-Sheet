package BinarySearch.MedianOfTwoSortedArray;

// https://youtu.be/NTop3VTjmxk
// https://takeuforward.org/data-structure/median-of-two-sorted-arrays-of-different-sizes/
// https://www.geeksforgeeks.org/median-of-two-sorted-arrays-of-different-sizes/

/*
Intuition:
 * We know that we will get answers only from the final merged sorted arrays.
   We figured it out with the naive approach in previous approach.
 * We will partition both the arrays into two halves, such that the left half of the partition
   will contain elements, which will be there when we merge them till the median element (left half
   will contain the 'median' element) and rest in the other right half.
   This partitioning of both arrays will be done by binary search.
*/

public class MedianOfTwoSortedArray_BinarySearch {
    /*
    ***************************** Binary Search Solution ****************************************
    * Time Complexity : O(log(min(m, n)))
      Reason – We are applying binary search on the array which has a minimum size.
    * Space Complexity: O(1)
      Reason – No extra array is used.
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        if (A.length > B.length){
            int[] temp = A;
            A = B;
            B = temp;
        }

        int m = A.length, n = B.length;

        // We divide the sorted combines array into two halves such that left-half always contains
        // median (one median in case of even length)
        // We add "+1", so that in case of odd elements, valid left half (given by break-point) will
        // always contain the 'median' element. In case of even elements, it doesn't have any affect.
        int halfElementsHavingMedian = (m + n + 1)/2;

        // Set "low" to "0" as we can pick minimum of 0 elements from array 'A'
        // Set "high" to "A.length" as we can pick at max. all the elements from array 'A'
        int low = 0, high = A.length;

        while (low <= high){
            // Cut point for Smaller Array 'A'
            int cutA = low + (high - low)/2;
            // Cut point for Larger Array 'B'
            int cutB = halfElementsHavingMedian - cutA;

            // Boundary Conditions
            int ALeft = cutA == 0 ? Integer.MIN_VALUE : A[cutA - 1];
            int ARight = cutA == m ? Integer.MAX_VALUE : A[cutA];
            int BLeft = cutB == 0 ? Integer.MIN_VALUE : B[cutB - 1];
            int BRight = cutB == n ? Integer.MAX_VALUE : B[cutB];

            // Checking if the partitioning is valid. This is the break point for finding the two
            // completely sorted halves (these two halves will be same as if we were just merging two
            // sorted arrays)
            if (ALeft <= BRight  &&  BLeft <= ARight){
                if ((m + n) % 2 == 1)      // Case for Odd elements, only one median in left-half
                    return Math.max(ALeft, BLeft);
                else                       // Case for Even elements, two medians in both left & right half
                    return (Math.max(ALeft, BLeft) + Math.min(ARight, BRight)) / 2.0;
            }
            // If partitioning is not valid, move ranges of binary search
            // When ALeft > BRight, move left and perform the above operations again.
            else if (ALeft > BRight)
                high = cutA - 1;
            // When BLeft > ARight, move right and perform the above operations again.
            else if (BLeft > ARight)
                low = cutA + 1;
        }
        return 0.0;
    }
}
