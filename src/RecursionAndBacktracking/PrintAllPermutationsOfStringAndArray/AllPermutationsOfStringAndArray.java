package RecursionAndBacktracking.PrintAllPermutationsOfStringAndArray;
import java.util.ArrayList;
import java.util.List;

// https://youtu.be/YK78FU5Ffjw
// https://youtu.be/f2ic2Rsc9pU
// https://takeuforward.org/data-structure/print-all-permutations-of-a-string-array/

public class AllPermutationsOfStringAndArray {
    /*
    ************************** Approach 1 : Keeping track of Visited Elements ***************************
    * Time Complexity:  O(n * n!)       for generating all n! permutations (of size 'n) & adding it to answer list
    * Space Complexity:  O(n) + O(n) + O(n!)  =  O(n!)
      Reason: One O(n) for visited boolean array, another O(n) for recursion stack space
      O(n * n!) for output permutation array that has 'n!' permutations each of length 'n'
     */
    public List<List<Integer>> allPermutations_Method1(int[] arr){
        int n = arr.length;
        boolean[] visited = new boolean[n];
        List<List<Integer>> allPermutations = new ArrayList<>();

        getAllPermutations_Method1(0, arr, visited, allPermutations, new ArrayList<>());
        return allPermutations;
    }

    private void getAllPermutations_Method1(int elementsPicked, int[] arr, boolean[] visited, List<List<Integer>> answer, ArrayList<Integer> list){
        if (elementsPicked == arr.length) {
            answer.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < arr.length; i++)
            if (!visited[i]){
                visited[i] = true;
                list.add(arr[i]);
                getAllPermutations_Method1( elementsPicked + 1, arr, visited, answer, list);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
    }



    /*
    ************************** Approach 2 : By Traversal & Swapping ***************************
    * Time Complexity --> O(permutation of array) * O(Adding permutations into arraylist)
    * Time Complexity: --> O(n * n!)
      Reason: O(n * n!) due to generating & adding of all 'n!' permutations of size 'n' into ArrayList

    * Space Complexity --> O(Recursion stack space) + O(Permutations array)
    * Space Complexity --> O(n) +  O(n!) = O(n)    if we ignore permutations array
     */
    public List<List<Integer>> allPermutations_Method2(int[] arr){
        List<List<Integer>> allPermutations = new ArrayList<>();

        getAllPermutations_Method2(0, arr, allPermutations);
        return allPermutations;
    }

    private void getAllPermutations_Method2(int index, int[] arr, List<List<Integer>> answer){
        if (index == arr.length){
            ArrayList<Integer> list = new ArrayList<>();
            for (int val : arr) list.add(val);
            answer.add(list);
            return;
        }

        for (int i = index; i < arr.length; i++){
            swap(index, i, arr);
            getAllPermutations_Method2(index + 1, arr, answer);
            swap(index, i, arr);
        }
    }

    private void swap(int i, int j, int[] arr){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
