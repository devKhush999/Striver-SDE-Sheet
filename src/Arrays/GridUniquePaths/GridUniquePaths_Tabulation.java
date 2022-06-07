package Arrays.GridUniquePaths;

// https://youtu.be/sdE0A2Oxofw
// https://takeuforward.org/data-structure/grid-unique-paths-dp-on-grids-dp8/
// https://www.codingninjas.com/codestudio/problems/total-unique-paths_1081470?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTab=0

public class GridUniquePaths_Tabulation {

    // ******************************* Tabulation Solution *******************************
    // T.C ==> O(m*n)  We only will be solving new problems/recursive-solution/states that are m*n in number (two for loops)
    // S.C ==> O(m*n)  due to DP array

    public static int tabulationSolution(int m, int n){
        int[][] dp = new int[m][n];

        // Base case for index (0,0), as we can reach index in only one way if (m,n)=(1,1)
        dp[0][0] = 1;

        // Just implementing the Memoization into the tabulation
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i==0 && j==0) continue;

                int totalWaysByMovingUp = 0, totalWaysByMovingLeft = 0;

                // This is (moving left) equivalent to moving right in the grid
                if (j > 0)
                    totalWaysByMovingLeft = dp[i][j-1];

                // This is (moving up) equivalent to moving down in the grid
                if (i > 0)
                    totalWaysByMovingUp = dp[i-1][j];

                int totalWaysFrom_index_i_j_to_index_0_0 =  totalWaysByMovingUp + totalWaysByMovingLeft;
                dp[i][j] = totalWaysFrom_index_i_j_to_index_0_0;
            }
        }
        // This will store the total unique paths from (0,0) to (m-1,n-1)
        return dp[m-1][n-1];
    }



    public static void main(String[] args) {
        System.out.println(tabulationSolution(2, 2));
    }
}
