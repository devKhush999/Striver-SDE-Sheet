package BinarySearch.SingleElementInSortedArray;

// https://www.youtube.com/watch?v=nMGL2vlyJk0
// https://youtu.be/PzszoiY5XMQ
// https://takeuforward.org/data-structure/search-single-element-in-a-sorted-array/
// MUST READ: https://www.geeksforgeeks.org/find-the-element-that-appears-once-in-a-sorted-array/

/*
**************************************** Intuition ****************************************
* Below is an observation on the input array.

* All elements before the required single element have the first occurrence at even
  index (0, 2, ..) and the next occurrence at odd index (1, 3, …).
  And all elements after the required elements have the first occurrence at an odd index and
  the next occurrence at an even index.

1) Find the middle index, say ‘mid’.
2) If ‘mid’ is even, then compare arr[mid] and arr[mid + 1].
   If both are the same, then the required element is present after ‘mid’ and else it is
   present before mid.
3) If ‘mid’ is odd, then compare arr[mid] and arr[mid – 1]. If both are the same, then the
   required element is present after ‘mid’ and else before mid.
 */
public class SingleElementInSortedArray_BinarySearch {
    /*
    ********************************** Binary Search: V1 *****************************************
    * Time Complexity: O(log(n))
    * Auxiliary Space: O(1)
    */
    public int singleNonDuplicate_BinarySearch1(int[] arr) {
        int n = arr.length;
        int low = 0, high = n - 1;

        // Some Edge Cases to Handle
        if (n == 1)
            return arr[0];
        if (arr[low] != arr[low + 1])
            return arr[low];
        if (arr[high] != arr[high - 1])
            return arr[high];

        while (low <= high){
            int mid = (low + high)/2;

            if (arr[mid] != arr[mid - 1]  && arr[mid] != arr[mid + 1])
                return arr[mid];

            if (mid % 2 == 0){
                if (arr[mid] == arr[mid + 1])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
            else{
                if (arr[mid] == arr[mid - 1])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }
        return -1;
    }


    /*
     ********************************** Binary Search: V2 *****************************************
     * Time Complexity: O(log(n))
     * Auxiliary Space: O(1)
     *
     * We do Binary Search from [0, n-2]
     * ********************************** CASE - 1 ***********************************
     * What if the last element in the array is single element?
     * If last element in the array is single element, then the sub-array from index 0 to n-2,
       i.e,arr[0:n-2] is always in the valid condition (has all elements occurring twice).
     * In that case, 'high' will never move to "mid - 1" regardless of whether the 'mid' index
       is even or odd.
     * Only 'low' pointer will keep on moving to "mid + 1" as the sub-array from
       index 0 to n-2 (i.e, arr[0:n-2]) has no single elements.
     * A situation will come when 'low' will overpass the 'high' (low > high)
     * And, we are simply returning arr[low]. So, it gets handled.
     *
     * ********************************** CASE - 2 ***********************************
     * Single element is in subarray arr[0:n-2]
     * In that case index at which Single element is occurring is always even (think)
     * In comparison to previous code, one condition has been omitted when, 'mid' is pointing to
       that Single element.
     * So, binary search goes as usual. When 'mid' is pointing to that Single element,
       This in turn means "low = high = mid"  as binary search goes same as in previous code.
       So, 'mid' will always be even. We will go in (mid % 2 == 0) if condition.
       As 'arr[mid] != arr[mid+1]' high will become 'mid - 1' & this will cause 'low' to
       overpass 'high'.
     * And, we are simply returning arr[low]. So, it gets handled.
     */
    public int singleNonDuplicate_BinarySearch2(int[] arr) {
        int low = 0, high = arr.length - 2;

        while (low <= high){
            int mid = (low + high)/2;

            // One condition of (arr[mid] != arr[mid - 1]  && arr[mid] != arr[mid + 1]) is omitted
            // which is handled by below if condition (mid % 2 == 0)

            // Same as previous code
            if (mid % 2 == 0){
                if (arr[mid] == arr[mid + 1])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
            else if (mid % 2 == 1){
                if (arr[mid] != arr[mid + 1])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }
        // It is guaranteed that arr[low] will contain the single element
        // DO some Dry Run to understand
        return arr[low];
    }
}
