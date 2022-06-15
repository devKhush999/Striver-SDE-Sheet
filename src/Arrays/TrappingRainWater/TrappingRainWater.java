package Arrays.TrappingRainWater;

// https://youtu.be/UZG3-vZlFM4
// https://youtu.be/m18Hntz4go8
// https://takeuforward.org/data-structure/trapping-rainwater/

public class TrappingRainWater {

    /*
    ************************** Approach 1 : Brute Force *******************************************
    * Time Complexity: O(N*N) as for each index we are calculating leftMax and rightMax. So it is a nested loop.
    * Space Complexity: O(1).
     */
    public int trapWater_BruteForce(int[] height) {
         int waterTrapped = 0;

         for (int i = 0; i < height.length; i++){
             int leftMaxHeight = 0, rightMaxHeight = 0;

             int j = i;
             while (j >= 0)
                 leftMaxHeight = Math.max(height[j--], leftMaxHeight);

             j = i;
             while (j < height.length)
                 rightMaxHeight = Math.max(height[j++], rightMaxHeight);

             waterTrapped += Math.min(leftMaxHeight, rightMaxHeight) - height[i];
         }
         return waterTrapped;
    }


    /*
     ************************** Approach 2: Using Dynamic Programming ****************************
     * Time complexity: O(n) + O(n) + O(n) =  O(n)
     * We store the prefix & suffix maximum heights upto a point using 2 iterations of O(n) & O(n) each
     * We finally update "maxWaterStored" using the stored values in O(n) time again

     * Space complexity: O(n) + O(n) = O(n)     extra space.
     * Additional O(n) space for left_max and right_max height of arrays.
    */
    public int trapWater_DynamicProgramming(int[] height) {
        int n = height.length;

        // stores next maximum bar height for each bar at given index, also called suffix maximum
        int[] nextMaximumBarHeight = new int[n];

        // stores previous maximum bar height for each bar at given index, also called prefix maximum
        int[] prevMaximumBarHeight = new int[n];

        // Base cases for next & previous greater bar heights
        prevMaximumBarHeight[0] = height[0];
        nextMaximumBarHeight[n-1] = height[n-1];

        // calculate previous maximum bar height that ever existed
        for (int i = 1; i < n; i++)
            prevMaximumBarHeight[i] = Math.max(height[i], prevMaximumBarHeight[i-1]);

        // calculate next maximum bar height that ever existed
        for (int i = n-2; i >= 0; i--)
            nextMaximumBarHeight[i] = Math.max(height[i], nextMaximumBarHeight[i+1]);

        int waterStored = 0;

        // Sums up the water stored by all the bars
        for (int i = 0; i < n; i++){
            int waterStoredByCurrentBar = Math.min(prevMaximumBarHeight[i], nextMaximumBarHeight[i]) - height[i];
            waterStored += waterStoredByCurrentBar;
        }
        return waterStored;
    }



    /*
    **************************** Approach 3 : Two Pointers ****************************************
    * Time complexity: O(n)      Single iteration of O(n)
    * Space complexity: O(1)     extra space. Only constant space required for left_max and right_max heights
    */

    private int trapWater(int[] height){
        int low = 0, high = height.length - 1;

        int maxHeightOnLeft = 0, maxHeightOnRight = 0;
        int totalWaterStored = 0;

        while (low < high){

            if (height[low] <= height[high]){
                if (height[low] >= maxHeightOnLeft)
                    maxHeightOnLeft = height[low];

                else if (height[low] < maxHeightOnLeft)
                    totalWaterStored += (maxHeightOnLeft - height[low]);

                low++;
            }

            else if (height[high] < height[low]){
                if (height[high] >= maxHeightOnRight)
                    maxHeightOnRight = height[high];

                else if (height[high] < maxHeightOnRight)
                    totalWaterStored += (maxHeightOnRight - height[high]);

                high--;
            }
        }
        return totalWaterStored;
    }
}
