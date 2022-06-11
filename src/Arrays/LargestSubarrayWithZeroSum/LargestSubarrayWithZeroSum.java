package Arrays.LargestSubarrayWithZeroSum;
import java.util.HashMap;

// https://takeuforward.org/data-structure/length-of-the-longest-subarray-with-zero-sum/
// https://youtu.be/xmguZ6GbatA
// https://www.geeksforgeeks.org/find-the-largest-subarray-with-0-sum/
// https://practice.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1

public class LargestSubarrayWithZeroSum {

    // ************************************** Brute Force *********************************************
    // Check/Explore all the sub-arrays & see if their sum is zero or not, if sum is zero then update the max. length
    // TC -> O(n*n)
    // SC -> O(1)
    int maxLengthSubArrayWith0Sum_BruteForce(int[] arr, int n){
        int len = 0;

        for (int i = 0; i < n; i++){
            int sum = 0;

            for (int j = i; j < n; j++){
                sum += arr[j];

                if (sum == 0)
                    len = Math.max(len, j - i + 1);
            }
        }
        return len;
    }


    // ********************************* Efficient Solution using Hashing ********************************
    // 1) are two conditions under which we can get Sub array with sum
    // One when the prefixSum at ith index itself becomes 0, then till the ith index (i+1) is required length
    // of subarray with 0 sum
    // 2) Now let’s say we know that the sum of subarray(i, j) = S, and we also know that sum of
    // subarray(i, x) = S where i < x < j. We can conclude that the sum of subarray(x+1, j) = 0.

    // TC -> O(n)
    // SC -> O(n)

    private int maxLengthSubArrayWith0Sum(int[] arr, int n){
        int maxSubArrayLength = 0;
        int prefixSum = 0;

        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();

        for (int i = 0; i < arr.length; i++){
            prefixSum += arr[i];

            // we don't meed Math.max() condition here, as if the entire prefix sum becomes 0 at any index 'i'
            // then it will be of maximum length till index 'i' (think)
            // So no need to check for next (else if) condition
            if (prefixSum == 0)
                maxSubArrayLength = i + 1;

            else if (prefixSumMap.containsKey(prefixSum)) {
                    int startIndexOfSum = prefixSumMap.get(prefixSum);
                    int endingIndexOfSameSum = i;
                    maxSubArrayLength = Math.max(maxSubArrayLength, endingIndexOfSameSum - startIndexOfSum);
            }

            // We do not update the 'prefixSum' in HashMap if 'prefixSum' exits earlier in the map
            // because we want maximum Sub array length with sum 0 & not recent one
            // So we added a 'else block' here
            else
                prefixSumMap.put(prefixSum, i);
        }
        return maxSubArrayLength;
    }

}
