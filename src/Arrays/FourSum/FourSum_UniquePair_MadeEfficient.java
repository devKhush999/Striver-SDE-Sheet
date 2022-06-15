package Arrays.FourSum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://youtu.be/4ggF3tXIAp0
// https://takeuforward.org/data-structure/4-sum-find-quads-that-add-up-to-a-target-value/

public class FourSum_UniquePair_MadeEfficient {

    // **************************** Efficient Solution ****************************************888
    // Same solution can be made faster by not iterating over ranges of array/values that are
    // out of range of targetSum. We can avoid redundant iteration based on certain conditions
    // 'Redundant' in sense that there is no use of going through current iteration
    // TC -> O(n^3)
    // SC -> O(1)
    // This problem is combination of problems 'Three sum' & 'Two sum: Input array is sorted'

    private List<List<Integer>> fourSum(int[] arr, int target) {
        int n = arr.length;
        List<List<Integer>> allQuadruplets = new ArrayList<>();

        // 4-Sum requires min. 4 elements, in case no. of elements are less, return empty List
        // This is optional step, it is taken care by range in iteration of for loops
        if (n < 4)
            return allQuadruplets;

        // Sorting is a must to ignore duplicates
        Arrays.sort(arr);


        // Loop running from 0 to n-4, i.e, [0, n-4] to account for exactly 3 values after 'ith' index
        for (int i = 0; i < n-3; i++) {

            // Ignoring Duplicates of ith index
            if (i > 0  && arr[i] == arr[i-1])
                continue;

            // If in current 'ith' iteration, first four values are greater than target, then break this
            // loop, bcoz after that 'ith' index (i+1, i+2...) it will exceed target too (as array is sorted)
            if ((long)arr[i] + arr[i+1] + arr[i+2] + arr[i+3] > target)
                break;
            // If ith value of array & lst three values are lesser than target, then skip this index & move
            // to greater 'ith' index in search for target
            if ((long)arr[i] + arr[n-1] + arr[n-2] + arr[n-3] < target)
                continue;

            // Loop running from 0 to n-3, i.e, [0, n-3] to account for exactly 2 values after 'jth' index
            for (int j = i + 1; j < n-2; j++) {
                // Ignoring Duplicates of jth index
                if (j > i + 1  && arr[j] == arr[j-1])
                    continue;

                // If in current 'jth' iteration, first three values & arr[i] are greater than target, then break this
                // loop, bcoz after that 'jth' index (j+1, j+2...) it will exceed target too (as array is sorted)
                if ((long)arr[i] + arr[j] + arr[j+1] + arr[j+2] > target)
                    break;
                // If 'ith' & 'jth' value of array & last two values are lesser tha target, then skip this index & move
                // to greater 'jth' index in search for target
                if ((long)arr[i] + arr[j] + arr[n-1] + arr[n-2] < target)
                    continue;

                // Here the problem 'Two Sum : Input Array is Sorted starts'
                int low = j + 1;
                int high = n - 1;

                while (low < high) {
                    if (arr[i] + arr[j] + arr[low] + arr[high] == target) {
                        ArrayList<Integer> currQuadruple = new ArrayList<>();

                        currQuadruple.add(arr[i]);
                        currQuadruple.add(arr[j]);
                        currQuadruple.add(arr[low]);
                        currQuadruple.add(arr[high]);
                        allQuadruplets.add(currQuadruple);

                        // Ignoring duplicates values of arr[low] & arr[high]
                        while (low + 1 < high  && arr[low] == arr[low + 1])
                            low++;
                        while (high - 1 > low  &&  arr[high] == arr[high - 1])
                            high--;

                        // We have to move low & high pointers, as the next target sum given by arr[low]
                        // & arr[high] may exits on after 'low' index & before 'high' index
                        low++;
                        high--;

                    }
                    else if (arr[i] + arr[j] + arr[low] + arr[high] > target)
                        high--;
                    else if (arr[i] + arr[j] + arr[low] + arr[high] < target)
                        low++;
                }
            }
        }
        return allQuadruplets;
    }
}
