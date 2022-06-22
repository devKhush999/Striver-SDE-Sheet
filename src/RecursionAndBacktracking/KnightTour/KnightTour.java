package RecursionAndBacktracking.KnightTour;
import java.util.ArrayList;
import java.util.Arrays;

// PROBLEM LINK:
// https://www.pepcoding.com/resources/online-java-foundation/recursion-backtracking/knights-tour-official/ojquestion

// MUST READ:
// https://www.geeksforgeeks.org/the-knights-tour-problem-backtracking-1/

public class KnightTour {

    /*
    * Time Complexity : O(8 ^ (n^2))
        There are n^2 Cells and for each, we have a maximum of 8 possible moves to choose from
        so the worst running time is O(8 ^(n^2)).
    * Auxiliary Space: O(n^2)    Chess board + Recursion Stack space
     */
    public ArrayList<int[][]> printKnightsTour(int n, int row, int column) {
        int[][] chessBoard = new int[n][n];

        // All possible moves of Knight in the form of dx and dy (change in x & y) from current location
        int[] moveX = {-2, -1, 1, 2,  2,  1, -1, -2};
        int[] moveY = { 1,  2, 2, 1, -1, -2, -2, -1};

        // Stores all the possible paths taken by Knight in the tour of chess board
        ArrayList<int[][]> allKnightTours = new ArrayList<>();

        // mark the current position (x,y) in chess board as 1st position as Knight is already standing there
        // and going to start from there & the Knight is initially at the cell/block "chess[x][y]"
        chessBoard[row][column] = 1;

        // find all possible Knight Tours
        getAllPossibleKnightTour(row, column, n, chessBoard, moveX, moveY, 2, allKnightTours);
        return allKnightTours;
    }

    private void getAllPossibleKnightTour(int x, int y, int n, int[][] chessBoard, int[] moveX,
                                                 int[] moveY, int upcomingMoveForKnight, ArrayList<int[][]> allKnightTours){
        // If upcoming move for knight is "n*n + 1", this means Knight has travelled all n*n cells in
        // the chess board. So, store the current Knight tour configuration.
        if (upcomingMoveForKnight == n * n + 1){
            int[][] knightTourConfiguration = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    knightTourConfiguration[i][j] = chessBoard[i][j];
            allKnightTours.add(knightTourConfiguration);
            return;
        }

        // Using Intuition of Recursion: Try out all the possible moves of Knight on current position/coordinates
        for (int i = 0; i < 8; i++){
            int nextX = x + moveX[i];
            int nextY = y + moveY[i];

            // Determine whether Knight can go to that location (nextX, nextY) in Chess Board or not
            if (isValidMoveForKnight(nextX, nextY, n, chessBoard)){
                // mark the next path of Knight at that location (nextX, nextY) in chess board with upcoming move
                chessBoard[nextX][nextY] = upcomingMoveForKnight;

                getAllPossibleKnightTour(nextX, nextY, n, chessBoard, moveX, moveY, upcomingMoveForKnight + 1, allKnightTours);

                // If further Knight tour is not possible with current Knight path (nextX, nextY)
                // then unmark the next Knight path taken and try next path of Knight at that location
                // in chess board (backtracking)
                chessBoard[nextX][nextY] = 0;
            }
        }
    }

    private boolean isValidMoveForKnight(int x, int y, int n, int[][] chessBoard){
        // Determine whether Knight can go to that location (x,y) in Chess Board or not
        // chessBoard[x][y] == 0  conditions implies that Knight has never visited that cell in Chess Board
        return x >= 0 &&  y >= 0  &&  x < n  &&  y < n  &&  chessBoard[x][y] == 0;
    }

    public static void main(String[] args) throws Exception {
        int n = 5;
        int r = 2;
        int c = 0;

        ArrayList<int[][]> allKnightTours = new KnightTour().printKnightsTour(n, r, c);
        for (int[][] knightTour : allKnightTours) {
            for (int[] row : knightTour) {
                System.out.println(Arrays.toString(row));
            }
            System.out.println();
        }
    }
}
