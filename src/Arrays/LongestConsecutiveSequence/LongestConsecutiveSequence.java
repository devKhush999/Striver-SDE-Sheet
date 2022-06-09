package Arrays.LongestConsecutiveSequence;
import java.util.Arrays;
import java.util.HashSet;

// https://youtu.be/qgizvmgeyUM
// https://takeuforward.org/interviews/strivers-sde-sheet-top-coding-interview-problems/

public class LongestConsecutiveSequence {

    // ***************************************** Brute Force *****************************************
    // TC -> O(n * log(n))  + O(n)
    // SC -> O(1)   (Ignoring merge sorting temp array)

    public int longestConsecutive_BruteForce(int[] arr) {
        int n = arr.length;
        if (n == 0)
            return 0;

        Arrays.sort(arr);

        // This is accounting for 0th index in the array
        int largestStreak = 1;
        int currentStreak = 1;

        for (int i = 1; i < n; i++){
            // if prev & curr element are same, then length of Consecutive Sequence is increased by 1
            if (arr[i] == arr[i-1] + 1)
                currentStreak++;

            // curr & prev elements can be same, in that case length of Consecutive Sequence is not affected
            // else if curr & prev elements are not same, then current Consecutive Sequence is lost,
            // and we start a new Consecutive Sequence from current element
            else if (arr[i] !=  arr[i-1])
                currentStreak = 1;

            largestStreak = Math.max(largestStreak, currentStreak);
        }
        return largestStreak;
    }



    // ******************************* Efficient Solution using Hashing *******************************
    // One O(n) for adding elements into hashSet, second O(n) for traversal in set in worst case (all unique elements)
    // Third O(n) in search of the longest Consecutive Sequence in while loop
    // (one entire array can be the longest streak in worst case)
    // Always REMEMBER: HashSet & HashMap operations are O(1)
    // TC -> O(n) + O(n) + O(n)
    // SC -> O(n)

    public int longestConsecutive_Hashing(int[] arr) {
        int n = arr.length;
        int longestStreak = 0;

        HashSet<Integer> set = new HashSet<>();
        for (int num : arr)
            set.add(num);

        /*
         In 2nd iteration of for loop, we can even traverse over the array instead of 'set'
         However, in that case time complexity will become O(n^2) for 2nd for loop iteration
         Let's take a test case: [3,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1]
         While loop won't execute for 3 & 2 since 1 is present. But while loop will be executed
         for 1 since 0 is not present, and that will be executed (n-2) times here. (Hence, O(n^2) Time complexity)
         Instead of iterating over nums which consists repeated elements, we can iterate over hashset then
         for 1 it will execute while loop only once resulting time complexity O(n) + O(n) + O(n)
         and not quadratic time complexity

         */

        for (int value : set){

            if (!set.contains(value - 1)){
                int currentValue = value;
                int currentStreak = 1;

                while (set.contains(currentValue + 1)){
                    currentStreak++;
                    currentValue++;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }
}
