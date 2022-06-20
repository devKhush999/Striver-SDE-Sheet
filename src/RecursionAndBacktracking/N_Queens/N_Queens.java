package RecursionAndBacktracking.N_Queens;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://youtu.be/i05Ju7AftcM
// https://takeuforward.org/data-structure/n-queen-problem-return-all-distinct-solutions-to-the-n-queens-puzzle/
// https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/

public class N_Queens {
    /*
    ************************************* Basic Approach ********************************
    * Note: In every row or column, we can only place "ONE" Queen
    * Otherwise, Queens will attack each other (horizontally & vertically).
    * Time Complexity: Exponential in nature since we are trying out all the ways,
      to be precise its O(N! * N).
    * Space Complexity: O(n^2)  duw to Chess board
     */
    public List<List<String>> solveNQueens(int n) {
        // our n x n chess board
        char[][] chessBoard = new char[n][n];
        for (char[] row : chessBoard)
            Arrays.fill(row, '.');

        List<List<String>> allValidQueenArrangements = new ArrayList<>();

        // Try Placing a 'Queen' at 0th column  "We have been doing dfs till now"
        dfs(0, n, chessBoard, allValidQueenArrangements);
        return allValidQueenArrangements;
    }

    public void dfs(int column, int n, char[][] board, List<List<String>> allQueenArrangements){
        // If column becomes 'n', this means we have successfully put the 'N' Queens in n-columns
        if (column == n){
            ArrayList<String> list = new ArrayList<>();
            for (char[] row : board)
                list.add(new String(row));
            allQueenArrangements.add(list);
            return;
        }

        for (int row = 0; row < n; row++){
            // if we can place the Queen at the current row & column, without any of the queen attacking each other.
            // Then, it is safe to place a Queen at that position
            // Then, we try to place another Queen at the next column
            if (isQueenSafeAndCanPlace(row, column, n, board)){
                board[row][column] = 'Q';
                dfs(column + 1, n, board, allQueenArrangements);
                board[row][column] = '.';
            }
        }
    }

    public boolean isQueenSafeAndCanPlace(int rowNumber, int columnNumber, int n, char[][] board){
        // this function is doing three traversal, one on-left, on upper-left diagonal & on lower-left diagonal
        // This will take O(3n) Time complexity. We can reduce this O(3n) time to O(1) using Hashing! (see other java file)
        // We don't check vertically up, vertically down, horizontally right, diagonally up-right
        // and diagonally down-right because we haven't put any 'Queen' at those locations.
        int row = rowNumber, column = columnNumber;

        // checking diagonally up-left whether the chess board contains any 'Queen', if 'Queen' is
        // to be put at chessBoard[row][column]
        while (row >= 0 && column >= 0){
            if (board[row][column] == 'Q')
                return false;
            row--;
            column--;
        }

        // checking horizontally left whether the chess board contains any 'Queen', if 'Queen' is
        // to be put at chessBoard[row][column]
        row = rowNumber;
        column = columnNumber;
        while (column >= 0){
            if (board[row][column] == 'Q')
                return false;
            column--;
        }

        // checking diagonally down-left whether the chess board contains any 'Queen', if 'Queen' is
        // to be put at chessBoard[row][column]
        row = rowNumber;
        column = columnNumber;
        while (row < n  && column >= 0){
            if (board[row][column] == 'Q')
                return false;
            row++;
            column--;
        }
        return true;
    }
}
