package Graphs.ShortestPathInGraph.ShortestPath_BellmanFordAlgorithm;
import java.util.ArrayList;
import java.util.Arrays;

// https://youtu.be/75yC1vbS8S8
// Must Read: https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/
// Must Read: https://www.scaler.com/topics/data-structures/bellman-ford-algorithm/

/**
 * Dijkstra Algorithm fails when the edges have negative weights.
 * In case of negative edges, Bellman-Ford Algorithm can be used to determine the Single source
     shortest paths, iff there are no cycles with (net) negative weight (sum of all edge weights
     involved in cycle should not be negative)
 * This concludes that Bellman Ford Algorithm can never be used in case of Undirected Graph.
     Bcoz in Undirected Graph, every edge is Bi-directional (every edge is a cycle b/w two vertex)

 * But but but Bellman Ford Algorithm can be used to determine if there exist any cycle with (net)
     negative weight both in case of Undirected Graph & Directed Graph
 * Though to detect any cycle with (net) negative weight in Undirected Graph, simple Adjacency list
     traversal can be done in O(V + E) time instead of Bellman Ford Algorithm (that takes O(V*E) time).
 */

public class BellmanFordAlgorithm {
    /** ********************************* Bellman-Ford Algorithm ****************************************
     * Time complexity: O(V * E)
          * We run a for loop "V - 1" times, to relax all the 'E' edges in graph
          * Why "V - 1"?
          * For a given graph with V nodes, the maximum number of edges we could have is "V - 1"
            in a single path. In other words, a simple shortest path from src to any other vertex can
            have at-most |V| - 1 edges
            There can be many such Single Paths in graphs, with every path having
            atmost "V - 1" edges. So "V-1" ensures every path is covered.
            Moreover, our adjacency list might be in such a manner that only one
            node is updated in a single pass. Thus, to try out all nodes, we would require
            atleast V-1 iterations
     * Space Complexity: O(E)
          * To store all the edges
     */
    // Finds the shortest distances from src to all other vertices using Bellman-Ford algorithm. The
    // function can also detect negative weight cycle
    public int[] bellmanFordAlgorithm(int sourceVertex, int V, ArrayList<ArrayList<int[]>> adjList){

        // Convert Adjacency list to the List of Edges
        ArrayList<Edge> edges = convertAdjacencyListToEdges(V, adjList);

        // Initialize the distances from the source to all vertices as infinite and distance to the source
        // itself as 0.
        int[] shortestPath = new int[V];
        Arrays.fill(shortestPath, (int) 1e9);
        shortestPath[sourceVertex] = 0;

        // “Relaxing Edges” where we would update our distance array if we find a better Distance from
        // source for current destination vertex
        // We would do this N-1 times.
        // Each iteration guarantees to give all shortest paths which are at most 'i' edge long
        for (int i = 1; i <= V -1; i++){
            for (Edge edge : edges){
                if (shortestPath[edge.endVertex] > shortestPath[edge.startVertex] + edge.weight)
                    shortestPath[edge.endVertex] = shortestPath[edge.startVertex] + edge.weight;
            }
        }
        return shortestPath;
    }

    // Convert Adjacency list to the List of Edges
    private ArrayList<Edge> convertAdjacencyListToEdges(int V, ArrayList<ArrayList<int[]>> adjList){
        ArrayList<Edge> edges = new ArrayList<>();

        for (int vertex = 0; vertex < V; vertex++){
            for (int[] neighbour : adjList.get(vertex)){
                int endVertex = neighbour[0];
                int edgeWeight = neighbour[1];
                edges.add(new Edge(vertex, endVertex, edgeWeight));
            }
        }
        return edges;
    }

    // *****************************  Structure of the Edges of the Graph  *****************************
    // "Edge" Structure for Directed graph, for Undirected Graph see KosaRaju Algorithm (MST)
    static class Edge{
        int startVertex, endVertex, weight;

        public Edge(int startVertex, int endVertex, int edgeWeight) {
            this.startVertex = startVertex;
            this.endVertex = endVertex;
            this.weight = edgeWeight;
        }
    }
}
