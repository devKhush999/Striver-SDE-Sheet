package Graphs.DetectCycleInUndirectedGraph;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

// https://youtu.be/A8ko93TyOns
// https://takeuforward.org/data-structure/detect-a-cycle-in-undirected-graph-breadth-first-search/
// https://www.geeksforgeeks.org/detect-cycle-undirected-graph/

public class DetectCycleInUnDirectGraph_BFS {
    /*
    * Intuition: The intuition behind this is to check for the visited element if it is found again,
                 this means the cycle is present in the given undirected graph.
                 To check the cycle condition (whether vertex already visited or not), we have to maintain
                 the parent vertex of each vertex (parent vertex is that vertex from which it is coming)
                 This is because we are checking for cycle except for adjacent vertices (adjacent vertices
                 always forms a cycle in undirected graph)
    * We do a BFS traversal of the given graph. For every visited vertex ‘v’, if there is an
      adjacent vertex ‘u’ such that 'u' is already visited and 'v' is not a parent of 'u', then there
      is a cycle in the graph. If we don’t find such an adjacent for any vertex, we say that there
      is no cycle.
    * Time Complexity: O(V + E)     Same as BFS for Graph with adjacency list
    * Space Complexity: O(V)        Same as BFS for Graph with adjacency list
     */
    public boolean cycleCheck_BFS(int vertex, int V, ArrayList<ArrayList<Integer>> adjList, boolean[] visited){
        Queue<VertexPair> queue = new ArrayDeque<>();

        // Mark the current node as visited and enqueue it
        queue.add(new VertexPair(vertex, -1));
        visited[vertex] = true;

        while (!queue.isEmpty()){
            // Dequeue a vertex from queue (and print it, in BFS)
            VertexPair currVertex = queue.remove();

            // Get all adjacent vertices of the dequeued vertex u.
            for (int adjacentVertex : adjList.get(currVertex.vertex)){
                // If any "adjacentVertex" which is already visited & it is not the Parent/Previous
                // vertex of "currentVertex". Then a cycle exists.
                if (visited[adjacentVertex]  &&  adjacentVertex != currVertex.parentVertex)
                    return true;

                // If an adjacent vertex has not been visited, then mark it visited and enqueue it.
                // We also mark parent so that parent is not considered for cycle.
                if (!visited[adjacentVertex]){
                    queue.add(new VertexPair(adjacentVertex, currVertex.vertex));
                    visited[adjacentVertex] = true;
                }
            }
        }
        return false;
    }

    public boolean isCycle_BFS(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];

        // BFS will be called for every Component in the graph
        for (int vertex = 0; vertex < V; vertex++)
            if (!visited[vertex]  &&  cycleCheck_BFS(vertex, V, adj, visited))
                return true;

        return false;
    }

    // Class to store the Vertex & its Parent Vertex (Vertex from which it came from in BFS Traversal)
    static class VertexPair{
        int vertex, parentVertex;
        public VertexPair(int vertex, int parentVertex) {
            this.vertex = vertex;
            this.parentVertex = parentVertex;
        }
    }
}
