package Arrays.RotateImage;

// https://youtu.be/Y72QeX0Efxw
// https://takeuforward.org/data-structure/rotate-image-by-90-degree/
// // https://www.geeksforgeeks.org/rotate-a-matrix-by-90-degree-in-clockwise-direction-without-using-any-extra-space/

public class RotateImage {

    // Note in every approach: 'i' --> ith row  &  'j' --> jth column

    // ************************************ Brute Force ************************************
    // Make a new matrix & store the mirror image in it & then copy it into original matrix
    // T.C. --> O(n*n)
    // S.C. --> O(n*n)
    public void rotate(int[][] matrix) {
        int n =  matrix.length;

        int[][] temp = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                temp[j][n -1 -i] = matrix[i][j];
                // temp[i][j] =  matrix[n -1 -j][i];            // Another way

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = temp[i][j];
    }


    // ********************************** Transpose Approach **********************************
    // T.C. --> O(n*n)
    // S.C. --> O(1)

    public void rotate_90Degrees(int[][] matrix) {
        int n =  matrix.length;

        /// Transposing the matrix
        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++)
                swap(i, j, j, i, matrix);

        // Swapping starting column with ending column in every row
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n/2; j++)
                swap(i, j, i, n - 1 -j, matrix);
    }


    private void swap(int i1, int j1, int i2, int j2, int[][] arr){
        int temp = arr[i1][j1];
        arr[i1][j1] = arr[i2][j2];
        arr[i2][j2] = temp;
    }
}
