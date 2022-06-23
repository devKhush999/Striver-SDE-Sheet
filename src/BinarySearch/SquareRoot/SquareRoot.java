package BinarySearch.SquareRoot;

// https://leetcode.com/problems/sqrtx/

public class SquareRoot {
    /*
    Time Complexity: O(log(n))
    Space Complexity: O(1)
    */
    // ************************************** Basic Approach **************************************
    public int mySqrt(int n) {
        int low = 1, high = n;
        int sqrt = 0;

        while (low <= high){
            int mid = (low+high) /  2;

            // overflow may occur when do 'mid * mid' if 'n' is Integer.MAX_VALUE
            if (mid * mid == n)
                return mid;
            else if (mid * mid > n)
                high = mid - 1;
            else{
                sqrt = mid;
                low = mid + 1;
            }
        }
        return sqrt;
    }


    // ************************************** Correct Solution **************************************
    public int mySqrt_CorrectSolution(int n) {
        int low = 1, high = n;

        // made sqrt as 0, to satisfy the answer for n = 0
        int sqrt = 0;

        // Standard Binary Search
        while (low <= high){
            int mid = (low + high) / 2;

            // To avoid overflow in value of "mid * mid" we do "mid <= n / mid"
            // If "mid <= n/mid" then save the current answer 'mid' and shrink Search Space to right
            // sub-half to find further greater value of sqrt
            if (mid <= n / mid){
                sqrt = mid;
                low = mid + 1;
            }
            // If "mid > n/mid" then shrink the Search Space to left sub-half
            else
                high = mid - 1;
        }
        return sqrt;
    }
}
