package RecursionAndBacktracking.CombinationSum_I;
import java.util.ArrayList;

// https://www.codingninjas.com/codestudio/problems/759331?
// Similar problem to find all the Subsets with Sum 'target'

public class AllSubsetWithSumK {
    public static ArrayList<ArrayList<Integer>> findSubsetsThatSumToK(ArrayList<Integer> arr, int n, int k) {
        // ArrayList to store the subsets that sum to k.
        ArrayList<ArrayList<Integer>> allCombinations = new ArrayList<>();

        combinationSum(0, k, arr, new ArrayList<>(), allCombinations);
        return allCombinations;
    }

    public static void combinationSum(int i, int target, ArrayList<Integer> arr, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> answer){
        // Base case, Here target is not kept in OR Condition because it will not work if the target
        // sum is itself "zero"
        if (i == arr.size()){
            if (target == 0)
                answer.add(new ArrayList<>(list));
            return;
        }

        // Case when we include the current element in the subset.
        list.add(arr.get(i));
        combinationSum(i + 1, target - arr.get(i), arr, list, answer);
        list.remove(list.size() - 1);

        // Case when we do not include the current element in the subset.
        combinationSum(i + 1, target, arr, list, answer);
    }
}
