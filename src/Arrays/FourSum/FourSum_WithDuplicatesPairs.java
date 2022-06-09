package Arrays.FourSum;
import java.util.ArrayList;
import java.util.List;

// https://youtu.be/4ggF3tXIAp0
// https://takeuforward.org/data-structure/4-sum-find-quads-that-add-up-to-a-target-value/

public class FourSum_WithDuplicatesPairs {

    // ********************************** Brute Force *******************************************
    // TC -> O(n^4)
    // SC - > O(1)
    private List<List<Integer>> fourSum_BruteForce(int[] arr, int target) {
        int n = arr.length;
        List<List<Integer>> allQuadruplets = new ArrayList<>();

        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                for (int k = j + 1; k < n; k++)
                    for (int l = k + 1; l < n; l++)
                        if (arr[i] + arr[j] + arr[k] + arr[l] == target){
                            ArrayList<Integer> currQuadruple = new ArrayList<>();
                            currQuadruple.add(arr[i]);
                            currQuadruple.add(arr[j]);
                            currQuadruple.add(arr[k]);
                            currQuadruple.add(arr[l]);

                            allQuadruplets.add(currQuadruple);
                        }
        return allQuadruplets;
    }



    // **************************** Efficient Solution ****************************************888
    // TC -> O(n^3)
    // SC -> O(1)
    // This problem is combination of problems 'Three sum' & 'Two sum: Input array is sorted'

    private List<List<Integer>> fourSum(int[] arr, int target) {
        int n = arr.length;
        List<List<Integer>> allQuadruplets = new ArrayList<>();

        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++){

                // Here the problem 'Two Sum : Input Array is Sorted starts'
                int low = j + 1;
                int high = n - 1;

                while (low < high){
                    if (arr[i] + arr[j] + arr[low] + arr[high] == target){
                        ArrayList<Integer> currQuadruple = new ArrayList<>();

                        currQuadruple.add(arr[i]);
                        currQuadruple.add(arr[j]);
                        currQuadruple.add(arr[low]);
                        currQuadruple.add(arr[high]);
                        allQuadruplets.add(currQuadruple);

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
        return allQuadruplets;
    }
}
