package RecursionAndBacktracking.RatInAMaze;
import java.util.ArrayList;

// https://youtu.be/bLGZhJlt4y0
// https://takeuforward.org/data-structure/rat-in-a-maze/
// https://www.geeksforgeeks.org/rat-in-a-maze-backtracking-2/

// Problem Link: https://www.codingninjas.com/codestudio/problems/758966?

public class RatInAMaze_CodeStudio {

    public ArrayList<ArrayList<Integer>> ratInAMaze(int[][] maze, int n) {
        // We don't need a direction String[] here because we don't need directions here
        // Below given are the direction's corresponding (di, dj) pairs, i.,e value by which by (i,j) changes
        // Directions: down, left, right, up
        int[] di =     {+1,    0,    0,   -1};
        int[] dj =     {0,    -1,   +1,    0};

        // visited int[][] array, not making boolean[][] array because we need to store path in fom of 1s and 0s for output
        int[][] visited = new int[n][n];

        // Stores all the paths that Rat can take to reach destination
        ArrayList<ArrayList<Integer>> allPaths = new ArrayList<>();

        // Finding all paths starting from maze[0][0] to maze[n-1][n-1]
        // we need to check first element in maze to be 1 because we are not checking inside recursion
        // if maze[0][0] we can't even move a single step
        if (maze[0][0] == 1)
            findPath(0, 0, maze, n, allPaths, visited, di, dj);
        return allPaths;
    }

    public void findPath(int i, int j, int[][] maze, int n, ArrayList<ArrayList<Integer>> allPaths, int[][] visited,
                         int[] di, int[] dj){
        // If we reached destination, store the path taken into an arraylist
        if (i == n-1 && j == n-1){
            // marking visited[n-1][n-1] as 1, because we need to store the paths
            visited[i][j] = 1;

            // add all the cells in the grid/maze travelled (given by visited array) into 'allPaths'
            ArrayList<Integer> currentPathToDestination = new ArrayList<>();
            for (int[] row : visited)
                for (int pathTakenValue : row)
                    currentPathToDestination.add(pathTakenValue);
            allPaths.add(currentPathToDestination);

            // un-marking visited[n-1][n-1] as 0, as path has been added & we need to backtrack
            visited[i][j] = 0;
            return;
        }

        // Moving Down, Left, Right & Up one by one in a for loop
        // Marks current cell as visited, and move into that direction, after backtracking mark it as unvisited
        for (int ind = 0; ind < 4; ind++){
            int nextI = i + di[ind];
            int nextJ = j + dj[ind];

            if (nextI >= 0  &&  nextJ >= 0  &&  nextI < n  &&  nextJ < n  &&  visited[nextI][nextJ] == 0  &&  maze[nextI][nextJ] == 1){
                visited[i][j] = 1;
                findPath(nextI, nextJ, maze, n, allPaths, visited, di, dj);
                visited[i][j] = 0;
            }
        }
    }
}
