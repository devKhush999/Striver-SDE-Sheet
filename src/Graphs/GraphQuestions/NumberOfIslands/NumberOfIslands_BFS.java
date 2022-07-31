package Graphs.GraphQuestions.NumberOfIslands;
import java.util.ArrayDeque;
import java.util.Queue;

// BFS: https://youtu.be/pV2kpPD66nE
// DFS: https://youtu.be/__98uL6wst8
// BFS: https://www.geeksforgeeks.org/islands-in-a-graph-using-bfs/
// DFS: https://www.geeksforgeeks.org/find-number-of-islands/

public class NumberOfIslands_BFS {
    /** ******************************* BFS Solution 1 ***********************************************
     * Time Complexity:  O(m * n)
     * Space Complexity: O(m * n)
        * Due to BFS Queue. In worst case entire grid is full of islands, then bfs queue size will be maximum
     */
    public int numberOfIslands_BFS2(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int numberOfIslands = 0;

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                // If a cell with value 1 is not visited yet, then new island is found.
                // Mark the all the cells on this island as visited in one BFS call (by making 1s to 0s)
                // Visit all the cells on this island & mark them 0 and increment island count
                // In each BFS call, entire island is visited & marked
                if (grid[i][j] == '1'){
                    numberOfIslands++;
                    bfs(i, j, m, n, grid);
                }
            }
        }
        return numberOfIslands;
    }

    public void bfs(int i, int j, int m, int n, char[][] grid){
        // Arrays are used to get row and column numbers of all 4 neighbours of a given cell
        int[] dx = {0,  0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        // Simple BFS first step, we enqueue source and mark it as visited
        Queue<int[]> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(new int[]{i, j});

        grid[i][j] = '0';

        // Next step of BFS. We take out indices (on island) one by one from queue and enqueue their
        // unvisited adjacent indices that are still on the island
        while (!bfsQueue.isEmpty()){
            i  = bfsQueue.peek()[0];
            j  = bfsQueue.peek()[1];
            bfsQueue.remove();

            // Go through all 4 adjacent neighbours
            for (int a = 0; a < 4; a++){
                int nextI = i + dx[a];
                int nextJ = j + dy[a];

                if (nextI >= 0  &&  nextJ >= 0  && nextI < m  &&  nextJ < n  && grid[nextI][nextJ] == '1') {
                    grid[nextI][nextJ] = '0';
                    bfsQueue.add(new int[]{nextI, nextJ});
                }
            }
        }
    }


    /** ******************************* BFS Solution 2 ***********************************************
     * Time Complexity:  O(m * n)
     * Space Complexity: O(m * n)
        * Due to BFS Queue In worst case entire grid is full of islands, then bfs queue size will be maximum
        * Also another O(m * n) due to Boolean visited array to keep track of visited indices
     */
    public int numberOfIslands_BFS1(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int numberOfIslands = 0;

        // Make a boolean array to mark visited cells on the Islands
        // Initially all cells are unvisited
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                // If a cell with value 1 is not visited yet, then new island is found.
                // Mark the all the cells on this island as visited in one BFS call
                // Visit all the cells on this island and increment island count
                // In each BFS call, entire island is visited & marked
                if (grid[i][j] == '1'  &&  !visited[i][j]){
                    numberOfIslands++;
                    bfs(i, j, m, n, grid, visited);
                }
            }
        }
        return numberOfIslands;
    }

    public void bfs(int i, int j, int m, int n, char[][] grid, boolean[][] visited){
        // Arrays are used to get row and column numbers of all 4 neighbours of a given cell
        int[] dx = {0,  0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        // Simple BFS first step, we enqueue source and mark it as visited
        Queue<int[]> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(new int[]{i, j});

        visited[i][j] = true;

        // Next step of BFS. We take out indices (on island) one by one from queue and enqueue their
        // unvisited adjacent indices that are still on the island
        while (!bfsQueue.isEmpty()){
            i  = bfsQueue.peek()[0];
            j  = bfsQueue.peek()[1];
            bfsQueue.remove();

            // Go through all 4 adjacent neighbours
            for (int a = 0; a < 4; a++){
                int nextI = i + dx[a];
                int nextJ = j + dy[a];

                if (nextI >= 0  &&  nextJ >= 0  && nextI < m  &&  nextJ < n  && grid[nextI][nextJ] == '1' && !visited[nextI][nextJ]) {
                    visited[nextI][nextJ] = true;
                    bfsQueue.add(new int[]{nextI, nextJ});
                }
            }
        }
    }

}
