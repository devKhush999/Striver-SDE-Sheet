package RecursionAndBacktracking.SubsetSums;
import java.util.ArrayList;
import java.util.Collections;

// https://youtu.be/rYkfBRtMJr8
// https://practice.geeksforgeeks.org/problems/subset-sums2234/1
// https://takeuforward.org/data-structure/subset-sum-sum-of-all-subsets/

// Pre req ==> Sub Sequence of array (both versions)

public class SubsetSums {
    /*
    ***************************** Approach 1 : Pick & Not Pick Approach *********************************
    * TC -> O(n * 2^n)      Generating all possible subsets sums that are 2^n in numbers

    * Ignoring the output ArrayList of Subsets (that are 2^n in number) into Space complexity
    * SC -> O(n)        At most n recursive calls, as we can either pick/not-pick only n elements
    * If we don't ignore the output ArrayList of Subsets into Space complexity, SC will be O(2^n) + O(n)
    * Space Complexity:  O(2^n) + O(n) to store every subset sum
    */
    public static ArrayList<Integer> subsetSum_V1(int[] arr) {
        ArrayList<Integer> subsetSum = new ArrayList<>();
        getAllSubsetSum(0, arr, subsetSum, 0);

        // to return all the subset sums in sorted order
        Collections.sort(subsetSum);
        return subsetSum;
    }

    private static void getAllSubsetSum(int index, int[] arr, ArrayList<Integer> list, int sum){
        if (index == arr.length){
            list.add(sum);
            return;
        }
        // Not pick
        getAllSubsetSum(index + 1, arr, list, sum);

        // Pick
        getAllSubsetSum(index + 1, arr, list, sum + arr[index]);
    }



    /*
     ***************************** Approach 2 : Construct Subset Size by Size *************************
     * TC -> O(2^n)      Generating all possible subsets-sums that are 2^n in numbers
     * Ignoring the output ArrayList of Subsets (that are 2^n in number) into Space complexity

     * SC -> O(n)        At most n recursive calls, as we can either pick/not-pick only n elements
     * If we don't ignore the output ArrayList of Subsets into Space complexity, SC will be O(2^n) + O(n)
     * Space Complexity:  O(2^n) + O(n) to store every subset's sum
     */
    public static ArrayList<Integer> subsetSum_V2(int[] arr) {
        ArrayList<Integer> subsetSum = new ArrayList<>();
        getAllSubsetSums(0, arr, subsetSum, 0);

        // to return all the subset sums in sorted order
        Collections.sort(subsetSum);
        return subsetSum;
    }

    private static void getAllSubsetSums(int index, int[] arr, ArrayList<Integer> list, int sum){
        list.add(sum);

        for (int i = index; i < arr.length; i++){
            getAllSubsetSums(i + 1, arr, list, sum + arr[i]);
        }
    }
}
