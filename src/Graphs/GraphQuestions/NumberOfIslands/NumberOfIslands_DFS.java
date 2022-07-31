package Graphs.GraphQuestions.NumberOfIslands;

// BFS: https://youtu.be/pV2kpPD66nE
// DFS: https://youtu.be/__98uL6wst8
// BFS: https://www.geeksforgeeks.org/islands-in-a-graph-using-bfs/
// DFS: https://www.geeksforgeeks.org/find-number-of-islands/

public class NumberOfIslands_DFS {
    /** ******************************* DFS Solution 1 ***********************************************
     * Time Complexity:  O(m * n)
     * Space Complexity: O(m * n)
        * Space due to Recursion stack space of DFS calls.
          In worst case entire grid is full of islands, then dfs recursion stack space will be maximum (O(m*n))
     */
    public int numberOfIslands_DFS1(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int numberOfIslands = 0;

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                // If a cell with value 1 is not visited yet, then new island is found.
                // Mark the all the cells on this island as visited in one DFS call (by making 1s to 0s)
                // Visit all the cells on this island & mark them 0 and increment island count
                // In each DFS call, entire island is visited & marked
                if (grid[i][j] == '1'){
                    numberOfIslands++;
                    dfs(i, j, m, n, grid);
                }
            }
        }
        return numberOfIslands;
    }

    public void dfs(int i, int j, int m, int n, char[][] grid){
        // We make sure DFS is called for indices inside boundary AND for neighbours that are
        // unvisited in DFS (not called previously) and for neighbours that are still inside the islands
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == '0')
            return;

        // Mark this cell as visited (by removing connected lands on the same island)
        grid[i][j] = '0';

        // Calling DFS for neighbours (since there are only 4 of them, we are writing manually)
        // We can also do this inside a for loop using 'dx' & 'dy' array (See GFG DFS)
        dfs(i - 1, j, m, n, grid);      // up
        dfs(i, j + 1, m, n, grid);      // right
        dfs(i + 1, j, m, n, grid);      // down
        dfs(i, j - 1, m, n, grid);      // left
    }


    /** ******************************* DFS Solution 2 ***********************************************
     * Time Complexity:  O(m * n)
     * Space Complexity: O(m * n)
        * Space due to Recursion stack space of DFS calls.
          In worst case entire grid is full of islands, then dfs recursion stack space will be maximum (O(m*n))
        * Another O(m*n) space due to Boolean visited array to keep track of visited indices
     */
    public int numberOfIslands_DFS2(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int numberOfIslands = 0;

        // Make a boolean array to mark visited cells on the Islands
        // Initially all cells are unvisited
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                // If a cell with value 1 is not visited yet, then new island is found.
                // Mark the all the cells on this island as visited in one DFS call
                // Visit all the cells on this island and increment island count
                // In each DFS call, entire island is visited & marked
                if (grid[i][j] == '1'  &&  !visited[i][j]){
                    numberOfIslands++;
                    dfs(i, j, m, n, grid, visited);
                }
            }
        }
        return numberOfIslands;
    }

    public void dfs(int i, int j, int m, int n, char[][] grid, boolean[][] visited){
        // We make sure DFS is called for indices inside boundary AND for neighbours that are
        // unvisited in DFS (not called previously) and for neighbours that are still inside the islands
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == '0' || visited[i][j])
            return;

        // Mark this cell as visited
        visited[i][j] = true;

        // Calling DFS for neighbours (since there are only 4 of them, we are writing manually)
        // We can also do this inside a for loop using 'dx' & 'dy' array (See GFG DFS)
        dfs(i - 1, j, m, n, grid, visited);     // up
        dfs(i, j + 1, m, n, grid, visited);     // right
        dfs(i + 1, j, m, n, grid, visited);     // down
        dfs(i, j - 1, m, n, grid, visited);     // left
    }
}