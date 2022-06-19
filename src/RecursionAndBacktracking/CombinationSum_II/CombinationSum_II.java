package RecursionAndBacktracking.CombinationSum_II;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://youtu.be/G1fRTGRxXU8
// https://takeuforward.org/data-structure/combination-sum-ii-find-all-unique-combinations/

/*
Time Complexity:  O(n*log(n)) + O(2^n * k)   ~  O(2^n * k)
Reason: Assume if all the elements in the array are unique then the no. of subsequence we will get
(will be made by recursive calls) will be O(2^n). We also add the combination (whose sum is target)
to our "allUniqueCombinations" ArrayList when we reach the base case that will take O(k) time
k is the average length of the combination

Space Complexity:O(k*x)
Reason: if we have x combinations then space will be x*k where k is the average length of the combination.
 */

public class CombinationSum_II {
    public List<List<Integer>> combinationSum2(int[] arr, int target) {
        // Sorting of array is a must to avoid duplicate
        Arrays.sort(arr);

        // ArrayList to store all the unique subsets that sum to k.
        List<List<Integer>> allUniqueCombinations = new ArrayList<>();

        getAllUniqueCombinations(0, arr, target, allUniqueCombinations, new ArrayList<>());
        return allUniqueCombinations;
    }

    private void getAllUniqueCombinations(int index, int[] arr, int target, List<List<Integer>> answer, ArrayList<Integer> list){
        // Base case
        if (target == 0)
            answer.add(new ArrayList<>(list));

        for (int i = index; i < arr.length; i++){
            if (i > index && arr[i] == arr[i-1])        // Skip duplicates
                continue;

            // This also be done instead of below "if" condition, we break the entire loop
            // because if current element is greater than 'target', all next elements will be greater too (as array is sorted)
            // if (arr[i] > target)  break;

            // If current element is <= than target, only then consider it into our subset
            if (arr[i] <= target){
                list.add(arr[i]);
                getAllUniqueCombinations(i + 1, arr, target - arr[i], answer, list);
                list.remove(list.size() - 1);
            }
        }
    }

}
