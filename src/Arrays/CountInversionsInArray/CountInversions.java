package Arrays.CountInversionsInArray;

// Logic is same as Mergesort:

// https://practice.geeksforgeeks.org/problems/inversion-of-array-1587115620/1/
// https://www.geeksforgeeks.org/counting-inversions/
// https://youtu.be/kQ1mJlwW-c0
// https://takeuforward.org/data-structure/count-inversions-in-an-array/

public class CountInversions {

    // ***************************** Using Merge Sort *****************************************
    // T.C --> O(n * log(n))
    // S.C --> O(n)

    public long getInversions(long[] arr, int n) {
        return mergeSortAndCountInversions(arr, 0, n-1, new long[n]);
    }

    public  long mergeSortAndCountInversions(long[] arr, int low, int high, long[] temp){

        if (low < high){
            int mid = (low + high)/2;
            long leftInversions = mergeSortAndCountInversions(arr, low, mid, temp);
            long rightInversions = mergeSortAndCountInversions(arr, mid+1, high, temp);
            long splitInversions = merge(arr, low, mid, high, temp);
            return leftInversions + rightInversions + splitInversions;
        }
        return 0;
    }

    public long merge(long[] arr, int low, int mid, int high, long[] temp){

        int i = low, j = mid + 1, k = low;
        long inversions = 0;

        while (i <= mid && j <= high){
            // if current element in left half is <= current element in right half, then there is no inversions
            if (arr[i] <= arr[j])
                temp[k++]  =  arr[i++];

            // if current element in left half is > current element in right half, then there are inversions
            // these inversions are equal in "mid - i + 1" in numbers (Do dry run!)
            else{
                temp[k++] = arr[j++];
                inversions += (mid  + 1 - i);
            }
        }

        while(i <= mid)
            temp[k++] = arr[i++];

        while (j <= high)
            temp[k++] = arr[j++];

        for (int a = low; a <= high; a++)
            arr[a] = temp[a];

        return inversions;
    }


    // ***************************** Brute Force *****************************************
    // T.C --> O(n * n)
    // S.C --> O(1)

    private long getInversion_BruteForce(int[] arr){
        long inversionsCount = 0;

        for (int i = 0; i < arr.length - 1; i++){
            for (int j = i + 1; j < arr.length; j++){
                if (arr[i] > arr[j])
                    inversionsCount++;
            }
        }
        return inversionsCount;
    }

}
