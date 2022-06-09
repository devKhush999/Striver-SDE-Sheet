package Arrays.ThreeSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    // ************************************ Brute Force **************************************
    // TC -> O(n^3)             (only unique triplets & ano duplicates)
    // SC -> O(1)

    public List<List<Integer>> threeSum(int[] arr) {
        int n = arr.length;
        int sum = 0;
        Arrays.sort(arr);

        List<List<Integer>> allTriplets = new ArrayList<>();

        for (int i = 0; i < n; i++){
            // skip duplicates
            if (i > 0 && arr[i] == arr[i-1])
                continue;

            for (int j = i + 1; j < n; j++){
                // skip duplicates
                if (j > i + 1 && arr[j] == arr[j-1])
                    continue;

                for (int k = j + 1; k < n; k++){
                    // skip duplicates
                    if (k > j + 1 && arr[k] == arr[k-1])
                        continue;

                    if (arr[i] + arr[j] + arr[k] == sum){
                        ArrayList<Integer> currTriplet = new ArrayList<>();
                        currTriplet.add(arr[i]);
                        currTriplet.add(arr[j]);
                        currTriplet.add(arr[k]);

                        allTriplets.add(currTriplet);
                    }

                }
            }
        }
        return allTriplets;
    }
}
