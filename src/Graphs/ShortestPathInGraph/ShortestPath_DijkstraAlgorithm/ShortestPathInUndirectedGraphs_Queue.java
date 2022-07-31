package Graphs.ShortestPathInGraph.ShortestPath_DijkstraAlgorithm;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

// UN-EFFICIENT SOLUTION USING QUEUE, BFS & DFS

public class ShortestPathInUndirectedGraphs_Queue {
    /* ************************************* BFS Solution *****************************************
    * Solution is same as the "Shortest path in Undirected Graphs with Unit Weights" except that past cost is
      taken into consideration.
    * NOTE: In Queue Solution, while doing BFS there will be lots of unnecessary computations
    * Approach: Instead of visited array, we take a Distance array to store the shortest path
      from the source to any node. This distance array is initialized with infinity, for Disconnected
      components n the graphs, the shortest path remains infinity only.

    * The Intuition is to use the BFS algorithm.
    * Actual time complexity is multiple times of O(V+ E), as there will be unnecessary iterations
    * Time Complexity : O(n * (V + E))                Same as BFS for Graph with adjacency list
        * n can vary as there will be multiple repetitions
    * Space Complexity: O(2 * V) = O(V)         Same as BFS for Graph with adjacency list
    */
    public int[] shortestPathsInDAG_BFS(int V, ArrayList<ArrayList<int[]>> adjList, int source){
        // Array to store "Shortest path from source to the nodes"
        int[] shortestPath = new int[V];
        Arrays.fill(shortestPath, Integer.MAX_VALUE);

        Queue<Integer> bfsQueue = new ArrayDeque<>();

        // For source, the shortest path to itself will be 0
        shortestPath[source] = 0;
        bfsQueue.add(source);

        while (!bfsQueue.isEmpty()){
            int currVertex = bfsQueue.remove();

            // Traverse every adjacent vertex of current vertex
            for (int[] neighbour : adjList.get(currVertex)){
                int adjacentVertex = neighbour[0];
                int pathCost = neighbour[1];

                // If the "Distance from currVertex plus path cost to neighbour vertex" is smaller than
                // "Distance from source to adjacentVertex". Then we found a shorter path to the
                // 'adjacentVertex' from the source, add adjacent vertex to the queue (as in BFS)
                if (shortestPath[currVertex] + pathCost < shortestPath[adjacentVertex]){
                    shortestPath[adjacentVertex] = shortestPath[currVertex] + pathCost;
                    bfsQueue.add(adjacentVertex);
                }
            }
        }
        return shortestPath;
    }


    /* ********************************** Un-Efficient DFS Solution ***********************************
     * NOTE: In DFS Solution, while doing DFS there will be lots of unnecessary computations because
       it assigns the shortest path in depth wise manner and not breadth wise. Think harder...
     * DFS Solution gives TLE
     * This DFS Solution is even worse than BFS Solution.

     * Approach: Instead of visited array, we take a Distance array to store the shortest path
       from the source to any node. This distance array is initialized with infinity, for Disconnected
       components n the graphs, the shortest path remains infinity only.

     * The Intuition is to use the DFS algorithm.
     * Actual time complexity is multiple times of O(V+ E), as there will be unnecessary iterations
     * Time Complexity : O(n * (V + E))                Same as DFS for Graph with adjacency list
        * n can vary as there will be multiple repetitions
     * Space Complexity: O(2 * V) = O(V)         Same as DFS for Graph with adjacency list
     */
    public int[] shortestPathToEveryNode_DFS(int V, ArrayList<ArrayList<int[]>> adjList, int source) {
        // Array to store "Shortest path from source to the nodes", initialized with Infinity
        int[] shortestPath = new int[V];
        Arrays.fill(shortestPath, Integer.MAX_VALUE);

        // For source, the shortest path to itself will be 0
        shortestPath[source] = 0;

        shortestPath_DFS(source, adjList, shortestPath);
        return shortestPath;
    }

    private void shortestPath_DFS(int vertex,  ArrayList<ArrayList<int[]>> adjList, int[] shortestPath){
        // Traverse every adjacent vertex of current vertex
        for (int[] neighbour : adjList.get(vertex)){
            int adjacentVertex = neighbour[0];
            int pathCost = neighbour[1];

            // If the "Distance from source to 'currVertex' plus path-cost" is smaller than "Distance from source to adjacentVertex"
            // Then we found a shorter path to the 'adjacentVertex' from the source, add adjacent vertex to the queue (as in DFS)
            if (shortestPath[vertex] + pathCost < shortestPath[adjacentVertex]){
                shortestPath[adjacentVertex] = shortestPath[vertex] + pathCost;
                shortestPath_DFS(adjacentVertex, adjList, shortestPath);
            }
        }
    }
}
