package Graphs.DFS_DepthFirstSearch;
import java.util.ArrayList;
import java.util.Stack;

// Traversal happens in the Depth Wise manner of each node
// https://youtu.be/uDWljP2PGmU
// https://takeuforward.org/data-structure/depth-first-search-dfs-traversal-graph/

// GFG Materials:
// https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/
// https://www.geeksforgeeks.org/iterative-depth-first-traversal/
// https://www.geeksforgeeks.org/implementation-of-dfs-using-adjacency-matrix/
// https://www.geeksforgeeks.org/applications-of-depth-first-search/

// Also see DFS on Matrix
// https://www.geeksforgeeks.org/depth-first-traversal-dfs-on-a-2d-array/
// https://www.geeksforgeeks.org/print-matrix-elements-using-dfs-traversal/

public class DFS_Iterative {
    /*
   * DFS uses a stack data structure for the traversal.
   * Time complexity:  O(V) + O(V + E)  =  O(V + E)
       * where V is the number of vertices and E is the number of edges in the graph.
       * O(V) time to carry out DFS of every vertex if not done
       * DFS traversal will be done for all 'V' vertices. This will take O(V) time.
       * For the (current) vertex whose DFS is going on, we loop through all the neighbourhood vertex.
         As adjacency list only provides us with the neighbour & adjacent vertex of the current vertex.
         So, in total if there are E edges, total time for which "for loop" will run (inside while loop)
         is O(E). So, total time complexity is O(V + E)
   * Space Complexity: O(V)
       * Since an extra "Visited array of size V" and "Stack space of size V" is required.
    */
    public void dfs(int vertex, boolean[] visited, ArrayList<ArrayList<Integer>> adjList, ArrayList<Integer> dfs){
        // Create a stack for DFS
        Stack<Integer> stack = new Stack<>();
        // Push the current source node
        stack.add(vertex);

        while (!stack.isEmpty()){
            // Pop a vertex from stack and to perform dfs on it
            int currentVertex = stack.pop();

            // Stack may contain same vertex twice. So we need to print the popped item only
            // if it is not visited.
            if (!visited[currentVertex]){
                dfs.add(currentVertex);
                visited[currentVertex] = true;
            }

            // Get all adjacent vertices of the popped 'vertex'. If an adjacent vertex has not been visited,
            // then push it to the stack.
            // Here, we don't mark adjacent vertex when we push it into the stack, bcoz we want Depth wise traversal.
            // So, we can't mark other adjacent vertex (while pushing into the stack) in breadth wise manner
            // If we mark other adjacent vertex (while pushing into the stack), it would be kind of BFS
            for (int adjacentVertex : adjList.get(currentVertex))
                if (!visited[adjacentVertex])
                    stack.push(adjacentVertex);

            /* // For sorted DFS traversal in sorted manner
            for (int i = adjList.get(currentVertex).size() - 1; i >= 0; i--){
                int adjacentVertex = adjList.get(currentVertex).get(i);
                if (!visited[adjacentVertex])
                    stack.add(adjacentVertex);
            }*/
        }
    }

    public ArrayList<Integer> getDFSTraversal(int V, ArrayList<ArrayList<Integer>> adjList){
        // Assuming Vertex are in the Range [0, V-1]
        // V -> Total no. of Vertices
        boolean[] visited = new boolean[V];
        int disconnectedComponents = 0;

        ArrayList<Integer> dfsTraversal = new ArrayList<>();

        // This will only do the DFS of only one component starting with vertex 0 (if there are more the one component)
        // dfs(0, V, visited, adjList, dfsTraversal);

        for (int vertex = 0; vertex < V; vertex++){
            if (!visited[vertex]){
                disconnectedComponents++;
                dfs(vertex, visited, adjList, dfsTraversal);
            }
        }
        return dfsTraversal;
    }
}
