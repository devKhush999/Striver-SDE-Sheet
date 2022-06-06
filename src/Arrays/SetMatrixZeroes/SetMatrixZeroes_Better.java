package Arrays.SetMatrixZeroes;
import java.util.Arrays;

// https://takeuforward.org/data-structure/set-matrix-zero/
// https://youtu.be/M65xBewcqcI


public class SetMatrixZeroes_Better {

    // ***************************************** Approach 2 *****************************************
    // T.C --> O(m * n)
    // S.C --> O(m + n)

    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean[] zeroRowFound = new boolean[m];
        boolean[] zeroColumnFound = new boolean[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0)
                    zeroRowFound[i] = zeroColumnFound[j] = true;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (zeroRowFound[i] || zeroColumnFound[j])
                    matrix[i][j] = 0;
            }
        }
    }


    // ***************************************** Approach 3 *****************************************
    public void setZeroes_(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean row0_HasToBeZero = false;
        boolean column0_HasToBeZero = false;

        // Checking whether the entire 0th column needs to be overwritten with 0
        for (int i = 0; i < m; i++)
            if (matrix[i][0] == 0) {
                column0_HasToBeZero = true;
                break;
            }

        // Checking whether the entire 0th row needs to be overwritten with 0
        for (int j = 0; j < n; j++)
            if (matrix[0][j] == 0) {
                row0_HasToBeZero = true;
                break;
            }

        // Using the same logic that we used in previous approach,
        // but we use 0th row & 0th column for this purpose
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                if (matrix[i][j] == 0)
                    matrix[0][j] = matrix[i][0] = 0;


        // Overwriting the array with 0s as done in previous logic
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;


        // If entire 0th row has to zero, then overwrite it
        if (row0_HasToBeZero)
            for (int j = 0; j < n; j++)
                matrix[0][j] = 0;

        // If entire 0th column has to zero, then overwrite it
        if (column0_HasToBeZero)
            for (int i = 0; i < m; i++)
                matrix[i][0] = 0;
    }


    public static void main(String[] args) {
        int[][] arr = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};

        new SetMatrixZeroes_Better().setZeroes_(arr);

        System.out.println(Arrays.deepToString(arr));
    }
}
