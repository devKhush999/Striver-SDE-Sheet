package Arrays.MaxConsecutiveOnes;

// https://youtu.be/Mo33MjjMlyA
// https://takeuforward.org/data-structure/count-maximum-consecutive-ones-in-the-array/

public class MaxConsecutiveOnes {
    /*
    **************************** Sliding Window Approach ********************************************
    * Sliding Window Approach OR Two pointers Approach
    * The Approach is similar to the Longest Substring without repeating character Hashing Solution

    * In worst case there will be two traversal of the array, one traversal by 'right' pointer
      and one pointer by 'left' pointer. Because we are assigning left to ++right.
      So, in worst case both 'left' & 'right' pointer can travel till array's length
     * TC -> O(n) + O(n) = O(n)     at most two traversals in worst case
     * SC -> O(1)
     */
    public int findMaxConsecutiveOnes_SlidingWindow(int[] arr) {
        int left = 0, right = 0;
        int maxOnesLength = 0;

        while (right != arr.length){
            if (arr[right] == 1){
                maxOnesLength = Math.max(maxOnesLength, right - left + 1);
                right++;
            }
            else if (arr[right] == 0)
                left = ++right;
        }

        return maxOnesLength;
    }


    /*
    ************************************** Efficient Solution **************************************
    * Logic is Simple
    * TC -> O(n)
    * SC -> O(1)
    */
    public int findMaxConsecutiveOnes(int[] arr) {
        int maxOnesLength = 0;
        int currOnesLength = 0;

        for (int i = 0; i < arr.length; i++){
            // If current value is 1, we increment the value of 'currOnesLength' as current consecutive ones
            // is still going on
            if (arr[i] == 1)
                currOnesLength++;

            // If current value is 0, we make the variable 'currOnesLength' as 0 since there are no
            // more consecutive ones.
            else
                currOnesLength = 0;

            // After updating the 'currOnesLength' variable if it becomes more than the 'maxOnesLength'
            // update the 'maxOnesLength'
            maxOnesLength = Math.max(maxOnesLength, currOnesLength);
        }
        return maxOnesLength;
    }



}
