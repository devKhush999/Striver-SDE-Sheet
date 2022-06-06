package Arrays.NextPermutation;

// All the permutations of the (sorted) array in lexicographically greater (dictionary) order can
// be made by boolean visited array approach, that approach makes all the permutation of sorted array
// in lexicographically greater order

// https://youtu.be/LuLCLgMElus
// https://takeuforward.org/data-structure/next_permutation-find-next-lexicographically-greater-permutation/

// For intuition watch VIDEO

public class NextPermutation {

    public void nextPermutation(int[] arr) {
        int n = arr.length;

        // Element in array with breakdown index
        // Linearly traverse array from backward such that ith index value of the array is less than (i+1)th index value
        // 'i' is the index which is the start of the increasing sequence in backward direction.
        int breakPointIndex = -1;

        for (int i = n-2; i >= 0; i--)
            if (arr[i] < arr[i+1]){
                breakPointIndex = i;
                break;
            }

        if (breakPointIndex == -1){
            reverse(0, n-1, arr);
            return;
        }

        // Element in array just greater than element at the breakdown index
        int justGreaterThanBreakDownIndex = -1;

        for (int i = n-1; i >= 0; i--)
            if (arr[i] > arr[breakPointIndex]){
                justGreaterThanBreakDownIndex = i;
                break;
            }

        // Swapping & reversing
        this.swap(justGreaterThanBreakDownIndex, breakPointIndex, arr);

        this.reverse(breakPointIndex + 1, n - 1, arr);
    }



    // ******************************** Another Code *************************************
    public void nextPermutation_(int[] arr) {

        // Element in array with breakdown index 'i'
        int i = arr.length - 2;

        // Linearly traverse array from backward such that ith index value of the array is less than (i+1)th index value
        // 'i' is the index which is the start of the increasing sequence in backward direction.
        while(i >= 0 && arr[i] >= arr[i + 1])
            i--;

        if(i >= 0) {

            // Find an index that has a value greater than the breakpoint index 'i'
            int j = arr.length - 1;
            while(arr[j] <= arr[i]) j--;

            swap(i, j, arr);
        }

        reverse(i + 1, arr.length - 1, arr);
    }


    // Simple swapping code
    private void swap(int i, int j, int[] arr){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Simple array reversal code
    private void reverse(int low, int high, int[] arr) {
        while (low < high)
            swap(low++, high--, arr);
    }
}
