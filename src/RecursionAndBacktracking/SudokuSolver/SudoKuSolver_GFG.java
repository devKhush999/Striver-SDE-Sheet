package RecursionAndBacktracking.SudokuSolver;

// https://youtu.be/FWAIf_EVUKE
// https://takeuforward.org/data-structure/sudoku-solver/
// https://www.geeksforgeeks.org/sudoku-backtracking-7/

// This solution is same as Previous one, just recursive function is modified

public class SudoKuSolver_GFG {
    /*
     * Time Complexity:    O(9(n ^ 2)),
     * Worst case is when entire Sudoku is empty
     * In the worst case, for each cell in the n2 board, we have 9 possible numbers.
     * For every unassigned index, there are 9 possible options so the time complexity is O(9^(n*n)).

     * Space Complexity: O(1),   Ignoring Recursion Stack Space
     * Since we are refilling the given board itself,
     * there is no extra space required, so constant space complexity.
     */
    public static boolean SolveSudoku(int[][] grid){
        return fillSudoKuBoard(0, 0, grid);
    }

    public static boolean fillSudoKuBoard(int row, int column, int[][] board){
        // This is base case when entire Sudoku board has been filled
        // (when matrix traversal reaches 8th row & 9th column, though 9th column doesn't exits)
        if (row == 8 && column == 9)
            return true;

        // If we become out of columns in boundary, we move to the Next Row in 0th Column
        if (column == 9){
            row++;
            column = 0;
        }
        // If Board already has a number filled, move to next Column in same Row to look for other
        // empty cells
        if (board[row][column] != 0)
            return fillSudoKuBoard(row, column + 1, board);

        // Same as before
        for (int number = 1; number <= 9; number++){
            if (isValidPositionForNumber(row, column, board, number)){
                board[row][column] = number;

                if (fillSudoKuBoard(row, column + 1, board))
                    return true;

                board[row][column] = 0;
            }
        }
        // If the current position was empty, and we are unable to fill any number (from 1 to 9)
        // in sudoku board, it means the current state of the board is wrong, and we need to backtrack
        return false;
    }

    // Same as before
    public static boolean isValidPositionForNumber(int row, int column, int[][] board, int number){
        for (int i = 0; i < 9; i++){
            if (board[row][i] == number)
                return false;

            if (board[i][column] == number)
                return false;

            if (board[3*(row/3) + i/3][3*(column/3) + i%3] == number)
                return false;
        }
        return true;
    }
}
