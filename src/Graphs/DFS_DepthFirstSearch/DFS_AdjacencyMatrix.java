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

// Also see DFS on Matrix
// https://www.geeksforgeeks.org/depth-first-traversal-dfs-on-a-2d-array/
// https://www.geeksforgeeks.org/print-matrix-elements-using-dfs-traversal/

public class DFS_AdjacencyMatrix {
    /*
    * DFS uses a stack data structure for the traversal.
    * Time Complexity: O(V) + O(V * V)  =  O(V * V)
        * O(V) time to carry out DFS of every vertex if not done
        * For the (current) vertex whose DFS is going on, we loop through (from 1 to V) to find all the
          neighbours. This will take O(V * V) time for every unvisited vertex.
    * Auxiliary Space: O(V)
        * O(V) space due to visited array and Queue to maintain BFS traversal
    */
    public void dfs(int vertex, int[][] adjMatrix, int V, boolean[] visited, ArrayList<Integer> dfs){
        // marking current node as visited
        visited[vertex] = true;
        dfs.add(vertex);

        // getting neighbour nodes
        for (int adjacentVertex = 0; adjacentVertex < V; adjacentVertex++){
            boolean isAdjacentVertex = vertex != adjacentVertex  &&  adjMatrix[vertex][adjacentVertex] != 0;
            if (isAdjacentVertex  &&  !visited[adjacentVertex])
                dfs(adjacentVertex, adjMatrix, V, visited, dfs);
        }
    }


    public ArrayList<Integer> DFS_Traversal(int[][] adjMatrix, int V){
        // boolean array to keep track of visited vertices
        boolean[] visited = new boolean[V];
        ArrayList<Integer> dfsTraversal = new ArrayList<>();

        // This will only do the DFS of only one component starting with vertex 0 (if there are more the one component)
        // dfs(0, adjMatrix, V, visited, dfsTraversal);

        for (int vertex = 0; vertex < V; vertex++)
            if (!visited[vertex])
                dfs(vertex, adjMatrix, V, visited, dfsTraversal);

        return dfsTraversal;
    }
}
