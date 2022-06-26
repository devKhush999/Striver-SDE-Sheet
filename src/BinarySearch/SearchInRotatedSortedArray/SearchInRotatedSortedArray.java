package BinarySearch.SearchInRotatedSortedArray;

// https://youtu.be/r3pMQ8-Ad5s
// https://takeuforward.org/data-structure/search-element-in-a-rotated-sorted-array/

public class SearchInRotatedSortedArray {
    /*
    ******************************** Simple Array Traversal ********************************
    * Time Complexity ==> O(n)
    * Space Complexity ==> O(1)
     */
    public int search_BruteForce(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == target)
                return i;
        return -1;
    }

    /*
    *********************************** Using Binary Search ***********************************
    * Intuition :
        It is mentioned that the array given is sorted but in a rotated manner.
        So, we can divide a given array into two parts (two halves) that are sorted.
        This gives us hints to use binary search. We can visualize a given array to be composed
        of two sorted arrays.
    * Approach :
        We divide the array into parts. It is done using two pointers, low and high, and dividing
        the range between them by 2. This gives the midpoint of the range.
        Check if the target is present in the midpoint, calculated before, of the array.
        If not present, check if the left half of the array is sorted. This implies that binary
        search can be applied in the left half of the array provided the target lies between the
        value range. Else check into the right half of the array.
        Repeat the above steps until low <= high.
        If low > high, indicates we have searched array and target is not present hence return -1.
        Thus, it makes search operations less as we check range first then perform searching
        in possible ranges which may have target value.
     * Time Complexity: O(log(N))
        Reason: We are performing a binary search, this turns time complexity to O(log(N)) where
        N is the size of the array.
      * Space Complexity: O(1)
        Reason: We do not use any extra data structure, this turns space complexity to O(1).
     */
    public int search_BinarySearch(int[] arr, int target) {
        int low = 0, high = arr.length -1;

        while (low <= high){
            int mid = low + (high - low)/2;

            if (arr[mid] == target)
                return mid;

            // left half sub-array from arr[low] to arr[mid] is sorted
            if (arr[low] <= arr[mid]){
                // Check whether target lies in left half of array.
                // If yes do Binary Search on left half by eliminating Right half
                if (target >= arr[low] && target <= arr[mid])
                    high = mid - 1;
                // If Not do Binary Search on right half by eliminating Left half
                else
                    low = mid + 1;
            }
            // right half sub-array from arr[mid] to arr[high] is sorted
            else {
                // Check whether target lies in Right half of array.
                // If yes do Binary Search on Right half by eliminating Left half
                if (target >= arr[mid] && target <= arr[high])
                    low = mid + 1;
                // If Not do Binary Search on Left half by eliminating Right half
                else
                    high = mid - 1;
            }
        }
        return -1;
    }
}
