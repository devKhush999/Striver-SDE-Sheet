package Graphs.BipartiteGraph;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

// Note: In a Bipartite graph, we can color all the nodes with exactly 2 colors such that no two
// adjacent nodes have the same color
// There is also a Bookish definition of Bipartite Graph
// Note that it is possible to color a cycle graph with an even cycle using two colors
// But it is not possible to color a cycle graph with an odd cycle using two colors.

// https://youtu.be/nbgaEu-pvkU
// https://takeuforward.org/data-structure/bipartite-check-using-dfs-if-graph-is-bipartite/
// https://www.geeksforgeeks.org/bipartite-graph/
// https://www.geeksforgeeks.org/check-if-a-given-graph-is-bipartite-using-dfs/
// This is the classical 2-coloring Problem (Recall M-coloring Problem, Back-tracking)

public class BipartiteGraph_BFS {
    /*
     * Time Complexity: O(V + E)     Same as BFS for Graph with adjacency list
     * Space Complexity: O(V)        Same as BFS for Graph with adjacency list
    */
    public boolean isBipartite(ArrayList<Integer>[] adjList) {
        // Denoting two colors as color '1' & color '2'
        // Color '0'  means vertex has not been colored/visited yet
        int V = adjList.length;

        // Create a color array to store colors assigned to all vertices.
        // Color array basically has 3 states. It tells whether the node has been colored, if colored
        // then whether it is the 1st color or 2nd one.  color == 0 implies not colored/visited yet
        int[] color = new int[V];

        for (int vertex = 0; vertex < V; vertex++)
            if (color[vertex] == 0)
                if (!canDoTwoColoring_BFS(vertex, color, adjList))
                    return false;

        return true;
    }

    public boolean canDoTwoColoring_BFS(int vertex, int[] color, ArrayList<Integer>[] adjList){
        Queue<Integer> queue = new ArrayDeque<>();

        // Color the start vertex with color '1'
        color[vertex] = 1;
        queue.add(vertex);

        while (!queue.isEmpty()){
            int currentVertex = queue.remove();

            // Find all adjacent vertices
            for (int adjacentVertex : adjList[currentVertex]){

                // An edge from 'u' to 'v' exists and destination 'v' is not colored
                if (color[adjacentVertex] == 0){
                    // Assign alternate color to this adjacent vertex 'v' of 'u'
                    color[adjacentVertex] = color[currentVertex] == 1 ? 2 : 1;
                    queue.add(adjacentVertex);
                }
                // An edge from 'u' to 'v' exists and adjacent vertex 'v' is colored with same color as 'u'
                else if (color[adjacentVertex] == color[currentVertex])
                    return false;
            }
        }
        // If traversed all vertex of the Graph by BFS, and all adjacent vertices can be colored with
        // alternate color, then graph is Bipartite
        return true;
    }
}
