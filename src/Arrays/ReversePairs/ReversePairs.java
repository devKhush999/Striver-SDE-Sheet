package Arrays.ReversePairs;

// https://youtu.be/S6rsAlj_iB4
// https://takeuforward.org/data-structure/count-reverse-pairs/

public class ReversePairs {

    // ************************************ Brute Force ************************************
    // TC -> O(n*n)
    // SC -> O(1)
    public int reversePairs_BruteForce(int[] arr) {
        int n = arr.length;
        int reversePair = 0;

        // We need to add 'l' to 2, as '2*arr[j]' may overflow (out of Integer range) and becomes -ve
        for (int i = 0; i < n-1; i++)
            for (int j = i+1; j < n; j++)
                if (arr[i] > (2L * arr[j]))
                    reversePair++;

        return reversePair;
    }


    // ************************************** Merge Sort ***********************************************
    // Most Efficient Solution using Merge Sort
    // We are doing at most two traversal of array in merge() operation: one for finding reversePairs
    // and one for merging the sorting arrays
    // TC -> O(n * log(n)) + O(n) + O(n)
    // SC -> O(n)
    private int mergeSortAndCountReversePair(int low, int high, int[] arr, int[] temp){
        if (low >= high)
            return 0;

        int mid = (low + high)/2;

        int leftReversePairs = mergeSortAndCountReversePair(low, mid, arr, temp);
        int rightReversePairs = mergeSortAndCountReversePair(mid + 1, high, arr, temp);
        int mergeReversePairs = mergeAndCountReversePair(low, mid, high, arr, temp);

        return leftReversePairs + rightReversePairs + mergeReversePairs;
    }


    private int mergeAndCountReversePair(int low, int mid, int high, int[] arr, int[] temp){
        // Count the number of Reverse Pairs
        int j = mid + 1;
        int reversePairs = 0;

        for(int i = low; i <= mid; i++){
            while (j <= high  &&  arr[i] > 2L * arr[j])
                j++;

            reversePairs += (j - (mid+1));
        }

        // Below code is same as Merge Sort
        int i = low, k = low;
        j = mid + 1;

        while (i <= mid  &&  j <= high){
            if (arr[i] <= arr[j])
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }

        while (i <= mid)
            temp[k++] = arr[i++];

        while (j <= high)
            temp[k++] = arr[j++];

        for (int a = low; a <= high; a++)
            arr[a] = temp[a];

        return reversePairs;
    }


    public int reversePairs(int[] arr) {
        int n = arr.length;
        int reversePairs = mergeSortAndCountReversePair(0, n-1, arr, new int[n]);

        return reversePairs;
    }

}
