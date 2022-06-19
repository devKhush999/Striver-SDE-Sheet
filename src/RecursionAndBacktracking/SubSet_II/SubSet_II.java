package RecursionAndBacktracking.SubSet_II;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;

// https://youtu.be/RIn3gOkbhQE
// https://takeuforward.org/data-structure/subset-ii-print-all-the-unique-subsets/

public class SubSet_II {
    /*
    **************** Approach 1: Pick & Non-pick by using HashSet to avoid Duplicate ***************
    * We also sort the array to avoid duplicates, this takes O(n*log(n))
    * We generate all 2^n subsets (this takes O(2^n) time) that are of length 'n' im worst case,
      & we also add all the 2^n subsets into a unique-sub-set list. So, this takes O(n * 2^n) time

    * T.C --> O(n*log(n)) + O(2^n) +O(n * 2^n)  ~ O(n * 2^n)
    * HashSet addition takes O(1) time for its all methods

    * Space Complexity:  O(2^n * k) + O(n)  + O(n * 2^n)  ~  O(n * 2^n)
    * O(2^n * k) To store every subset of average length k. (k = n in worst case)
    * Since we are initially using a set to store the answer another O(2^n *k) is also used.
    * O(n) for 'n' recursion stack space (recursive calls).
    * Even if we don't Ignore output ArrayList of Subsets (that are 2^n in number) into Space complexity,
    * then also Space Complexity is O(2^n * k) + O(n) due to HashSet & Recursion calls
    */
    public List<List<Integer>> subsetsWithDuplicate_V1(int[] nums) {
        // By sorting we ensure that every subset inside the all Unique combinations sets are in sorted fashion
        // and hence HashSet can avoid duplicates
        Arrays.sort(nums);
        HashSet<ArrayList<Integer>> allSubsets = new HashSet<>();

        // Finding all the unique subsets
        getAllSubset(0, nums, allSubsets, new ArrayList<>());

        // Shorthand to convert any collection into a new collection type
        return new ArrayList<>(allSubsets);
    }

    public void getAllSubset(int i, int[] arr, HashSet<ArrayList<Integer>> set, ArrayList<Integer> list){
        if (i == arr.length){
            set.add(new ArrayList<>(list));
            return;
        }
        // Picking the current element
        list.add(arr[i]);
        getAllSubset(i + 1, arr, set, list);
        list.remove(list.size() - 1);

        // Not picking the current element
        getAllSubset(i + 1, arr, set, list);
    }


    /*
    ********** Approach 2: By Constructing all the Subsets size-by-size in every step/recursion *********
    * We also sort the array to avoid duplicates, this takes O(n*log(n))
    * We generate all 2^n subsets (this takes O(2^n) time) that are of length 'n' im worst case,
      & we also add all the 2^n subsets into a unique-sub-set list. So, this takes O(n * 2^n) time

    * T.C --> O(n*log(n)) + O(2^n) + O(n * 2^n)  ~ O(n * 2^n)

    * Space Complexity:  O(2^n * k) + O(n)   ~  O(n * 2^n)
    * Space Complexity:  O(2^n * k) + O(n)   ~  O(n)    Ignoring output
    * O(2^n * k) To store every subset of average length k. (k = n in worst case)
    * O(n) for 'n' recursion stack space (recursive calls).
    * If we Ignore output ArrayList of Subsets (that are 2^n in number) into Space complexity,
    * then also Space Complexity is O(n) only due to Recursion calls
    */
    public List<List<Integer>> subsetsWithDup(int[] arr) {
        //By sorting we can avoid duplicates
        Arrays.sort(arr);
        // this will store all the unique subsets
        List<List<Integer>> allUniqueSubsets = new ArrayList<>();

        getAllSubsets(0, arr, allUniqueSubsets, new ArrayList<>());
        return allUniqueSubsets;
    }

    public void getAllSubsets(int index, int[] arr, List<List<Integer>> answer, ArrayList<Integer> list){
        answer.add(new ArrayList<>(list));

        for (int i = index; i < arr.length; i++){
            if (i > index  && arr[i] == arr[i-1])       // skip suplicates
                continue;

            list.add(arr[i]);
            getAllSubsets(i + 1, arr, answer, list);
            list.remove(list.size() - 1);
        }
    }
}
