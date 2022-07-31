package Graphs.ShortestPathInGraph.ShortestPath_AllVerticesPairs;
import java.util.ArrayList;

// https://youtu.be/nV_wOZnhbog
// https://www.geeksforgeeks.org/floyd-warshall-algorithm-dp-16/
// https://www.quora.com/Whats-the-logic-behind-the-Floyd-Warshall-algorithm-especially-in-regards-to-the-2D-matrix
// https://www.scaler.com/topics/data-structures/floyd-warshall-algorithm/

/**
 * Floyd-Warshall Algorithm is an algorithm for finding the shortest path between all the pairs of vertices
   in a weighted graph. This algorithm works for both the directed and undirected weighted graphs.
 * But, it does not work for the graphs with negative cycles (where the sum of the edges in a cycle is
   negative). So, it will not work for Undirected Graphs with even a single negative edge (as it will be a cycle).

 * Floyd-Warshall Algorithm has a time complexity of O(V^3)
 * For Undirected Graphs:
        * With Negative Edges:
            * Can't be used as it will be a negative edge weight cycle, but it can detect a negative edge
                weight cycle
            * Though negative edge weight cycle detection in Undirected graph can be done by simple Edge
                traversal & checking whether edge weight is negative or not.
        * With Positive Edges:
            * Floyd-Warshall Algorithm can be used, but Dijkstra can be used to reduce time complexity.
            * This algorithm will take V^3 time. But Dijkstra Algorithm for all V vertices will take
                "V * (V*log(V) + E)"  time.

 * For Directed Graphs:
        * With Negative Edges:
            * Can't be used with a negative edge weight cycle, but it can detect a negative edge
                weight cycle. In case of graph without negative edge weight cycle, this algo is a good choice.
            * This algorithm will take V^3 time. But another algorithm "Bellman Ford" Algorithm will
                "V * (V*E)" time for all the V vertices. It can also detect a cycle.
                Though Bellman ford algorithm is not a good choice as Number of edges can be atmost
                V*(V-1)/2. Making Time complexity in worst case to be O(V^4)
        * With Positive Edges:
            * Floyd-Warshall Algorithm can be used, but Dijkstra can be used to reduce time complexity.
            * This algorithm will take V^3 time. But Dijkstra Algorithm for all V vertices will take
                "V * (V*log(V) + E)"  time.
 */

public class FloydWarshallAlgorithm {
    /**
     * Intuition:
        * The crux of the algorithm is to pick a node, say k and use it as an intermediate
         node for every other pair of nodes, i and j in order to reach 'i' to 'j'
        * Now, picking an intermediate node can increase or decrease the overall distance, so
         make sure you always pick the minimum distance as shown below:
                         D[i][j]  =  min(D[i][j], D[i][k] + D[k][j])

     * Time Complexity: O(V^2) + O(V+E) + O(V^3) + O(V)   =   O(V ^ 3)
            * O(V^2) for initializing the Shortest path distance Adjacency matrix
            * O(V+E) for traversing over the graph adjacency list to compute the shortest paths given
                between the pair of vertices given in adjacency list
            * O(V^3) For Floyd-Warshall Algorithm to find all the pairs of the shortest paths
            * O(V) to check for Negative weighted edges cycles in the graph

     * Space Complexity: O(1)
            Ignoring output space for shortest path distance matrix
     */
    public int[][] allPairsShortestPaths_FloydWarshall(int V, ArrayList<ArrayList<int[]>> adjList){
        // Shortest path Adjacency matrix
        // "shortestPath[source][destination]" denotes the shortest path from source to destination
        int[][] shortestPath = new int[V][V];

        // Initialize the "Shortest Path Adjacency Matrix" as infinity
        // except same edges from vertex to same vertex
        for (int source = 0; source < V; source++){
            for (int destination = 0; destination < V; destination++){
                if (source == destination)
                    shortestPath[source][destination] = 0;
                else
                    shortestPath[source][destination] = Integer.MAX_VALUE;
            }
        }

        // Iterating over adjacency list to compute the shortest paths given between vertices
        // given in adjacency list
        for (int source = 0; source < V; source++) {
            for (int[] neighbour : adjList.get(source)) {
                int destination = neighbour[0];
                int pathCost = neighbour[1];
                shortestPath[source][destination] = pathCost;
            }
        }

        // One by one consider all the vertices as the intermediate vertex, and find the shortest path
        // from source to destination via this vertex as intermediate vertex (in b/w the path)
        // Try to reach from vertex 'u' to vertex 'v' via 'vertex' in between as intermediate node
        for (int vertex = 0; vertex < V; vertex++){

            // Pick all vertices as source one by one
            for (int u = 0; u < V; u++){

                // Pick all vertices as destination for the above picked source
                for (int v = 0; v < V; v++){
                    if (shortestPath[u][vertex] == Integer.MAX_VALUE || shortestPath[vertex][v] == Integer.MAX_VALUE)
                        continue;

                    if (shortestPath[u][v] > shortestPath[u][vertex] + shortestPath[vertex][v])
                        shortestPath[u][v] = shortestPath[u][vertex] + shortestPath[vertex][v];
                }
            }
        }

        // Checking for Negative Edge Weight Cycles in the graph
        // See video for this
        for (int source = 0; source < V; source++)
            if (shortestPath[source][source] < 0) {
                System.out.println("Graph Contains Negative Edge weight Cycle");
                return null;
            }

        return shortestPath;
    }
}

/**
 **************************** Order of For loop in Floyd-Warshall algorithm ****************************
 * Let intermediate vertex be 'k'
 * Order of for loop we ar following is k -> u -> v
 * This should be same & must not be changed.

 * Reason:
    * Order of loops matters here & can't be switched
    * Consider example of linked-list graph: 1 -> 2 -> 3 -> 4 -> 5
    * To find the shortest path from 1 to 5 with vertex 4 as intermediate, we need to find
        the shortest distance from 1->4 & 4->5.
        To find distance from 1->4, with 3 as intermediate, we need to find the distance 1->3 and so on.
    * However, there can be shorter routes from u to v if we consider multiple intermediate nodes as
        we have seen just before. So, the order matters here. Read Scalar Solution for this
 */