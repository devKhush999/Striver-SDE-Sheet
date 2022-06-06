package Arrays.SortArrayOf_012;

// https://youtu.be/oaVa-9wmpns
// https://takeuforward.org/data-structure/sort-an-array-of-0s-1s-and-2s/

public class SortArrayOf_012 {

    //  ******************** Brute Force ==> Counting the number of  0s, 1s & 2s ********************
    // This is also known as Count Sort
    // T.C --> O(n)
    // S.C --> O(1)

    public static void sort012(int[] arr){
        int count0 = 0, count1 = 0;

        for (int val : arr){
            if (val == 0) count0++;
            if (val == 1) count1++;
        }

        int i = 0;
        while (i < count0)  arr[i++] = 0;
        while (i < count0 + count1)  arr[i++] = 1;
        while (i < arr.length)  arr[i++] = 2;
    }


    // ********************************** Approach 2 **********************************
    // Dutch National flag algorithm

    public void sort012_(int[] arr){
        int low = 0, mid = 0, high = arr.length - 1;

        while (mid <= high){
            switch (arr[mid]) {

                case 0 -> {
                    swap(low, mid, arr);
                    low++;
                    mid++;
                }

                case 1 -> mid++;

                case 2 -> {
                    swap(mid, high, arr);
                    high--;
                }
            }
        }

    }

    private void swap(int i, int j, int[] arr){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
