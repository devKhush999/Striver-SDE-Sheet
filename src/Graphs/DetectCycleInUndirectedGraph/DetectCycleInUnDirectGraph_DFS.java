package Graphs.DetectCycleInUndirectedGraph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://youtu.be/Y9NFqI6Pzd4
// https://takeuforward.org/data-structure/cycle-detection-in-undirected-graph-using-dfs/
// https://www.geeksforgeeks.org/detect-cycle-undirected-graph/

public class DetectCycleInUnDirectGraph_DFS {
    /*
    * Intuition: The intuition behind this is to check for the visited element if it is found again,
                 this means the cycle is present in the given undirected graph.
                 To check the cycle condition (whether vertex already visited or not), we have to maintain
                 the parent vertex of each vertex (parent vertex is that vertex from which it is coming)
                 This is because we are checking for cycle except for adjacent vertices (adjacent vertices
                 always forms a cycle in undirected graph)
    * We do a DFS traversal of the given graph. For every visited vertex ‘v’, if there is an
      adjacent vertex ‘u’ such that 'u' is already visited and 'v' is not a parent of 'u', then there
      is a cycle in the graph. If we don’t find such an adjacent for any vertex, we say that there
      is no cycle.

     * Time Complexity : O(V + E)   Same as DFS for Graph with adjacency list
            * The program does a simple DFS Traversal of the graph which is represented using adjacency
              list. So the time complexity is O(V+E).
     * Space Complexity: O(V)       Same as DFS for Graph with adjacency list
            * To store the visited array O(V) & for "Recursion stack space" another O(V) is required.
     */
    public boolean cycleCheck_DFS(int vertex, int parentVertex, int V, ArrayList<ArrayList<Integer>> adjList, boolean[] visited){
        // Mark the current node as visited
        visited[vertex] = true;

        // Get all adjacent vertices of the vertex (on which DFS is called)
        for (int adjacentVertex : adjList.get(vertex)){

            // If any "adjacentVertex" which is already visited & it is not the Parent/Previous
            // vertex of current vertex (on which DFS is happening). Then a cycle exists.
            if (visited[adjacentVertex]  &&  adjacentVertex != parentVertex)
                return true;

            // If an adjacent vertex has not been visited, then do DFS for that adjacent vertex (as usual)
            if (!visited[adjacentVertex])
                if (cycleCheck_DFS(adjacentVertex, vertex, V, adjList, visited))
                    return true;
        }
        return false;
    }

    public boolean isCycle_DFS(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];

        // DFS will be called for every Component in the graph
        for (int vertex = 0; vertex < V; vertex++)
            if (!visited[vertex]  &&  cycleCheck_DFS(vertex, -1, V, adj, visited))
                return true;

        return false;
    }


    // Demo if creating Adjacency List without for loops
    public static void main(String[] args) {
        int V = 5;
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>(List.of(1)));
        adjList.add(new ArrayList<>(Arrays.asList(0, 2, 4)));
        adjList.add(new ArrayList<>(Arrays.asList(1, 3)));
        adjList.add(new ArrayList<>(Arrays.asList(2, 4)));
        adjList.add(new ArrayList<>(Arrays.asList(1, 3)));

        System.out.println(new DetectCycleInUnDirectGraph_DFS().isCycle_DFS(V, adjList));
    }
}
