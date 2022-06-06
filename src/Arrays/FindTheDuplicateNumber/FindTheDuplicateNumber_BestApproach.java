package Arrays.FindTheDuplicateNumber;

// https://youtu.be/32Ll35mhWg0
// https://takeuforward.org/data-structure/find-the-duplicate-in-an-array-of-n1-integers/

public class FindTheDuplicateNumber_BestApproach {

    // ********************************  Best Approach *******************************
    // T.C --> O(n)
    // S.C --> O(1)
    // Approach is similar that is used in linked list
    // Since, there is a duplicate number in the range [1, N] with array length N+1
    // Therefore a cycle will always exits in the array

    public int findDuplicate(int[] arr){
        int slowPtr = arr[0];
        int fastPtr = arr[0];

        do {

            fastPtr = arr[arr[fastPtr]];
            slowPtr = arr[slowPtr];

        } while (fastPtr != slowPtr);

        fastPtr = arr[0];

        while (fastPtr != slowPtr){
            fastPtr = arr[fastPtr];
            slowPtr = arr[slowPtr];
        }
        
        return fastPtr;
    }
}
