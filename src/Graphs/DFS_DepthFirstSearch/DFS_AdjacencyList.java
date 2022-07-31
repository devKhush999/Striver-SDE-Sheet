package Graphs.DFS_DepthFirstSearch;
import java.util.ArrayList;

// Traversal happens in the Depth Wise manner of each node
// https://youtu.be/uDWljP2PGmU
// https://takeuforward.org/data-structure/depth-first-search-dfs-traversal-graph/

// GFG Materials:
// https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/
// https://www.geeksforgeeks.org/iterative-depth-first-traversal/
// https://www.geeksforgeeks.org/implementation-of-dfs-using-adjacency-matrix/
// https://www.geeksforgeeks.org/applications-of-depth-first-search/

public class DFS_AdjacencyList {
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
        * Since an extra "Visited array of size V" and "Recursion stack space of size V" is required.
        * Recursion stack space is of size 'V' as V will be the max. depth of recursive calls
     */
    public void dfs(int vertex, boolean[] visited, ArrayList<ArrayList<Integer>> adjList, ArrayList<Integer> dfs){
        // marking current node as visited
        dfs.add(vertex);
        visited[vertex] = true;

        // getting neighbour nodes
        for (int adjacentVertex : adjList.get(vertex)){
            if (!visited[adjacentVertex])
                dfs(adjacentVertex, visited, adjList, dfs);
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
