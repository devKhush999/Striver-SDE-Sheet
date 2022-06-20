package RecursionAndBacktracking.N_Queens;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://youtu.be/i05Ju7AftcM
// https://takeuforward.org/data-structure/n-queen-problem-return-all-distinct-solutions-to-the-n-queens-puzzle/
// https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/

public class N_Queens_Hashing {
    /*
     ****************************** Basic Approach Made Efficient Using Hashing ************************
     * Note: In every row or column, we can only place "ONE" Queen
     * Otherwise, Queens will attack each other (horizontally & vertically).
     * Time Complexity: Exponential in nature since we are trying out all the ways,
       to be precise its O(N! * N).
     * Space Complexity: O(N^2) + 2*O(2n-1) + O(n)
       For chess board, two Hashed diagonals & one Hashed Row to keep track of whether queen can be
       placed at current location in the board.
     */

    public List<List<String>> solveNQueens(int n) {
        // our n x n chess board
        char[][] chessBoard = new char[n][n];
        for (char[] row : chessBoard)
            Arrays.fill(row, '.');

        // Using Hashing to check for any Queen on horizontal left direction
        boolean[] hasQueenOnHorizontalLeft = new boolean[n];
        // Using Hashing to check for any Queen on upper-left diagonal direction
        boolean[] hasQueenOnUpperLeftDiagonal = new boolean[2*n - 1];
        // Using Hashing to check for any Queen on lower-left diagonal direction
        boolean[] hasQueenOnLowerLeftDiagonal = new boolean[2*n - 1];

        // this will have all valid Queen arrangements
        List<List<String>> allValidQueenArrangements = new ArrayList<>();

        // Try Placing a 'Queen' at 0th column  "We have been doing dfs till now"
        dfs(0, n, chessBoard, allValidQueenArrangements, hasQueenOnHorizontalLeft, hasQueenOnUpperLeftDiagonal, hasQueenOnLowerLeftDiagonal);
        return allValidQueenArrangements;
    }

    private void dfs(int column, int n, char[][] board, List<List<String>> allQueenArrangements,
                     boolean[] hasQueenOnHorizontalLeft, boolean[] hasQueenOnUpperLeftDiagonal,
                     boolean[] hasQueenOnLowerLeftDiagonal){
        // If column becomes 'n', this means we have successfully put the 'N' Queens in n-columns
        if (column == n){
            ArrayList<String> list = new ArrayList<>();
            for (char[] row : board)
                list.add(new String(row));
            allQueenArrangements.add(list);
            return;
        }

        for (int row = 0; row < n; row++){
            // If we can place the Queen at the current row & column, without any of the queen attacking each other.
            // Then, it is safe to place a Queen at that position
            // Then, we try to place another Queen at the next column along with marking the Queen on
            // horizontal-left, diagonal up-left & diagonal down-eft directions
            // For equations inside Hashed array, see Video/Notes.
            // Earlier to check for valid & safe position of Queen we took O(3n) time, now the same is being done using O(1) time
            if (!hasQueenOnHorizontalLeft[row]  && !hasQueenOnLowerLeftDiagonal[row + column] &&
                    !hasQueenOnUpperLeftDiagonal[n - 1 + column - row]){
                hasQueenOnHorizontalLeft[row] = true;
                hasQueenOnLowerLeftDiagonal[column + row] = true;
                hasQueenOnUpperLeftDiagonal[n - 1 + column - row] = true;

                board[row][column] = 'Q';
                dfs(column +1 ,n, board, allQueenArrangements, hasQueenOnHorizontalLeft, hasQueenOnUpperLeftDiagonal, hasQueenOnLowerLeftDiagonal);
                board[row][column] = '.';

                hasQueenOnUpperLeftDiagonal[n - 1 + column - row] = false;
                hasQueenOnLowerLeftDiagonal[column + row] = false;
                hasQueenOnHorizontalLeft[row] = false;
            }
        }
    }
}
