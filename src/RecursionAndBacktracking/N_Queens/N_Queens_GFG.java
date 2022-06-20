package RecursionAndBacktracking.N_Queens;
import java.util.ArrayList;

// https://youtu.be/i05Ju7AftcM
// https://takeuforward.org/data-structure/n-queen-problem-return-all-distinct-solutions-to-the-n-queens-puzzle/
// https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/

public class N_Queens_GFG {
    static ArrayList<ArrayList<Integer>> nQueen(int n) {
        char[][] board = new char[n][n];

        ArrayList<ArrayList<Integer>> allQueenPositions = new ArrayList<>();

        dfs(0, n, board, allQueenPositions);
        return allQueenPositions;
    }

    static void dfs(int column, int n, char[][] board, ArrayList<ArrayList<Integer>> allQueenPositions){
        // If column becomes 'n', this means we have successfully put the 'N' Queens in n-columns
        if (column == n){
            ArrayList<Integer> list = new ArrayList<>();

            for (int j = 0; j < n; j++)
                for (int i = 0; i < n; i++)
                    if (board[i][j] == 'Q')
                        list.add(i + 1);

            allQueenPositions.add(list);
            return;
        }

        // if we can place the Queen at the current row & column, without any of the queen attacking each other.
        // Then, it is safe to place a Queen at that position
        // Then, we try to place another Queen at the next column
        for (int row = 0; row < n; row++){
            if (isQueenSafeAndCanPlace(row, column, board, n)){
                board[row][column] = 'Q';
                dfs(column + 1, n, board, allQueenPositions);
                board[row][column] = '.';
            }
        }
    }

    static boolean isQueenSafeAndCanPlace(int rowNumber, int columnNumber, char[][] board, int n){
        // this function is doing three traversal, one on-left, on upper-left diagonal & on lower-left diagonal
        // This will take O(3n) Time complexity. We can reduce this O(3n) time to O(1) using Hashing! (see other java file)
        // We don't check vertically up, vertically down, horizontally right, diagonally up-right
        // and diagonally down-right because we haven't put any 'Queen' at those locations.

        // checking diagonally up-left whether the chess board contains any 'Queen', if 'Queen' is
        // to be put at chessBoard[row][column]
        for (int row = rowNumber, column = columnNumber; row >= 0 && column >= 0; row--, column--)
            if (board[row][column] == 'Q')
                return false;

        // checking horizontally left whether the chess board contains any 'Queen', if 'Queen' is
        // to be put at chessBoard[row][column]
        for (int row = rowNumber, column = columnNumber; column >= 0; column--)
            if (board[row][column] == 'Q')
                return false;

        // checking diagonally down-left whether the chess board contains any 'Queen', if 'Queen' is
        // to be put at chessBoard[row][column]
        for (int row = rowNumber, column = columnNumber; row < n && column >= 0; row++, column--)
            if (board[row][column] == 'Q')
                return false;
        return true;
    }
}
