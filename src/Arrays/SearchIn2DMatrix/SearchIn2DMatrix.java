package Arrays.SearchIn2DMatrix;

// https://youtu.be/ZYpYur0znng
// https://takeuforward.org/data-structure/search-in-a-sorted-2d-matrix/
// https://www.geeksforgeeks.org/search-in-row-wise-and-column-wise-sorted-matrix/

public class SearchIn2DMatrix {

    // *********************************** Brute Force *************************************
    // Brute Force is linear search
    // TC -> O(mn)
    // SC -> O(1)
    public boolean searchMatrix_BruteForce(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        for (int[] row : matrix)
            for (int elementInRow : row)
                if (elementInRow == target)
                    return true;

        return false;
    }


    // ******************************** Recursive Binary Search ********************************
    // This Binary Search Solution is the best ever algorithm if both the rows & columns are sorted
    // and last element of current column not greater than first element of next column
    // ,i.e, all elements of matrix when written in a single row must all be sorted
    // TC -> O( log(m*n) )
    // SC -> O( log(m*n) )
    private boolean binarySearchMatrix(int low, int high, int target, int[][] matrix, int totalRow, int totalColumn) {
        if (low > high)
            return false;

        int mid = (low + high) / 2;
        int row = mid / totalColumn;
        int column = mid % totalColumn;

        // Case when matrix[row][column] == target, element found
        if (matrix[row][column] == target)
            return true;

            // Case when matrix[row][column] > target, reduce search space to [low, mid-1]
        else if (matrix[row][column] > target)
            return binarySearchMatrix(low, mid - 1, target, matrix, totalRow, totalColumn);

            // Case when matrix[row][column] < target, reduce search space to [mid+1, high]
        else
            return binarySearchMatrix(mid + 1, high, target, matrix, totalRow, totalColumn);
    }

    public boolean searchMatrix_BinarySearchRecursive(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        return binarySearchMatrix(0, m * n - 1, target, matrix, m, n);
    }


    // ******************************** Iterative Binary Search ********************************
    // This Binary Search Solution is the best ever algorithm if both the rows & columns are sorted
    // and last element of current row is not greater than first element of next row
    // ,i.e, all elements of matrix when written in a single row must all be sorted
    // TC -> O( log(m*n) )
    // SC -> O(1)
    public boolean searchMatrix_BinarySearchIterative(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int low = 0, high = m * n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            int row = mid / n;
            int column = mid % n;

            // Case when matrix[row][column] == target, element found
            if (matrix[row][column] == target)
                return true;

                // Case when matrix[row][column] > target, reduce search space to [low, mid-1]
            else if (matrix[row][column] > target)
                high = mid - 1;

                // Case when matrix[row][column] < target, reduce search space to [mid+1, high]
            else if (matrix[row][column] < target)
                low = mid + 1;
        }
        return false;
    }


    // ********************************** Another Solution ******************************************
    // This above algo will work for both cases:
    // Case 1: even when both the rows & columns are sorted and last element of current row is
    // lesser than first element of next row.
    // Case 2: This algo will work for above case of """last element of current column is not greater
    // than first element of next column""" too

    // T.C -> O(m + n)    Only one traversal is needed, i.e, i from 0 to n and j from n-1 to 0 with at most m+n steps
    // SC -> O(1)
    // MUST SEE:  https://www.geeksforgeeks.org/search-in-row-wise-and-column-wise-sorted-matrix/

    public boolean searchMatrix_GFG(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int i = 0, j = n-1;

        while (i < m  &&  j >= 0){
            if (matrix[i][j] == target)
                return true;

            else if (matrix[i][j] > target)
                j--;

            else if (matrix[i][j] < target)
                i++;
        }
        return false;
    }

    /*
    Intuition:

    Efficient approach: The simple idea is to remove a row or column in each comparison until an
    element is found. Start searching from the top-right corner of the matrix. There are three
    possible cases.

    1) The given number is greater than the current number: This will ensure that all the elements in
    the current row are smaller than the given number as the pointer is already at the right-most
    elements and the row is sorted. Thus, the entire row gets eliminated and continues the search
    for the next row. Here, elimination means that a row needs not be searched.

    2) The given number is smaller than the current number: This will ensure that all the elements in
    the current column are greater than the given number. Thus, the entire column gets eliminated and
     continues the search for the previous column, i.e. the column on the immediate left.

    3) The given number is equal to the current number: This will end the search.
     */
}