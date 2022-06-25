package BinarySearch.NthRootOfNumberBinarySearch;

// https://youtu.be/WjpswYrS2nY
// https://takeuforward.org/interviews/strivers-sde-sheet-top-coding-interview-problems/

public class NthRootOfNumber {
    /*
    ********************************** Solution 1: Simple Binary Search ********************************
    * Problem with this method is that while multiplying the number 'mid' n times in multiply() function.
      overflow may occur in the value of product
    * Time Complexity : O(log(N) * log(M * 10^d))
      log(N) due to because we are finding the "mid^n" using Power Function
      where 'd' is the decimal value till which error is allowed
    * We can also do the same in Time Complexity of O(N * log(M * 10^d)) if we directly
    * multiply 'mid' n times to find 'mid ^ n"
    * Space Complexity : O(1)
     */
    public double findNthRootOfM(int n, long m) {
        double error = 1e-7;
        double low = 1, high = m;

        // Dp binary search till difference of high & low is greater than error value
        // This can be interpreted as "high - low >= 0"  ==>  "low <= high"
        while (high - low >= error){
            double mid = (low + high) / 2;

            // Instead of multiplying 'mid' n times, we can find Math.pow(mid, n) to get the same
            // not doing "low = mid + 1", as decimal value may get disturbed
            if (Math.pow(mid, n) <= m)
                low = mid;

            // not doing "high = mid - 1", as decimal value may get disturbed
            else
                high = mid;
        }
        // We will run the while loop till (high – low > eps). When we will come out of the loop we
        // will have the answer which will be equal to low as well as high.
        // return low;
        return high;
    }

    private double multiplyNTimes(double number, int n){
        double ans = 1;
        for (int i = 1; i <= n; i++)
            ans *= number;
        return ans;
    }
}
