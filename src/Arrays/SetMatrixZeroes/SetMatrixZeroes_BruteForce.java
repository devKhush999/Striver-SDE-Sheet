package Arrays.SetMatrixZeroes;

// https://takeuforward.org/data-structure/set-matrix-zero/
// https://youtu.be/M65xBewcqcI

public class SetMatrixZeroes_BruteForce {

    // ***************************************** Approach 1 *****************************************
    // This will work only when elements of array are not -1
    // Not recommended

    // T.C --> O(m * n * (m+n))
    // S.C --> O(1)

    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        for (int i = 0; i < m; i++){
            for (int j  =0; j < n; j++){
                if (matrix[i][j] == 0){

                    int row = i -1;
                    while (row >= 0){
                        if (matrix[row][j] != 0)
                            matrix[row][j] = -1;
                        row--;
                    }

                    row = i + 1;
                    while (row < m){
                        if (matrix[row][j] != 0)
                            matrix[row][j] = -1;
                        row++;
                    }

                    int column = j -1;
                    while (column >= 0){
                        if (matrix[i][column] != 0)
                            matrix[i][column] = -1;
                        column--;
                    }

                    column = j + 1;
                    while (column < n){
                        if (matrix[i][column] != 0)
                            matrix[i][column] = -1;
                        column++;
                    }
                }
            }
        }

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == -1)
                    matrix[i][j] = 0;
            }
        }
    }

}
