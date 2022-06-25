package BinarySearch.MedianInRowWiseSortedMatrix;

// https://takeuforward.org/data-structure/median-of-row-wise-sorted-matrix/
// https://www.geeksforgeeks.org/find-median-row-wise-sorted-matrix/
// https://afteracademy.com/blog/median-in-a-row-wise-sorted-matrix#:~:text=Given%20a%20N*M%20matrix,the%20mean%20of%20the%20numbers.
// https://youtu.be/63fPPOdIr2c

/*
*************** Intuition ***************
The idea is that for a number to be median there should be exactly (n/2) numbers that are less than
this number. So, we try to find the count of numbers less than all the numbers

*************** Algorithm ***************:
1) First, we assign 'low' & 'high' to be 1 & 10^8 respectively, (these values can be taken
   as per the range of value matrix given in Question)
2) Then we use binary search on our range of numbers from 'low' to 'high', we find the 'mid' of
   the 'low' and 'high' and get a count of numbers less than or equal to our 'mid'.
   And accordingly change the 'low' & 'high'.
3) For a number to be median, there should be (r*c)/2 numbers smaller than that number (on left side of it).
   So for every number, we get the count of numbers less than or equal to that by using
   countOfNumberLessThanOrEqualTo() in each row of the matrix.
   *  If it is less than or equal to the required count (r*c)/2, the median must be greater than the
      selected number (as including the 'mid' there should be more than (r*c)/2 numbers on left of it).
      So, we make 'low' to "mid + 1"
   *  Else the 'mid' value can be our possible answer, i.e, median must be less than or
      equal to the selected number. So, we make 'high' to "mid - 1"
4) When low becomes greater than high, low will point to the MEDIAN element of the matrix.
*/

public class MedianInRowWiseSortedMatrix_BinarySearch_Solution2 {
    /*
        Time Complexity: O(32 * r * log(c))    =  O(r * log(c))
        The countOfNumberLessThanOrEqualTo() function will take log(c) time and is performed for each
        row. And since the numbers will be max of 32 bit, so binary search of numbers from low to high
        will be performed in at most 32 ( log2(2^32) = 32 ) operations.

        Auxiliary Space: O(1)
     */
    public int findMedian(int[][] arr, int m, int n) {
        // 'low' & 'high' of Binary Search
        int low = 1;
        int high = (int) 1e8;

        while (low <= high){
            int mid = low + (high - low) / 2;

            // Find count of elements smaller than or equal to current value of 'mid'
            int countOfLessThanOrEqualTo = 0;
            for (int i = 0; i < m; i++)
                countOfLessThanOrEqualTo += countOfNumberLessThanOrEqualTo(arr[i], mid, n);

            // If 'countOfLessThanOrEqualTo' is less than or equal to the required count (r*c)/2,
            // the median must be greater than the 'mid' (as including the 'mid' there should be
            // more than (r*c)/2 numbers on left of it).
            if (countOfLessThanOrEqualTo <= (m * n)/2)
                low = mid + 1;

            // Else the 'mid' value can be our possible answer, i.e, median must be less than or
            // equal to the selected number
            else
                high = mid -1;
        }
        // When low becomes greater than high, low will point to the MEDIAN element of the matrix.
        return low;
    }


    // This function calculates the Count of numbers lesser than or equal to the no. 'num' in the array
    // This another version of previous Same code as in Solution-1
    // Problem is REDUCED to "Finding the first element greater than 'num' in the sorted array"
    // Using Binary Search Algorithm
    private int countOfNumberLessThanOrEqualTo(int[] arr, int num, int n){
        int low = 0, high = n - 1;

        while (low <= high){
            int mid = low + (high - low)/2;

            if (arr[mid] <= num)
                low = mid + 1;
            else
                high = mid - 1;
        }
        // 'low' will contain the index of the first element greater than 'num' in the sorted array
        // equal to number of element less than or equal to 'num'
        return low;
    }

}
