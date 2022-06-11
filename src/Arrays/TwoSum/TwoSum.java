package Arrays.TwoSum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// https://www.geeksforgeeks.org/given-an-array-a-and-a-number-x-check-for-pair-in-a-with-sum-as-x/
// https://youtu.be/dRUpbt8vHpo
// https://takeuforward.org/data-structure/two-sum-check-if-a-pair-with-given-sum-exists-in-array/
// https://leetcode.com/problems/two-sum/

// Another variant of same problem: Return all possible to sum pairs
// https://www.codingninjas.com/codestudio/problems/pair-sum_697295?topList=striver-sde-sheet-problems&utm_source=striver&utm_medium=website&leftPanelTab=0

public class TwoSum {

    // **************************************** Brute Force ****************************************
    // TC -> O(n * n)
    // SC -> O(1)

    public int[] twoSum_BruteForce(int[] arr, int target) {

        for (int i = 0; i < arr.length; i++)
            for (int j = i+1; j < arr.length; j++)
                if (arr[i] + arr[j] == target){
                    int[] answer = new int[]{i, j};
                    return answer;
                }

        return arr;
    }



    // **************************** Efficient Solution using HashMap ****************************
    // TC -> O(n)
    // SC -> O(n)

    public int[] twoSum_HashMapSolution(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] answer = new int[2];

        for (int i=0; i<nums.length; i++){
            if (map.containsKey(target - nums[i])){
                answer[0] = map.get(target - nums[i]);
                answer[1] = i;
                return answer;
            }
            map.put(nums[i], i);
        }
        return nums;
    }


    // https://www.codingninjas.com/codestudio/problems/pair-sum_697295?topList=striver-sde-sheet-problems&utm_source=striver&utm_medium=website&leftPanelTab=0

    // ************* For generating all Possible two sum pairs containing duplicates *************
    // TC -> O(n * log(n))
    // SC -> O(n)
    public int[][] twoSum_HashMap_AllPairs(int[] arr, int sum) {

        // Sorting the list as we need to return the sorted key & values pairs
        // This is done to make both the values in the pair sum (constituting the two sum) sorted
        Arrays.sort(arr);

        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<int[]> allTwoSumList = new ArrayList<>();

        for (int i=0; i<arr.length; i++){

            if (map.containsKey(sum - arr[i])){
                int timesOfOccurrence = map.get(sum - arr[i]);

                for (int j = 0; j < timesOfOccurrence; j++){
                    allTwoSumList.add(new int[]{sum - arr[i], arr[i]});
                }
            }

            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        // Sorting the all Two sum pairs in increasing orders for eg [[-2, 2], [-3, 2], [0, 2]]
        // allTwoSumList.sort();             // This also works
        allTwoSumList.sort((a, b) -> (a[0] - b[0]));

        int[][] allTwoSumPairs = new int[allTwoSumList.size()][2];
        return allTwoSumList.toArray(allTwoSumPairs);
    }
}




/*
Python Code for generating all possible Two sum pairs, here we need to store the count of all elements:

def pairSum(arr, sum):
    all_pairs = []
    map = {}
    # To print in sorted fashion
    arr.sort()

    for i in range (len(arr)):
        if map.get(sum - arr[i], -1) != -1:

            for j in range(map[sum - arr[i]]):
                all_pairs.append([sum - arr[i], arr[i]])

        # Ternary operator
        # map[arr[i]] =  1 if map.get(arr[i], -1) == -1 else map[arr[i]] +1
        map[arr[i]] =  map.get(arr[i], 0) + 1

    all_pairs.sort()
    return all_pairs


'''
    a=[]
    arr.sort()
    for i in range(len(arr)):
        for j in range(i+1,len(arr)):
            if arr[i]+arr[j]==sum:
                a.append([arr[i], arr[j]])
    return
'''
 */