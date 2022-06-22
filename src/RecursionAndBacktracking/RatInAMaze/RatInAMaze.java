package RecursionAndBacktracking.RatInAMaze;
import java.util.ArrayList;

// https://youtu.be/bLGZhJlt4y0
// https://takeuforward.org/data-structure/rat-in-a-maze/
// https://www.geeksforgeeks.org/rat-in-a-maze-backtracking-2/

public class RatInAMaze {
    /*
    * Time Complexity: O(4^(m*n)), because on every cell we need to try 4 different directions.
    * Space Complexity:  O(m*n) + o(m*n)
    * One O(mn) for Maximum Depth of the recursion tree or Recursion stack space (auxiliary space).
    * Another O(mn) for Visited boolean array
     */
    /*
    ****************************** Easy Recursion Solution : Similar to DP ****************************
    * Approach is similar to DP.
    * But we need to maintain a visited array, because in a path no cell can be visited more than once.
    * Also, we need to maintain a visited array so that we don't get stuck in a
      loop of path (will cause StackOverFlowException)
     */
    public ArrayList<String> findAllPathsToDestination_V1(int[][] maze, int n) {
        // Stores all the paths that Rat can take to reach destination
        ArrayList<String> allPaths = new ArrayList<>();

        // visited boolean array
        boolean[][] visited = new boolean[n][n];
        // Finding all paths starting from maze[0][0] to maze[n-1][m-1]
        findAllPathsToDestination_V1(0, 0, maze, n, new ArrayList<>(), allPaths, visited);
        return allPaths;
    }

    private void findAllPathsToDestination_V1(int i, int j, int[][] maze , int n, ArrayList<Character> path, ArrayList<String> allPaths, boolean[][] visited){
        // Check for boundary case, whether we are still in grid or not
        if (i < 0 || j < 0 || j >= n || i >= n)
            return;

        // If path is block simply return
        if (maze[i][j] == 0)
            return;

        // If we reached destination, store the path taken into an arraylist
        if (i == n-1 && j == n-1){
            String pathTaken = "";
            for (char ch : path)
                pathTaken += ch;
            allPaths.add(pathTaken);
            return;
        }

        // Moving downwards: Marks current cell as visited, add the Down 'D' direction into the 'path' ArrayList
        // and move downwards, after backtracking mark it as unvisited & also remove the direction taken
        if (i+1 < n && !visited[i+1][j]){
            visited[i][j] = true;
            path.add('D');
            findAllPathsToDestination_V1(i+1, j, maze, n, path, allPaths, visited);
            path.remove(path.size() -1);
            visited[i][j] = false;
        }

        // Moving Left: Marks current cell as visited, add the Left 'L' direction into the 'path' ArrayList
        // and move left, after backtracking mark it as unvisited & also remove the direction taken
        if (j-1 >= 0 && !visited[i][j-1]){
            visited[i][j] = true;
            path.add('L');
            findAllPathsToDestination_V1(i, j-1, maze, n, path, allPaths, visited);
            path.remove(path.size() -1);
            visited[i][j] = false;
        }

        // Moving Right: Marks current cell as visited, add the Right 'R' direction into the 'path' ArrayList
        // and move right, after backtracking mark it as unvisited & also remove the direction taken
        if (j+1 < n && !visited[i][j+1]){
            visited[i][j] = true;
            path.add('R');
            findAllPathsToDestination_V1(i, j+1, maze, n, path, allPaths, visited);
            path.remove(path.size() -1);
            visited[i][j] = false;
        }

        // Moving Upwards: Marks current cell as visited, add the Upwards 'U' direction into the 'path' ArrayList
        // and move upwards, after backtracking mark it as unvisited & also remove the direction taken
        if (i-1 >= 0 && !visited[i-1][j]){
            visited[i][j] = true;
            path.add('U');
            findAllPathsToDestination_V1(i - 1, j, maze, n, path, allPaths, visited);
            path.remove(path.size() -1);
            visited[i][j] = false;
        }
    }



    /*
    ****************************** Easy Recursion Solution : Same Code ****************************
    * Recursion is simplified
    * Approach is similar to DP.
    * But we need to maintain a visited array, because in a path no cell can be visited more than once.
    * Also, we need to maintain a visited array so that we don't get stuck in a
      loop of path (will cause StackOverFlowException)
     */
    public ArrayList<String> findAllPathsToDestination_V2(int[][] maze, int n) {
        // Stores all the paths that Rat can take to reach destination
        ArrayList<String> allPaths = new ArrayList<>();

        // visited boolean array
        boolean[][] visited = new boolean[n][n];

        // Finding all paths starting from maze[0][0] to maze[n-1][n-1]
        // we need to check first element in maze to be 1 because we are not checking inside recursion
        // if maze[0][0] we can't even move a single step
        if (maze[0][0] == 1)
            findAllPathsToDestination(0, 0, maze, n, "", allPaths, visited);
        return allPaths;
    }

    private void findAllPathsToDestination(int i, int j, int[][] maze, int n, String path, ArrayList<String> allPaths, boolean[][] visited){
        // If we reached destination, store the path taken into an arraylist
        if (i == n-1 && j == n-1){
            allPaths.add(path);
            return;
        }

        // Moving downwards: Marks current cell as visited, add the Down 'D' direction into the 'path' String
        // and move downwards, after backtracking mark it as unvisited
        if (i+1 < n  &&  !visited[i+1][j]  &&  maze[i+1][j] == 1){
            visited[i][j] = true;
            findAllPathsToDestination(i+1, j, maze, n, path + "D", allPaths, visited);
            visited[i][j] = false;
        }

        // Moving Left: Marks current cell as visited, add the Left 'L' direction into the 'path' String
        // and move left, after backtracking mark it as unvisited
        if (j-1 >= 0  &&  !visited[i][j-1]  &&  maze[i][j-1] == 1){
            visited[i][j] = true;
            findAllPathsToDestination(i, j-1, maze, n, path + "L", allPaths, visited);
            visited[i][j] = false;
        }

        // Moving Right: Marks current cell as visited, add the Right 'R' direction into the 'path' String
        // and move right, after backtracking mark it as unvisited
        if (j+1 < n  &&  !visited[i][j+1]  &&  maze[i][j+1] == 1){
            visited[i][j] = true;
            findAllPathsToDestination(i, j+1, maze, n, path + "R", allPaths, visited);
            visited[i][j] = false;
        }

        // Moving Upwards: Marks current cell as visited, add the Upwards 'U' direction into the 'path' String
        // and move upwards, after backtracking mark it as unvisited
        if (i-1 >= 0  &&  !visited[i-1][j] &&  maze[i-1][j] == 1){
            visited[i][j] = true;
            findAllPathsToDestination(i - 1, j, maze, n, path + "U", allPaths, visited);
            visited[i][j] = false;
        }
    }

}
