package Graphs.BipartiteGraph;
import java.util.ArrayList;

// Note: In a Bipartite graph, we can color all the nodes with exactly 2 colors such that no two
// adjacent nodes have the same color
// There is also a Bookish definition of Bipartite Graph
// Note that it is possible to color a cycle graph with an even cycle using two colors
// But it is not possible to color a cycle graph with an odd cycle using two colors.

// https://youtu.be/uC884ske2uQ
// https://takeuforward.org/data-structure/bipartite-check-using-dfs-if-graph-is-bipartite/
// https://www.geeksforgeeks.org/bipartite-graph/
// https://www.geeksforgeeks.org/check-if-a-given-graph-is-bipartite-using-dfs/
// This is the classical 2-coloring Problem (Recall M-coloring Problem, Back-tracking)

public class BipartiteGraph_DFS {
    /*
     * Time Complexity: O(V + E)     Same as DFS for Graph with adjacency list
     * Space Complexity: O(V)        Same as DFS for Graph with adjacency list
     */
    public boolean isBipartite(ArrayList<Integer>[] adjList) {
        // Denoting two colors as color '1' & color '2'
        // Color '0'  means vertex has not been colored/visited yet
        int V = adjList.length;

        // Create a color array to store colors assigned to all vertices.
        // Color array basically has 3 states. It tells whether the node has been colored, if colored
        // then whether it is the 1st color or 2nd one.  color == 0 implies not colored/visited yet
        int[] color = new int[V];

        // If the current vertex is not colored before, then color it and call DFS on it
        for (int vertex = 0; vertex < V; vertex++)
            if (color[vertex] == 0) {
                color[vertex] = 1;

                if (!canDoTwoColoring_DFS(vertex, adjList, color, V))
                    return false;
            }
        return true;
    }


    public boolean canDoTwoColoring_DFS(int vertex, ArrayList<Integer>[] adjList, int[] color, int V){
        for (int adjacentVertex : adjList[vertex]){

            // If vertex u is not explored before, Mark its color opposite to the adjacent node
            if (color[adjacentVertex] == 0){
                color[adjacentVertex] = color[vertex] == 1 ? 2 : 1;

                // call DFS again on the adjacent vertex to check for Bipartite graph
                if (!canDoTwoColoring_DFS(adjacentVertex, adjList, color, V))
                    return false;
            }
            // If two adjacent vertex are colored with same color then the graph is not bipartite
            else if (color[vertex] == color[adjacentVertex])
                return false;
        }
        return true;
    }
}
