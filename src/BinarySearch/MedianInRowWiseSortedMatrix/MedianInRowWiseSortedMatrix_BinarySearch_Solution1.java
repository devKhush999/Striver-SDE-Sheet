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
1) First, we find the minimum and maximum elements in the matrix.
   The minimum element can be easily found by comparing the first element of each row, and
   similarly, the maximum element can be found by comparing the last element of each row.

2) Then we use binary search on our range of numbers from minimum to maximum, we find the 'mid' of
   the 'low' and 'high' and get a count of numbers less than or equal to our 'mid'.
   And accordingly change the min or max.
3) For a number to be median, there should be (r*c)/2 numbers smaller than that number (on left side of it).
   So for every number, we get the count of numbers less than or equal to that by using
   countOfNumberLessThanOrEqualTo() in each row of the matrix.
   *  If it is less than or equal to the required count (r*c)/2, the median must be greater than the
      selected number (as including the 'mid' there should be more than (r*c)/2 numbers on left of it).
      So, we make 'low' to "mid + 1"
   *  Else the 'mid' value can be our possible answer, i.e, median must be less than or
      equal to the selected number. So, we SAVE the current value of 'mid' & make 'high' to "mid - 1"
*/

public class MedianInRowWiseSortedMatrix_BinarySearch_Solution1 {
    /*
        Time Complexity: O(R) + O(log2(max - min) * R * log(C))   =  O(R * log(C))
        The countOfNumberLessThanOrEqualTo() function will take "log(c)" time and is performed for each row.
        And since the numbers 'high' & 'low' will be max. & min. of array, so binary search of
        numbers from min to max will be performed in at most log2(max - min) operations.

        Auxiliary Space: O(1)
     */
    public int median(int[][] matrix, int r, int c) {
        int low = 1, high = (int)1e8;
        int median = 0;

        // Finding the min. & max. element of the array to perform
        // Binary Search on minimum & maximum element in the array
        for (int i = 0; i < r; i++){
            low = Math.min(matrix[i][0], low);            // Finding the minimum element
            high = Math.max(matrix[i][c-1], high);        // Finding the maximum element
        }

        while (low <= high){
            int mid = low + (high - low)/2;

            // Find count of elements smaller than or equal to current value of 'mid'
            int count = 0;
            for (int i = 0; i < r; i++)
                count += countOfNumberLessThanOrEqualTo(matrix[i], mid, c);

            // If 'count' is less than or equal to the required count (r*c)/2, the median must be
            // greater than the 'mid' (as including the 'mid' there should be more than (r*c)/2 numbers
            // on left of it).
            if (count <= (r * c)/2)
                low = mid + 1;

            // Else the 'mid' value can be our possible answer, i.e, median must be less than or
            // equal to the selected number. So, we save the current value of 'mid'
            else{
                median = mid;
                high = mid - 1;
            }
        }
        // return median of array
        return median;
    }


    // This function calculates the Count of numbers lesser than or equal to the no. 'num' in the array
    // Using Binary Search Algorithm
    private int countOfNumberLessThanOrEqualTo(int[] arr, int num, int n){
        int low = 0, high = n - 1;
        int countOfLessThanOrEqualTo = 0;

        while (low <= high){
            int mid = low + (high - low)/2;

            if (arr[mid] <= num){
                countOfLessThanOrEqualTo = mid + 1;
                low = mid + 1;
            }
            else
                high = mid - 1;
        }
        return countOfLessThanOrEqualTo;
    }
}

/*
And then after we do mid = (low+high)/2, and again we are uncertain whether mid even lies in the
matrix or not, and we keep doing it until we come to a favourable situation, and that uncertainty
still prevails , but how does this guarantees that whatever 'low' was last calculated actually lies
in the matrix???

1) That's why we keep moving the pointers 'low' & 'high', till we don't reach to a number where this
   pattern breaks...
2) Suppose, if current number 'mid' is not present in matrix, then the count of numbers
   lesser than or equal to 'mid' will be same as the "Maximum Smallest number" than 'mid' present
   in the matrix (think...).
   Then, two cases arises:
   2.1) If 'mid' is smaller or equal to (m*n)/2, then 'low' keeps on increasing to "mid + 1"
        until the low reaches a new element present in the matrix or greater than it.
   2.2) If 'mid' is greater than (m*n)/2, then 'high' keeps on decreasing to "mid - 1"
        until the high reaches a new element present in the matrix or smaller than it.
   This step (2) will continue to occur until low or high reaches to an element in the matrix.
   After that when low becomes greater than high', low will become equal to the element which is
   the median of the matrix.
   Try Dry Running over the Test-cases to understand it better:
   Matrix => { {1,  20,  75 }
               {10, 100, 300}
               {20, 100, 300} }

Hence, low > high is the break point and 'low' will contain the median element
 */
