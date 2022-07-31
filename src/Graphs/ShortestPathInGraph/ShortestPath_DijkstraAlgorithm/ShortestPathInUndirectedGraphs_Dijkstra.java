package Graphs.ShortestPathInGraph.ShortestPath_DijkstraAlgorithm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// https://youtu.be/jbhuqIASjoM
// https://takeuforward.org/data-structure/dijkstras-algorithm-shortest-distance/
// https://www.geeksforgeeks.org/dijkstras-algorithm-for-adjacency-list-representation-greedy-algo-8/

// IMP: Dijkstra Algorithm works for both directed and undirected, with positively weighted graphs only

public class ShortestPathInUndirectedGraphs_Dijkstra {
    /** *********************************** Dijkstra Algorithm ******************************************
     * Idea is to use BFS Algorithm for finding the shortest path and instead of normal queue, we will use
       Priority Queue (Min-Heap).
     * Dijkstra Algorithm is a graph algorithm for finding the shortest path from a source node to all
       other nodes in a graph(single-source shortest path). It is a type of greedy algorithm.
       It only works on weighted graphs with positive weights. It has a time complexity of O(V^2 * log(V))
       using the adjacency matrix representation of graph.
       The time complexity can be reduced to O((V+E) * logV) using adjacency list representation of the
       graph, where E is the number of edges in the graph and V is the number of vertices in the graph.

     * IMP: Dijkstra Algorithm works for both directed and undirected, with positively weighted graphs
     * The algorithm is purely based on greedy approach and thus finds the optimal choice at each step
       of the algorithm.

     * Time Complexity: O((V + E) * logV)  ~  O(V * log(V) + E)
        BFS algorithm takes O(V + E) time, and we are also adding V vertices in MinHeap. So, add() and
        extractMin() will cost O(log(V)) time for every vertex. So, time complexity is O((V + E) * logV) ~ O(V*log(V) + E)
     * Space Complexity: O(2 * V) = O(V)
        O(V) for Priority Queue
        Another O(V) for storing int[] array inside Priority queue
     */
    public int[] shortestPath_Dijkstra_V1(int V, int source, ArrayList<ArrayList<int[]>> adjList){
        // Shortest Path array to store shortest distance from source to every other node
        int[] shortestPath = new int[V];

        // The distance value assigned to all other vertices is infinity
        // The distance value assigned to source vertex is 0
        Arrays.fill(shortestPath, Integer.MAX_VALUE);
        shortestPath[source] = 0;

        // Create a Min Heap. Every node of the min-heap contains the vertex number and distance value
        // of that vertex from source. Initialize Min Heap with source vertex as root.
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        minHeap.add(new int[]{source, 0});

        // While Min Heap is not empty, do the following
        while (!minHeap.isEmpty()){
            // Extract the vertex with minimum distance value node from Min Heap. Let the extracted vertex be u.
            int currVertex  = minHeap.remove()[0];

            // For every adjacent vertex v of u. If the distance of "v from source" is more than the
            // weight of u-v plus the distance of "u from source", then update the distance value of v
            for (int[] neighbour : adjList.get(currVertex)){
                int adjacentVertex = neighbour[0];
                int pathCost = neighbour[1];

                if (shortestPath[adjacentVertex] > shortestPath[currVertex] + pathCost){
                    shortestPath[adjacentVertex] = shortestPath[currVertex] + pathCost;
                    minHeap.add(new int[]{adjacentVertex, shortestPath[adjacentVertex]});
                }
            }
        }
        return shortestPath;
    }


    /************************************** Dijkstra Solution (another Version) ***************************************
     * We don't need to store the int[] inside MinHeap (to arrange vertices in increasing distance from the source)
     * We can modify the MinHeap using lambda function to do the same
     *
     * Time Complexity: O((V + E) * logV)  ~  O(V * log(V) + E)
        BFS algorithm takes O(V + E) time, and we are also adding V vertices in MinHeap. So, add() and
        extractMin() will cost O(log(V)) time for every vertex. So, time complexity is O((V + E) * logV) ~ O(V*log(V) + E)
     * Space Complexity: O(V)
        O(V) for Priority Queue. We don't need to store int[] array
     */
    public int[] shortestPath_Dijkstra_V2(int V, int source, ArrayList<ArrayList<int[]>> adjList){
        int[] shortestPath = new int[V];

        Arrays.fill(shortestPath, Integer.MAX_VALUE);
        shortestPath[source] = 0;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> (shortestPath[a] - shortestPath[b]));
        minHeap.add(source);

        while (!minHeap.isEmpty()){
            int currVertex  = minHeap.remove();

            for (int[] neighbour : adjList.get(currVertex)){
                int adjacentVertex = neighbour[0];
                int pathCost = neighbour[1];

                if (shortestPath[adjacentVertex] > shortestPath[currVertex] + pathCost){
                    shortestPath[adjacentVertex] = shortestPath[currVertex] + pathCost;
                    minHeap.add(adjacentVertex);
                }
            }
        }
        return shortestPath;
    }
}
