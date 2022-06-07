package Arrays.GridUniquePaths;

// https://youtu.be/sdE0A2Oxofw
// https://takeuforward.org/data-structure/grid-unique-paths-dp-on-grids-dp8/
// https://www.codingninjas.com/codestudio/problems/total-unique-paths_1081470?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTab=0


public class GridUniquePaths_SpaceOptimized {

    // ******************************* Space Optimized Solution ******************************
    // T.C --> O(m*n)
    // S.C --> O(n)

    public static int spaceOptimized_V1(int m, int n){
        // we only just need a one column of grid to further optimized this solution
        int[] dp = new int[n];

        // Base case for (0,0) index, this indicates total  no. of ways from index (i,j) to index (0,0)
        dp[0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i==0 && j==0)
                    continue;
                int totalWaysByMovingUp = 0, totalWaysByMovingLeft = 0;

                // We only need two values: dp[i-1][j] & dp[i][j-1]
                // Think logically what is happening, see lecture notes

                // This is (moving left) equivalent to moving right in the grid
                // We store it in the previous element of the DP array
                if (j > 0)
                    totalWaysByMovingLeft = dp[j-1];

                // This is (moving up) equivalent to moving down in the grid
                // We store it in the current element of the DP array
                // so that it can be used as the previous left (total ways from left/right)
                // of the next element in the same row (but next column, j+1)
                if (i > 0)
                    totalWaysByMovingUp = dp[j];

                // we update the total no. of unique path for current (i,j) in DP array
                // so that it can be used as the previous top (total ways from top/bottom)
                // of the next row element (in same column)
                dp[j] = totalWaysByMovingLeft + totalWaysByMovingUp;
            }
        }
        // we get the total no. of unique path for last (i,j) = (m-1,n-1) in DP array
        return dp[n-1];
    }


    // Another approach by Striver (same solution, but he does this task by 2 arary)
    public static int spaceOptimized_V2(int m, int n){
        // we only just need a one column of grid to further optimized this solution
        int[] dp_totalWaysByMovingUp = new int[n];
        int[] dp_totalWaysByMovingLeft ;

        for (int i = 0; i < m; i++) {
            dp_totalWaysByMovingLeft =  new int[n];

            for (int j = 0; j < n; j++) {

                if (i==0 && j==0) {
                    // Base case
                    dp_totalWaysByMovingLeft[0] = 1;
                    continue;
                }
                int totalWaysByMovingUp = 0, totalWaysByMovingLeft = 0;

                // We only need two values: dp[i-1][j] & dp[i][j-1]
                // Think logically what is happening, see lecture notes

                // This is (moving left) equivalent to moving right in the grid
                if (j > 0)
                    totalWaysByMovingLeft = dp_totalWaysByMovingLeft[j-1];

                // This is (moving up) equivalent to moving down in the grid
                if (i > 0)
                    totalWaysByMovingUp = dp_totalWaysByMovingUp[j];

                dp_totalWaysByMovingLeft[j] = totalWaysByMovingLeft + totalWaysByMovingUp;
            }

            // current dp_totalWaysByMovingUp will become dp_totalWaysByMovingLeft as
            // we move down, in this outer loop
            dp_totalWaysByMovingUp = dp_totalWaysByMovingLeft;
        }

        return dp_totalWaysByMovingUp[n-1];
    }

}
