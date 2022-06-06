package Arrays.MaximumSubarraySum;

// https://takeuforward.org/data-structure/kadanes-algorithm-maximum-subarray-sum-in-an-array/
// https://youtu.be/w_KEocd__20

public class KadaneAlgorithm {

    public int maxSubArray(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;

        for (int i = 0; i < arr.length; i++){
            currSum += arr[i];

            maxSum = Math.max(maxSum, currSum);

            if (currSum < 0)
                currSum = 0;
        }

        return maxSum;
    }
}
