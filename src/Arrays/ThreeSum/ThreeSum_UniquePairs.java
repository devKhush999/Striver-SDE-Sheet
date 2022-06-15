package Arrays.ThreeSum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://youtu.be/onLoX6Nhvmg
// https://takeuforward.org/data-structure/3-sum-find-triplets-that-add-up-to-a-zero/

public class ThreeSum_UniquePairs {

    // ************************************ Brute Force **************************************
    // TC -> O(n^3) + O(n * log(n))            (only unique triplets & ano duplicates)
    // SC -> O(1)

    public List<List<Integer>> threeSum_BruteForce(int[] arr, int sum) {
        int n = arr.length;
        List<List<Integer>> allTriplets = new ArrayList<>();

        // We need to sort array in case of unique pairs, though sorting wasn't necessary in
        // case of duplicate pairs. But as we check for uniqueness we require array to be sorted
        Arrays.sort(arr);

        for (int i = 0; i < n; i++){
            if (i > 0  && arr[i] == arr[i-1])               // skip duplicates
                continue;

            for (int j = i + 1; j < n; j++){
                if (j > i+1  &&  arr[j] == arr[j-1])        // skip duplicates
                    continue;

                for (int k = j + 1; k < n; k++){
                    if (k > j+1  && arr[k] == arr[k-1])     // skip duplicates
                        continue;

                    if (arr[i] + arr[j] + arr[k] == sum){
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(arr[i]);
                        list.add(arr[j]);
                        list.add(arr[k]);
                        allTriplets.add(list);
                    }
                }
            }
        }
        return allTriplets;
    }


    // **************************** Efficient Solution ****************************************888
    // TC -> O(n^2) + O(n * log(n))
    // SC -> O(1)
    // This problem is combination of problem 'Two sum: Input array is sorted'

    private List<List<Integer>> threeSum_Optimized(int[] arr, int sum){
        // sorting is a must to avoid duplicates
        Arrays.sort(arr);

        int n = arr.length;
        List<List<Integer>> allTriplets = new ArrayList<>();

        for (int i = 0; i < n; i++){
            if (i > 0 && arr[i] == arr[i-1])        // skip duplicates
                continue;

            // Here the problem 'Two Sum : Input Array is Sorted starts'
            int low = i + 1, high = n-1;

            while (low < high){
                if (arr[i] + arr[low] + arr[high] == sum){
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(arr[i]);
                    list.add(arr[low]);
                    list.add(arr[high]);
                    allTriplets.add(list);

                    // We have to move low & high pointers, as the next target sum given by arr[low]
                    // & arr[high] may exits on after 'low' index & before 'high' index
                    low++;
                    high--;

                    while (low < high  && arr[low] == arr[low - 1])
                        low++;
                    while (high > low  &&  arr[high] == arr[high + 1])
                        high--;
                }
                else if (arr[i] + arr[low] + arr[high] > sum)
                    high--;
                else if (arr[i] + arr[low] + arr[high] < sum)
                    low++;
            }
        }
        return allTriplets;
    }

}
