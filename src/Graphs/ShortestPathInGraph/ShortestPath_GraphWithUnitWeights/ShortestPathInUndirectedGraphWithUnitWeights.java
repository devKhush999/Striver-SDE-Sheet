package Graphs.ShortestPathInGraph.ShortestPath_GraphWithUnitWeights;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

// https://youtu.be/hwCWi7-bRfI

public class ShortestPathInUndirectedGraphWithUnitWeights {
    /* ********************************** Efficient BFS Solution ***********************************
     * For Unit Weighted edges, BFS Solution is much efficient & faster than DFS Solution
       This is because, BFS traverse all the vertices in breadth wise manner. So, starting from source
       vertex, BFS assigns the shortest distance breadth wise.
     * Approach: Instead of visited array, we take a Distance array to store the shortest path
       from the source to any node. This distance array is initialized with infinity, for Disconnected
       components n the graphs, the shortest path remains infinity only.

     * The Intuition is to use the BFS algorithm.

     * Time Complexity : O(V + E)                Same as BFS for Graph with adjacency list
     * Space Complexity: O(2 * V) = O(V)         Same as BFS for Graph with adjacency list
     */
    public int[] shortestPathToEveryNode_BFS(int V, ArrayList<ArrayList<Integer>> adjList, int source){
        // Array to store "Shortest path from source to the nodes", initialized with Infinity
        int[] shortestPath = new int[V];
        Arrays.fill(shortestPath, Integer.MAX_VALUE);

        Queue<Integer> bfsQueue = new ArrayDeque<>();

        // For source, the shortest path to itself will be 0
        shortestPath[source] = 0;
        bfsQueue.add(source);

        while (!bfsQueue.isEmpty()){
            int currVertex = bfsQueue.remove();

            // Traverse every adjacent vertex of current vertex
            for (int adjacentVertex : adjList.get(currVertex)){

                // If the "Distance from source to 'currVertex' plus 1" is smaller than "Distance from source to adjacentVertex"
                // Then we found a shorter path to the 'adjacentVertex' from the source, add adjacent vertex to the queue (as in BFS)
                if (shortestPath[currVertex] + 1 < shortestPath[adjacentVertex]){
                    shortestPath[adjacentVertex] = shortestPath[currVertex] + 1;
                    bfsQueue.add(adjacentVertex);
                }
            }
        }

        return shortestPath;
    }


    /* ********************************** Un-Efficient DFS Solution ***********************************
     * In DFS Solution, there will be lots of unnecessary DFS calls because it assigns the shortest path,
       depth wise and not breadth wise. Think harder...
     * Approach: Instead of visited array, we take a Distance array to store the shortest path
       from the source to any node. This distance array is initialized with infinity, for Disconnected
       components n the graphs, the shortest path remains infinity only.

     * The Intuition is to use the DFS algorithm.

     * Time Complexity : O(V + E)                Same as DFS for Graph with adjacency list
     * Space Complexity: O(2 * V) = O(V)         Same as DFS for Graph with adjacency list
     */
    public int[] shortestPathToEveryNode_DFS(int V, ArrayList<ArrayList<Integer>> adjList, int source) {
        // Array to store "Shortest path from source to the nodes", initialized with Infinity
        int[] shortestPath = new int[V];
        Arrays.fill(shortestPath, Integer.MAX_VALUE);

        // For source, the shortest path to itself will be 0
        shortestPath[source] = 0;

        shortestPath_DFS(source, adjList, shortestPath);
        return shortestPath;
    }

    private void shortestPath_DFS(int vertex,  ArrayList<ArrayList<Integer>> adjList, int[] shortestPath){
        // Traverse every adjacent vertex of current vertex
        for (int adjacentVertex : adjList.get(vertex)){

            // If the "Distance from source to 'currVertex' plus 1" is smaller than "Distance from source to adjacentVertex"
            // Then we found a shorter path to the 'adjacentVertex' from the source, add adjacent vertex to the queue (as in BFS)
            if (shortestPath[adjacentVertex] > shortestPath[vertex] + 1){
                shortestPath[adjacentVertex] = shortestPath[vertex] + 1;
                shortestPath_DFS(adjacentVertex, adjList, shortestPath);
            }
        }
    }
}
