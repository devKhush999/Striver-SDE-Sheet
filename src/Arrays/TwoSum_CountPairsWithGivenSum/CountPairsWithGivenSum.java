package Arrays.TwoSum_CountPairsWithGivenSum;
import java.util.HashMap;

// https://www.geeksforgeeks.org/count-pairs-with-given-sum/

public class CountPairsWithGivenSum {

    // ****************************** Efficient HashMap Solution *************************************
    // TC -> O(n)
    // SC -> O(n)

    int getPairsCount(int[] arr, int n, int sum) {
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int val : arr){
            if (map.containsKey(sum - val))
                count += map.get(sum - val);

            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        return count;
    }



    // ********************************** BruteForce ********************************************
    // Counting all pairs with for loops
    // TC -> O(n*n)
    // SC -> O(1)

    int getPairsCount_BruteForce(int[] arr, int n, int sum) {
        int count = 0;

        for (int i = 0; i < n; i++)
            for (int j = i+1; j < n; j++)
                if (arr[i] + arr[j] == sum)
                    count++;

        return count;
    }
}
