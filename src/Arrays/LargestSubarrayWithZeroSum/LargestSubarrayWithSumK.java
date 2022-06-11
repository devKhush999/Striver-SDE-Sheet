package Arrays.LargestSubarrayWithZeroSum;
import java.util.HashMap;

// https://takeuforward.org/data-structure/length-of-the-longest-subarray-with-zero-sum/
// https://youtu.be/xmguZ6GbatA
// https://www.geeksforgeeks.org/find-the-largest-subarray-with-0-sum/
// https://practice.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1
// Question of Striver SDE Sheet

public class LargestSubarrayWithSumK {

    // *********************************** Generalized Version *************************************

    //                                 Efficient Solution using Hashing
    // 1) There are two conditions under which we can get Sub array with target sum 'k'
    // One when the prefixSum at ith index itself becomes 'k', then till the ith index (i+1) is required length
    // of subarray with target sum 'k', and it is the longest till 'ith' index
    // 2) Now let’s say we know that the sum of subarray(i, j) = S, and we also know that sum of
    // subarray(i, x) = S - k, where i < x < j. We can conclude that the sum of subarray(x+1, j) = k.

    // TC -> O(n)
    // SC -> O(n)       due to HashMap

    private int maxLengthSubArrayWith0Sum(int[] arr, int k){
        int maxSubArrayLength = 0;
        int prefixSum = 0;

        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();

        for (int i = 0; i < arr.length; i++){
            prefixSum += arr[i];

            // we don't meed Math.max() condition here, as if the entire prefix sum becomes 'k' at any index 'i'
            // then it will be of maximum length till index 'i' (think...)
            // So no need to check for next (else if) condition
            if (prefixSum == k)
                maxSubArrayLength = i + 1;

            else if (prefixSumMap.containsKey(prefixSum - k)) {
                int startIndexOfSum = prefixSumMap.get(prefixSum - k);
                int endingIndexOfSameSum = i;
                maxSubArrayLength = Math.max(maxSubArrayLength, endingIndexOfSameSum - startIndexOfSum);
            }

            // We do not update the 'prefixSum' in HashMap if 'prefixSum' exits earlier in the map
            // because we want maximum Sub array length with sum k & not recent one
            // So we added a 'else block' here
            else
                prefixSumMap.put(prefixSum, i);
        }
        return maxSubArrayLength;
    }

}
