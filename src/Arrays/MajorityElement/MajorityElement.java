package Arrays.MajorityElement;
import java.util.Arrays;
import java.util.HashMap;

// https://leetcode.com/problems/majority-element/

public class MajorityElement {

    // ********************************* Brute Force *********************************
    // Count the occurrence of each element in the array & decide the majority element
    // T.C --> O(n*n)
    // S.C --> O(1)
    public int majorityElement_BruteForce(int[] arr){
        int n = arr.length;

        for (int i = 0; i < n; i++){
            int currElementCount = 0;

            for (int j = i; j < n; j++)
                if (arr[j] == arr[i])
                    currElementCount++;

            if (currElementCount > n/2)
                return arr[i];
        }
        return -1;
    }


    // ******************************** Sorting Solution 1 ******************************
    // T.C --> O(n.log(n)) + O(1)
    // S.C --> O(n)         Due to sorting
    public int majorityElement_Sorting1(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length/2];
    }


    // ******************************** Sorting Solution 2 ******************************
    // T.C --> O(n.log(n)) + O(n)
    // S.C --> O(n)         Due to sorting
    // Simple Logic to find the majority element in an array (not just limited to n/2)
    public int majorityElement_Sorting2(int[] arr) {
        Arrays.sort(arr);

        int majorElement = arr[0];
        int majorCount = 1;
        int currCount = 1;

        for (int i = 1; i < arr.length; i++){
            if (arr[i] == arr[i-1]){
                currCount++;

                if (currCount > majorCount){
                    majorCount = currCount;
                    majorElement = arr[i];
                }
            }
            else
                currCount = 1;
        }

        // As the majority count is always > n/2, so always majorElement will be returned
        return majorCount > arr.length/2 ? majorElement : -1;
    }


    // ******************************** HashMap Count Solution ******************************
    // T.C --> O(n)
    // S.C --> O(n)         Due to HashMap
    // Simple Logic
    public int majorityElement_HashmapCount(int[] arr) {
        int n = arr.length;
        HashMap<Integer, Integer> count = new HashMap<>();

        for (int value : arr)
            count.put(value, count.getOrDefault(value, 0) + 1);

        for (int valueKey : count.keySet()){
            if (count.get(valueKey) > n/2)
                return valueKey;
        }
        return -1;
    }

}
