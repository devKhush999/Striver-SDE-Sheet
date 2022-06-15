package Arrays.ThreeSum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://youtu.be/onLoX6Nhvmg
// https://takeuforward.org/data-structure/3-sum-find-triplets-that-add-up-to-a-zero/

public class ThreeSum_MadeEfficient {

    // **************************** Efficient Solution ****************************************888
    // TC -> O(n^2) + O(n * log(n))
    // SC -> O(1)
    // This problem is combination of problem 'Two sum: Input array is sorted'

    private List<List<Integer>> threeSum_Optimized(int[] arr, int sum){
        // sorting is a must to avoid duplicates
        Arrays.sort(arr);

        int n = arr.length;
        List<List<Integer>> allTriplets = new ArrayList<>();

        // Loop running from 0 to n-3, i.e, [0, n-3] to account for exactly 2 values after 'ith' index
        for (int i = 0; i <= n-3; i++){
            if (i > 0 && arr[i] == arr[i-1])        // skip duplicates
                continue;

            // If in current 'ith' iteration, first 3 values are greater than target, then break this
            // loop, bcoz after that 'ith' index (i+1, i+2...) it will exceed target too (as array is sorted)
            if (arr[i] + arr[i+1] + arr[i+2] > sum)
                break;
            // If ith value of array & lst 2 values are lesser than target, then skip this index & move
            // to greater 'ith' index in search for target
            if (arr[i] + arr[n-2] + arr[n-1] < sum)
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
