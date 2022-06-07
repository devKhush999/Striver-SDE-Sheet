package Arrays.MajorityElement;

// See this video for "INTUITION" (Must)
// https://youtu.be/AoX3BPWNnoE
// https://takeuforward.org/data-structure/find-the-majority-element-that-occurs-more-than-n-2-times/
// https://leetcode.com/problems/majority-element/
// https://www.codingninjas.com/codestudio/problems/842495?topList=striver-sde-sheet-problems&utm_source=striver&utm_medium=website

public class MajorityElement_BetterApproach {

    // *********************************** Fastest Approach **********************************8**
    // Here, in this approach we actually neglect the part of array (sub-array) where 'count' of
    // majority element becomes 0
    // Bayer-Moore Voting Algo   (works only when frequency of majority element is > n/2)
    // TC: O(n)
    // SC: O(1)

    public int majorityElement(int[] arr) {
        int majorityElement = 0;
        int count = 0;

        for (int num : arr){
            // New Element -> Increase frequency from 0->1 in next if condition
            if (count == 0)
                majorityElement = num;

            // Same Element OR new majority element -> Increase Frequency by 1
            if (num == majorityElement)
                count++;

            // Diff Element --> Decrease Frequency by 1
            else
                count--;
        }
        return majorityElement;
    }



    // *********************************************************************************************
    // Another version of Majority Element By Coding-ninjas
    // Since Moore Voting ALog fails when frequency of majority element is not > n/2
    // So we need to check whether the majority element given by moore voting algo is actually the
    // majority element (occurring more than n/2 times), that will take another O(n) time
    // TC: O(n)
    // SC: O(1)

    // https://www.codingninjas.com/codestudio/problems/842495?topList=striver-sde-sheet-problems&utm_source=striver&utm_medium=website

    public int majorityElement_CodingNinjas(int[] arr) {
        int n = arr.length;
        int majorElement = 0;
        int count = 0;

        for (int num : arr) {
            // New Element -> Increase frequency from 0->1
            if (count == 0) {
                count++;
                majorElement = num;
            }

            // Same Element -> Increase Frequency by 1
            else if (num == majorElement)
                count++;

            // Diff Element --> Decrease Frequency by 1
            else
                count--;
        }

        int majorityElementCount = 0;

        for (int num : arr)
            if (num == majorElement)
                majorityElementCount++;

        return majorityElementCount > n/2 ? majorElement : -1;
    }
}
