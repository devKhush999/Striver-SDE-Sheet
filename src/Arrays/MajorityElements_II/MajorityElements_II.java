package Arrays.MajorityElements_II;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// https://youtu.be/yDbkQd9t2ig
// https://leetcode.com/problems/majority-element-ii/
// https://takeuforward.org/data-structure/majority-elementsn-3-times-find-the-elements-that-appears-more-than-n-3-times-in-the-array/

public class MajorityElements_II {

    // ******************************* Brute Force Solution *********************************
    // TC -> O(n*n)
    // SC -> O(n/3) in worst case = O(n)
    public List<Integer> majorityElement_BruteForce(int[] arr) {
        int n = arr.length;
        List<Integer> majorityElements = new ArrayList<>();

        for (int i = 0; i < n; i++){
            int currCount = 0;
            for (int j = i; j < n; j++){
                if (arr[i] == arr[j])
                    currCount++;
            }

            // list.contains() also takes O(n) time in worst case
            if (currCount > n/3  &&  !majorityElements.contains(arr[i]))
                majorityElements.add(arr[i]);
        }

        return majorityElements;
    }



    // ******************************* Brute Force HashMap Solution *********************************
    // TC -> O(n)
    // SC -> O(n)
    public List<Integer> majorityElement_HashMapSolution(int[] arr) {
        int n = arr.length;
        HashMap<Integer, Integer> count = new HashMap<>();
        List<Integer> majorityElements = new ArrayList<>();

        for (int num : arr)
            count.put(num, count.getOrDefault(num, 0) + 1);

        for (int num : count.keySet())
            if (count.get(num) > n/3)
                majorityElements.add(num);

        return majorityElements;
    }
}
