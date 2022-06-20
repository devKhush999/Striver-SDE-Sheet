package RecursionAndBacktracking.KthPermutationSequence;

import java.util.ArrayList;

public class KthPermutationSequence_BruteForce {
     /*
     ********************** Approach 1 : Brute Force Generate all possible Permutation ********************
     * Generate all possible permutations of array
     * And return th kth permutation from the list
     * TC -> O(n!) + O(k)  Generating all permutations (n by n Permutation) that are "n!" in numbers
     * O(k) for getting kth permutation sequence by traversal

     * SC -> O(n * n!) + O(n) + O(n)
     * One O(n) for visited array. Another O(n) for Recursion stack space
     * O(n * n!) for ArrayList<String> that stores all the 'n!' permutations each of which is of size 'n'
     * ArrayList<String> that stores all the 'n!' permutations is not the output list
     */

    public String getPermutation(int n, int k) {
        ArrayList<String> allPermutation = new ArrayList<>();
        boolean[] visited = new boolean[n + 1];

        getAllPermutation(n, 0, "", visited, allPermutation);
        return allPermutation.get(k - 1);
    }

    public void getAllPermutation(int n, int elementPicked, String permutation, boolean[] visited, ArrayList<String> answer){
        if (elementPicked == n){
            answer.add(permutation);
            return;
        }
        for (int i = 1; i <= n; i++){
            if (!visited[i]){
                visited[i] = true;
                getAllPermutation(n, elementPicked + 1,  permutation + i, visited, answer);
                visited[i] = false;
            }
        }
    }
}
