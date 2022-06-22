package RecursionAndBacktracking.RatInAMaze;
import java.util.ArrayList;

// https://youtu.be/bLGZhJlt4y0
// https://takeuforward.org/data-structure/rat-in-a-maze/
// https://www.geeksforgeeks.org/rat-in-a-maze-backtracking-2/

public class RatInAMaze_Simple {
    public ArrayList<String> findAllPathsToDestination(int[][] maze, int n) {
        // Below given are the directions & corresponding (di, dj) pairs, i.,e value by which by (i,j) changes
        // Directions:     down, left, right, up
        String[] directions = {"D", "L", "R", "U"};
        int[] di =          {+1,   0,   0,   -1};
        int[] dj =          { 0,  -1,  +1,    0};

        // Stores all the paths that Rat can take to reach destination
        ArrayList<String> allPaths = new ArrayList<>();
        // visited boolean array
        boolean[][] visited = new boolean[n][n];

        // Finding all paths starting from maze[0][0] to maze[n-1][n-1]
        // we need to check first element in maze to be 1 because we are not checking inside recursion
        // if maze[0][0] we can't even move a single step
        if (maze[0][0] == 1)
            findAllPathsToDestination(0, 0, maze, visited, n, "", allPaths, directions, di, dj);
        return allPaths;
    }

    private void findAllPathsToDestination(int i, int j, int[][] maze, boolean[][] visited, int n,
                                           String path, ArrayList<String> allPaths, String[] directions,
                                           int[] di, int[] dj){
        // If we reached destination, store the path taken into an arraylist
        if (i == n-1 && j == n-1){
            allPaths.add(path);
            return;
        }


        // Moving Down, Left, Right & Up one by one in a for loop
        // Marks current cell as visited, add the current direction into the 'path' String
        // and move into that direction, after backtracking mark it as unvisited
        for (int index = 0; index < 4; index++){
            int nextI = i + di[index];
            int nextJ = j + dj[index];
            String directionToMove = directions[index];

            if (nextI < n  && nextI >= 0  &&  nextJ >= 0  && nextJ < n  && !visited[nextI][nextJ]  && maze[nextI][nextJ] == 1){
                visited[i][j] = true;
                findAllPathsToDestination(nextI, nextJ, maze, visited, n, path + directionToMove, allPaths, directions, di, dj);
                visited[i][j] = false;
            }
        }
    }
}
