package BinarySearch.MedianInRowWiseSortedMatrix;

import java.util.Arrays;

public class MedianInRowWiseSortedMatrix {
    /*
    ******************************** Brute Force ********************************
    * Time Complexity: O(m * n) + O((m * n) * log(m * n))   =  O((m * n) * log(m * n))
    * O(m * n) for copying the Array's element into a Linear Array
    * For sorting the Linear Array where 'm * n' denotes the number of elements in the linear array.

    * Space Complexity: O(m * n)  for storing elements in the linear array

    * Note: This method will work for both the case when number of element in the array
      is "ODD" and "EVEN"
    */
    public static int findMedian(int[][] arr, int m, int n) {
        int totalElements = m * n;
        int[] matrix = new int[totalElements];

        // Copying Array
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                matrix[n * i + j] = arr[i][j];

        // Sorting to find median of array
        Arrays.sort(matrix);

        // Finding Median acc. to Formulae
        if ((m * n) % 2 == 1)
            return matrix[(m*n) / 2];
        else
            return (matrix[totalElements/2] + matrix[totalElements/2 - 1]) / 2;
    }
}
