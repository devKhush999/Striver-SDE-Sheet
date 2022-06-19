package RecursionAndBacktracking.CombinationSum_I;
import java.util.ArrayList;
import java.util.List;

// Pre-req --> Sub-Sequence of the array
// https://youtu.be/OyZFFqQtu98
// https://takeuforward.org/data-structure/combination-sum-1/

/*
If we add one OR condition in the base condition as if(ind == arr.size() || target == 0),
keeping rest of the code same, we get the right answer but in the less recursive calls.
Because sometimes we get the target before reaching the last index, so once we get the target
we need to stop at that moment and no need to check for further elements. As it's OR, once the
base condition is satisfied, again inside that we need to check whether (target == 0) and confirm.

NOTE: THIS IDEA WILL WORK ONLY WHEN THE ELEMENT OF THE ARRAY ARE POSITIVE, IN CASE OF NEGATIVE ELEMENTS OR ZEROS
ELEMENTS, THIS IDEA WILL NOT WORK. (and hence "target == 0" must be moved inside the base case 'if' condition)
 */

/*
Time Complexity: O(2^t * k)     where t is the target, k is the average length of each subset
Reason: Assume if you were not allowed to pick a single element multiple times,
every element will have a couple of options: 'pick' or 'not pick' which is 2^n different recursion calls,
also assuming that the average length of every combination generated is k.
(to put length k data structure into another data structure)
Why not (2^n) but (2^t) (where n is the size of an array)?
Assume that there is 1 and the target you want to reach is 10.
So, 10 times you can “pick or not pick” an element.

Space Complexity: O(k*x), k is the average length of each combination and x is the no. of combinations
 */

public class CombinationSum_I {
    public static List<List<Integer>> combinationSum(int[] arr, int target) {
        // ArrayList to store the subsets that sum to k.
        List<List<Integer>> allCombinations = new ArrayList<>();

        combinationSum(0, target, arr, new ArrayList<>(), allCombinations);
        return allCombinations;
    }

    public static void combinationSum(int i, int target, int[] arr, ArrayList<Integer> list, List<List<Integer>> answer){
        if (target == 0 || i == arr.length){
            if (target == 0)
                answer.add(new ArrayList<>(list));
            return;
        }

        // Picking the current element AGAIN into the subset only if the arr[index] is less than the target Sum,
        // if it is greater than target sum, then we shouldn't consider & call recursion on same element
        if (target >= arr[i]){
            list.add(arr[i]);
            combinationSum(i, target - arr[i], arr, list, answer);
            list.remove(list.size() - 1);
        }

        // Not Picking the current element into the subset & moving to next index
        combinationSum(i + 1, target, arr, list, answer);
    }
}
