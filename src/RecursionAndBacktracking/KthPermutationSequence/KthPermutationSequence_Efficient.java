package RecursionAndBacktracking.KthPermutationSequence;
import java.util.ArrayList;

// https://youtu.be/wT7gcXLYoao
// MUST READ:
// https://takeuforward.org/data-structure/find-k-th-permutation-sequence/

public class KthPermutationSequence_Efficient {
    /*  ******************************* Efficient Solution ******************************************
     * Time Complexity: O(N) * O(N) + O(N)  =  O(N^2)
       Reason: O(N) for generating arraylist of n numbers & previous factorial
       We are placing N numbers in N positions. This will take O(N) time.
       For every number, we are reducing the search space by removing the element already placed
       in the previous step. This takes another O(N) time.
     * Space Complexity: O(N) + O(N)  =  O(N)
       Reason: O(N) for storing the numbers in a List. O(N) fo String ouptut
    */
    private String getPermutation_Iterative(int n, int k) {
        int previousFactorial = 1;
        ArrayList<Integer> number = new ArrayList<>();

        // Compute previous factorial (factorial of (n-1)) & store all the 'n' numbers in a list
        for (int i = 1; i <= n; i++){
            number.add(i);
            if (i != n)
                previousFactorial *= i;
        }

        // Decreasing 'k' for 0-based indexing
        k--;
        // Recall that String Builder are faster tha strings. But, we can also use Strings too
        StringBuilder kthPermutation = new StringBuilder();

        // Now 'n' will store the size of ArrayList
        while (true){
            // determine which number to be put in kth permutation depending upon the logic
            kthPermutation.append(number.get(k / previousFactorial));
            number.remove(k / previousFactorial);
            n--;

            if (n == 0)
                break;
            k = k % previousFactorial;
            previousFactorial = previousFactorial / n;
        }
        return kthPermutation.toString();
    }



    // ********************************* Recursive Solution **************************************
    // Iterative Solution converted into Recursive One
    public String getPermutation_Recursive(int n, int k) {
        int previousFactorial = 1;
        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= n; i++){
            numbers.add(i);
            if (i != n)
                previousFactorial *= i;
        }

        return findKthPermutation(n, k - 1, previousFactorial, numbers);
    }

    private String findKthPermutation(int n, int k, int prevFact, ArrayList<Integer> nums){
        String kthPermutation = "" + nums.get(k / prevFact);
        nums.remove(k / prevFact);
        n--;

        if (n == 0)
            return kthPermutation;
        return kthPermutation + findKthPermutation(n, k % prevFact, prevFact / n, nums);
    }
}
