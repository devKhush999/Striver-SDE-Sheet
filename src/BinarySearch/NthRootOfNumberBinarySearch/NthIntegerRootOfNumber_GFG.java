package BinarySearch.NthRootOfNumberBinarySearch;

// https://practice.geeksforgeeks.org/problems/find-nth-root-of-m5843/1/#

public class NthIntegerRootOfNumber_GFG {
    /*
     ************************************* Simple Binary Search *************************************
     Time Complexity -> O(log(m) * log(n))
     Reason:
     1) log(m) due to Binary Search on the range [1, m] to find Nth root of M AND
     2) log(n) because in each search (to find Nth root of M) we are calculating Math.power(mid, n)
        to find the 'mid ^ n'
     * We can also do the same in Time Complexity of O(n * log(m)) if we directly multiply 'mid' n times
     to find 'mid ^ n"

     Space Complexity: O(1)         No extra space is used
     */
    // Return the only Integer which is Exactly the Nth root of M
    // For eg,  For NthRoot_OnlyInteger(8, 3) = 2,  NthRoot_OnlyInteger(8, 4) = -1, NthRoot_OnlyInteger(8, 2) = -1
    public static int NthRoot_OnlyInteger(int n, int m){
        int low = 1, high = m;

        while (low <= high){
            int mid = (low + high) / 2;
            // double power = Math.pow(mid, n);
            double power = power(mid, n);

            if (power == m)
                return mid;
            else if (power < m)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }



    // *********************** This Question is similar to Square Root of Leetcode **********************
    // See Folder "SquareRoot"
     /*
     Time Complexity -> O(log(m) * log(n))
     Reason:
     1) log(m) due to Binary Search on the range [1, m] to find Nth root of M AND
     2) log(n) because in each search (to find Nth root of M) we are calculating Math.power(mid, n)
        to find the 'mid ^ n'
     * We can also do the same in Time Complexity of O(n * log(m)) if we directly multiply 'mid' n times
     to find 'mid ^ n"

     Space Complexity: O(1)         No extra space is used
     */
    // Return the last Nth Integer Root of M which is last-most the Nth root of M
    // For eg.  For NthRoot_LastInteger(8, 3) = 2,  NthRoot_LastInteger(8, 4) = 1, NthRoot_LastInteger(8, 2) = 2

    public int NthRoot_LastInteger(int n, int m){
        int low = 1, high = m;
        int nthRoot = 1;

        // Standard Binary Search
        while (low <= high){
            int mid = (low + high) / 2;

            // Finding the mid^n
            double power = power(mid, n);

            // If "power <= m" then save the current answer 'mid' and shrink Search Space to right
            // sub-half to find further greater value of nthRoot
            if (power <= m){
                nthRoot = mid;
                low = mid + 1;
            }
            // If "power > m" then shrink the Search Space to left sub-half
            else
                high = mid - 1;
        }
        return nthRoot;
    }


    // *************************  Standard Power Function *************************
    private static double power(double x, int n){
        long N = n;
        if (n < 0)
            N = -N;

        double power = 1;
        while (N != 0){
            if (N % 2 == 0){
                x = x * x;
                N /= 2;
            }
            else{
                power = power * x;
                N--;
            }
        }

        return n >= 0 ? power : 1 / power;
    }

}
