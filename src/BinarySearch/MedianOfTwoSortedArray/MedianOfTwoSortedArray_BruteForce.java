package BinarySearch.MedianOfTwoSortedArray;

public class MedianOfTwoSortedArray_BruteForce {
    /*
    **************************** Simple Brute Force Solution *******************************
    * Copy Both the arrays into a Single sorted array
    * Then find the median
    * Time Complexity: O(m + n)             where m & n are size of both the arrays
      Reason: We are copying both the arrays into the single sorted array
    * Space Complexity: O(m + n)
      Reason: We are using temporary array of size 'm + n'
     */
    public double findMedianSortedArrays_BruteForce1(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] arr = new int[m+n];

        int i=0, j=0, k=0;
        while(i<m && j<n){
            if (nums1[i] <= nums2[j])
                arr[k++] = nums1[i++];
            else
                arr[k++] = nums2[j++];
        }
        while (i<m)
            arr[k++] = nums1[i++];
        while (j<n)
            arr[k++] = nums2[j++];

        if ((m+n)%2 != 0)
            return arr[(m+n)/2];
        else
            return (arr[(m+n)/2] + arr[((m+n)/2) - 1]) / 2.0;
    }

    /*
     *************************** Another Compact Version of the same Solution ***************************
     * Time Complexity: O(m + n)
     * Space Complexity: O(m + n)
     */
    public double findMedianSortedArrays_BruteForce2(int[] A, int[] B) {
        int lenA = A.length, lenB = B.length;
        int[] arr = new int[lenA + lenB];

        int i = 0, j = 0, k = 0;
        while (i < lenA  ||  j < lenB){
            int valA = i < lenA ? A[i] : Integer.MAX_VALUE;
            int valB = j < lenB ? B[j] : Integer.MAX_VALUE;

            if (valA <= valB)
                arr[k++] = A[i++];
            else if (valB < valA)
                arr[k++] = B[j++];
        }

        if ((lenA + lenB) % 2 == 1)
            return arr[(lenA + lenB)/2];
        else
            return (arr[(lenA + lenB)/2] + arr[(lenA + lenB)/2 - 1]) / 2.0;
    }
}
