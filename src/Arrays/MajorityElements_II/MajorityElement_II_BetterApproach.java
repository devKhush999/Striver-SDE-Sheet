package Arrays.MajorityElements_II;

import java.util.ArrayList;
import java.util.List;

// https://youtu.be/yDbkQd9t2ig
// https://leetcode.com/problems/majority-element-ii/
// https://takeuforward.org/data-structure/majority-elementsn-3-times-find-the-elements-that-appears-more-than-n-3-times-in-the-array/

public class MajorityElement_II_BetterApproach {

    // ******************************* Fastest Approach ********************************************
    // Bayer-Moore Voting Algo   (works only when frequency of majority element is > n/2)
    // At max. we can have 2 majority element (with frequency more than n/3)
    // and at min. we can have 0 majority elements
    // Here, we are not given that these two majority element will actually exist, so we need to check

    // TC --> O(n)
    // SC --> O(1)

    public List<Integer> majorityElement(int[] arr) {
        int n = arr.length;

        int majorElement1 = 0, count1 = 0;
        int majorElement2 = 0, count2 = 0;

        for (int num : arr){
            // Same majorElement1 -> Increase Frequency by 1
            if (num == majorElement1)
                count1++;

            // Same majorElement2 -> Increase Frequency by 1
            else if (num == majorElement2)
                count2++;

            // New majorElement1 -> Increase frequency from 0->1
            else if (count1 == 0){
                majorElement1 = num;
                count1 = 1;
            }

            // New majorElement2 -> Increase frequency from 0->1
            else if (count2 == 0){
                majorElement2 = num;
                count2 = 1;
            }

            // Some Diff. Element encountered --> Decrease Frequencies by 1
            else{
                count1--;
                count2--;
            }
        }


        // Checking whether these majority elements given by Moore voting algo actually are occurring
        // more than n/3 times, as we are not told in this ques that both the two majority elements will exist
        int majorElement1_Count = 0;
        int majorElement2_Count = 0;
        for (int num : arr){
            if (num == majorElement1)
                majorElement1_Count++;

            else if (num == majorElement2)
                majorElement2_Count++;
        }

        List<Integer> allMajorElements = new ArrayList<>();

        if (majorElement1_Count > n/3)
            allMajorElements.add(majorElement1);

        if (majorElement2_Count > n/3)
            allMajorElements.add(majorElement2);

        return allMajorElements;
    }
}
