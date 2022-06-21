package RecursionAndBacktracking.SudokuSolver;
import java.util.Arrays;

// https://youtu.be/FWAIf_EVUKE
// https://takeuforward.org/data-structure/sudoku-solver/
// https://www.geeksforgeeks.org/sudoku-backtracking-7/

public class SudokuSolver {
    /*
    * Time Complexity:    O(9(n ^ 2)),
       * Worst case is when entire Sudoku is empty
       * In the worst case, for each cell in the n2 board, we have 9 possible numbers.
       * For every unassigned index, there are 9 possible options so the time complexity is O(9^(n*n)).

    * Space Complexity: O(1),   Ignoring Recursion Stack Space
       * Since we are refilling the given board itself,
       * there is no extra space required, so constant space complexity.
     */
    public void solveSudoku(char[][] board) {
        fillSudokuBoard(board);
    }

    private boolean fillSudokuBoard(char[][] board){
        // Visit every position in the Sudoku board, and see if it is empty and can be filled with any number
        for (int row = 0; row < 9; row++){
            for (int column = 0; column < 9; column++){

                // If the current position in sudoku is empty, then try to fill that position with
                // all numbers from 1 to 9 one by one
                if (board[row][column] == '.') {

                    for (char number = '1'; number <= '9'; number++) {
                        if (isValidPositionForNumber(number, row, column, board)) {
                            // Fill the current number in Sudoku board
                            board[row][column] = number;

                            // returns 'true' if the remaining empty positions of Sudoku can be filled by
                            // filling the current position in the board with the current number
                            // If a recursive call returns true, we can assume that we found one possible way of filling,
                            // and we simply do an early return. (because there are multiple solutions in Sudoku)
                            // So, we return the first Solution that we found
                            if (fillSudokuBoard(board))
                                return true;
                            // Since we were unable to fill the entire Sudoku by putting current number in board[row][column]
                            // So, remove that number from current position (board[row][column])
                            board[row][column] = '.';
                        }
                    }
                    // If the current position was empty, and we are unable to fill any number (from 1 to 9)
                    // in sudoku board, it means the current state of the board is wrong, and we need to backtrack
                    // An important point to follow is, we need to return false to let the parent
                    // function(which is called this function) know that we cannot fill this way.
                    return false;
                }
            }
        }
        // This is the case when all the rows & columns in the sudoku-puzzle are filled
        // then return true, because all the positions in the board has been filled (control came out of for loop)
        return true;
    }

    private boolean isValidPositionForNumber(char numberToFill, int row, int column, char[][] board){
        for (int i = 0; i < 9; i++){
            // Check horizontally, if Sudoku board already has the number to filled in current position
            if (board[row][i] == numberToFill)
                return false;

            // Check vertically, if Sudoku board already has the number to filled in current position
            if (board[i][column] == numberToFill)
                return false;

            // Check in 3x3 small squares, if Sudoku board already has the number to filled in current position
            // for expression see intuition
            if (board[3 * (row/3) + (i/3)][3 * (column/3) + (i%3)] == numberToFill)
                return false;
        }
        return true;
    }


    public static void main(String[] args) {
        char[][] board = new char[9][9];
        for (char[] row : board)
            Arrays.fill(row, '.');

        new SudokuSolver().solveSudoku(board);

        for (char[] row : board)
            System.out.println(Arrays.toString(row));
    }
}
