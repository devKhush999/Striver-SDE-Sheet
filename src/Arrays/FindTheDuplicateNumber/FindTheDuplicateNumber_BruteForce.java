package Arrays.FindTheDuplicateNumber;
import java.util.Arrays;
import java.util.HashMap;

// https://youtu.be/32Ll35mhWg0
// https://takeuforward.org/data-structure/find-the-duplicate-in-an-array-of-n1-integers/

public class FindTheDuplicateNumber_BruteForce {

    // *********************************** Brute Force 1 ***********************************
    // T.C --> O(n)
    // S.C --> O(n)
    public int findDuplicate_HashMapCount(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();

        for (int num : nums){
            if (count.containsKey(num))
                return num;

            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        return -1;
    }



    // *********************************** Brute Force 2 ***********************************
    // T.C --> O(n)
    // S.C --> O(n)
    // Always go for array count if possible, counts using are faster (Static memory allocation)
    public int findDuplicate_ArrayCount(int[] arr) {
        int n = arr.length;
        int[] count = new int[n];

        for (int i = 0; i < n; i++){
            count[arr[i]]++;

            if (count[arr[i]] > 1)
                return arr[i];
        }
        return -1;
    }



    // *********************************** Brute Force 3 ***********************************
    // By sorting the array
    // T.C --> O(n.log(n) + n)
    // S.C --> O(n)
    public int findDuplicate_UsingSorting(int[] nums) {
        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++){
            if (nums[i] == nums[i-1])
                return nums[i];
        }

        return -1;
    }
}
