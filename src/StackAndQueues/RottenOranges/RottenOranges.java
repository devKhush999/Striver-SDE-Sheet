package StackAndQueues.RottenOranges;
import java.util.ArrayDeque;
import java.util.Queue;

// https://youtu.be/pUAPcVlHLKA
// https://takeuforward.org/data-structure/rotten-oranges-min-time-to-rot-all-oranges-bfs/
// https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
// https://www.geeksforgeeks.org/minimum-time-required-so-that-all-oranges-become-rotten/

/*
* INTUITION: USE OF BFS
* Approach: The idea is to use Breadth First Search. The condition of oranges getting rotten is when
    they come in contact with other rotten oranges. This is similar to breadth-first search where the
    graph is divided into layers or circles and the search is done from lower or closer layers to
    deeper or higher layers.

* Algorithm:
    * Create an empty queue Q.
    * Find all rotten oranges and enqueue them to Q.
    * Run a loop While Q is not empty
    * Do following while Q is not empty
        * Dequeue an orange from the queue, rot all adjacent oranges. While rotting the adjacent,
          make sure that the time frame is incremented only once.
          And the time frame is not incremented if there are no adjacent oranges.
        * Dequeue the old rotten orange and enqueue a new rottened orange (if exist).
          The oranges rotten in the previous time frame lie between the two interval.

* Time Complexity: O(n * n) +  4 * O(n * n)  =  O(n * n)
  * O(n*n) for counting all oranges & finding rotten oranges position.
  * In worst case there can be Just one rotten orange, all cells have un-rotten oranges.
  * 4 * O(n*n)  due to finding all possible paths for all rotten oranges.
  * Worst-case – We will be making each fresh orange rotten in the grid and for each rotten orange will check in 4 directions

* Space Complexity: O(n * n)
  * Due to Size of queue, in worst case all oranges can be Rotten. SO, in that case queue will
    have n*n rotten oranges.
  * worst-case –  If all oranges are Rotten, we will end up pushing all rotten oranges into the Queue data structure
*/

public class RottenOranges {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // It will store total number of oranges in the grid ( Rotten + Fresh )
        int totalOranges = 0;

        // Queue data structure to store coordinate of Rotten Oranges
        Queue<int[]> rottenOrangesQueue = new ArrayDeque<>();

        // Find all the coordinates of Rotten oranges & count the total number of oranges in the grid
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1 || grid[i][j] == 2)
                    totalOranges++;
                if (grid[i][j] == 2)
                    rottenOrangesQueue.add(new int[]{i, j});
            }

        // Movement Directions
        int[] dx = {1, -1, 0,  0};
        int[] dy = {0,  0, 1, -1};

        // It will store the total number of oranges rotten by us .
        int totalOrangesRottened = 0;
        // total time taken to rotten.
        int totalTimeTaken = 0;


        // While our queue is not empty, we will pick up each Rotten Orange and check in all its
        // "4" directions whether a Fresh orange is present or not.
        // If it is present we will make it rotten and push it in our queue data structure and pop
        // out the Rotten Orange which we took up as its work is done now.
        // BFS starting from initially rotten oranges
        while (!rottenOrangesQueue.isEmpty()){
            // keep track of the count of rotten oranges we have processed (i.e, turning their fresh neighbors oranges into rotten oranges )
            int currRottenOranges = rottenOrangesQueue.size();
            totalOrangesRottened += currRottenOranges;

            for (int i = 1; i <= currRottenOranges; i++){
                int[] currRottenOrangeLocation = rottenOrangesQueue.remove();

                for (int j = 0; j < 4; j++){
                    int nextX = currRottenOrangeLocation[0] + dx[j];
                    int nextY = currRottenOrangeLocation[1] + dy[j];

                    if (nextX >= 0 && nextY >= 0 && nextX < m && nextY < n && grid[nextX][nextY] == 1){
                        grid[nextX][nextY] = 2;
                        rottenOrangesQueue.add(new int[]{nextX, nextY});
                    }
                }
            }
            // If we rotten some oranges, then obviously our queue will not be empty.
            // In that case, we will increase our total time. This goes on until our queue becomes empty.
            if (!rottenOrangesQueue.isEmpty())
                totalTimeTaken++;
        }

        // Whether the total number of oranges initially is equal to the count of oranges rottened by us.
        // If yes, we will return the total time taken, else will return -1 because some fresh oranges
        // are still left and can’t be made rotten.
        return totalOranges == totalOrangesRottened ? totalTimeTaken : -1;
    }
}
