package Arrays.GridUniquePaths;
import java.util.Arrays;

// https://youtu.be/sdE0A2Oxofw
// https://takeuforward.org/data-structure/grid-unique-paths-dp-on-grids-dp8/
// https://www.codingninjas.com/codestudio/problems/total-unique-paths_1081470?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTab=0


public class GridUniquePaths_Memoization {

    // ************************** Simple Recursive Solution **************************

    // Simple recursive solution
    // T.C. ==> O(2^(m*n)) because we are calling recursive function two times for every element
    // in the grid, total no. of element in the grid is m*n
    // S.C ==> O(Path length) = O(m+n)
    public static int simpleRecursion(int i, int j, int m, int n){
        if (i==m-1 && j==n-1)
            return 1;
        if (i >= m || j >= n)
            return 0;

        int totalPathsByMovingDown = simpleRecursion(i+1, j, m, n);
        int totalPathsByMovingRight = simpleRecursion(i, j+1, m, n);

        return totalPathsByMovingDown + totalPathsByMovingRight;
    }


    // ************************** Memoization aka Tabulation Solution **************************
    // It is actually a Tabulation solution because we are moving from base cases to top cases
    // We have going bottom to up here

    // T.C ==> O(m*n)  We only will be solving new problems/recursive-solution/states that are m*n in number
    // due to size of dp array, as overlapping sub-problems will be returned by DP array (O(1) in T.C)
    // S.C ==> O(m*n) + O(m+n)  due to DP array & path-length

    public static int memoizationSolution(int i, int j, int m, int n, int[][] dp){
        if (i==m-1 && j==n-1)
            return 1;
        if (i >= m || j >= n)
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        int totalPathsByMovingDown = memoizationSolution(i+1, j, m, n, dp);
        int totalPathsByMovingRight = memoizationSolution(i, j+1, m, n, dp);

        // storing the answer
        return dp[i][j] = totalPathsByMovingDown + totalPathsByMovingRight;
    }

    public static int totalUniquePaths_V1(int m, int n){
        int[][] dp = new int[m][n];
        for (int i=0; i<m; i++)
            Arrays.fill(dp[i], -1);

        return memoizationSolution(0, 0, m, n, dp);
    }


    // ******************************* Actual Memoization Solution *******************************
    // Recall memoization solution is from top to bottom cases (m-1, n-1) to (0, 0)
    // This will be the actual Memoization solution

    // T.C ==> O(m*n)  We only will be solving new problems/recursive-solution/states that are m*n in number
    // due to size of dp array, as overlapping sub-problems will be returned by DP array (O(1) in T.C)
    // S.C ==> O(m*n) + O(m+n)  due to DP array & path-length

    public static int memoizationSolution(int i, int j, int[][] dp){
        if (i==0 && j==0)
            return 1;
        if (i < 0 || j < 0)
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        // This is opposite to that of moving bottom
        int totalWaysByMovingUp = memoizationSolution(i-1, j, dp);

        // This is opposite to that of moving right
        int totalWaysByMovingLeft = memoizationSolution(i, j-1, dp);

        return dp[i][j] = totalWaysByMovingLeft + totalWaysByMovingUp;
    }

    public static int totalUniquePaths_V2(int m, int n){
        int[][] dp = new int[m][n];

        for (int i=0; i<m; i++)
            Arrays.fill(dp[i], -1);

        return memoizationSolution(m-1, n-1, dp);
    }

}
