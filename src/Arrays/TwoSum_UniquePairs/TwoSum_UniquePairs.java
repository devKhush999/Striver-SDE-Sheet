package Arrays.TwoSum_UniquePairs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

// https://www.geeksforgeeks.org/count-distinct-pairs-with-given-sum/
// PRE_REQUISITE: TWO SUM
// PRE_REQUISITE: TWO SUM: INPUT ARRAY IS SORTED

public class TwoSum_UniquePairs {
    /*
    Time Complexity : O(n * log(n))
    Space Complexity: O(1)
     */
    static List<List<Integer>> numberOfPairs_Sorting(int[] arr, long k) {
        Arrays.sort(arr);

        // Number of Two Sum unique pairs
        int uniqueTwoSumPair = 0;
        List<List<Integer>> uniqueTwoSumPairsElements = new ArrayList<>();

        int low = 0, high = arr.length - 1;

        while (low < high){
            if (arr[low] + arr[high] == k){
                uniqueTwoSumPair++;

                ArrayList<Integer> twoSumPair = new ArrayList<>();
                twoSumPair.add(arr[low]);
                twoSumPair.add(arr[high]);
                uniqueTwoSumPairsElements.add(twoSumPair);
                low++;
                high--;

                while (low < high  && arr[low - 1] == arr[low])         // Skip Duplicates
                    low++;
                while (high > low  &&  arr[high] == arr[high + 1])      // Skip Duplicates
                    high--;
            }
            else if (arr[low] + arr[high] > k)
                high--;
            else
                low++;
        }
        return uniqueTwoSumPairsElements;
    }


    /*
    Time Complexity : O(n)
    Space Complexity: O(n)
     */
    static int numberOfPairs_Hashing(int[] a, int k) {
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> visitedNumbers = new HashSet<>();
        int count = 0;

        for (int val : a){
            if (set.contains(k - val) && !visitedNumbers.contains(val)){
                count++;
                visitedNumbers.add(val);
                visitedNumbers.add(k - val);
            }
            set.add(val);
        }
        return count;
    }
}
