package Graphs.DetectCycleInDirectedGraph;
import java.util.ArrayList;

// https://youtu.be/uzVUw90ZFIg
// https://takeuforward.org/data-structure/detect-a-cycle-in-directed-graph-using-dfs/
// https://www.geeksforgeeks.org/detect-cycle-in-a-graph/
// https://www.geeksforgeeks.org/detect-cycle-direct-graph-using-colors/

/*
INTUITION:
    * Cycle will be present only if a Vertex is present in the recursion stack & we found it
      again somehow in DFS Recursion stack of other adjacent Vertex
    * We can visit a Vertex in the Recursion stack again, iff it was previously present in the Recursion
      stack.
    * We can visit a vertex again by calling DFS on its neighbour vertex
    * There is a cycle in a graph only if there is a back edge present in the graph.
      A back edge is an edge that is from a node to itself (self-loop) or one of its ancestors in the
      graph produced by DFS.
    * For a disconnected graph:
        * To detect cycle, check for a cycle in individual trees by checking back edges.
        * To detect a back edge, keep track of vertices currently in the recursion stack of function
          for DFS traversal.
        * If a vertex is reached that is already in the recursion stack, then there is a cycle in
          the graph. The edge that connects the current vertex to the vertex in the recursion stack
          is a back edge.
        * Use recursionStack[] or dfsOngoing[] array to keep track of vertices currently in the recursion stack.
 */

public class DetectCycleInDirectedGraph_DFS {
    /* ************************** Solution 1 : Using Two Arrays *************************************
    * We maintain two Separate arrays. One to keep track of Visited array & another to track
      whether the DFS Traversal of the current vertex is going on (in Recursion Stack) or not
    * In other words, other array checks whether the vertex is in the DFS Recursion stack or not.
      As, the "Cycle will be present only if a vertex is present in the recursion stack & we found it
      again somehow in DFS Recursion stack of other adjacent Vertex".

     * Time Complexity: O(V + E)               Same as DFS for Graph with adjacency list
     * Space Complexity: O(3 * V) = O(V)
     */

    private boolean detectCycleInDirectGraph_DFS(int vertex, ArrayList<ArrayList<Integer>> adjList, boolean[] visited, boolean[] dfsOngoing, int V){
        // Mark the current vertex as visited and also mark that vertex in recursion stack
        // (implying vertex currently is in Recursion stack)
        visited[vertex] = true;
        dfsOngoing[vertex] = true;

        // Find all the vertices which are not visited and are adjacent to the current vertex
        for (int adjacentVertex : adjList.get(vertex)){

            // If the recursive function of DFS in adjacent Vertex returns true, return true
            if (!visited[adjacentVertex]){
                if (detectCycleInDirectGraph_DFS(adjacentVertex, adjList, visited, dfsOngoing, V))
                    return true;
            }
            // If the adjacent vertices are already marked/present in the recursion stack, then return true
            else if (visited[adjacentVertex] && dfsOngoing[adjacentVertex])
                return true;

            // else condition of "visited[adjacentVertex] && !dfsOngoing[adjacentVertex]" implies
            // that the current adjacentVertex was called previously to detect cycle, but it couldn't
            // detect any cycle. So, there is no need to call DFS for current adjacentVertex again.
            // So, continue for next "adjacentVertex"
        }
        // If we couldn't detect any "Cycle in the Graph starting from the current Vertex", then remove
        // it from the Recursion Stack & hence from the array too. So, return false
        dfsOngoing[vertex] = false;
        return false;
    }

    public boolean detectCycle_DFS(int V, ArrayList<ArrayList<Integer>> adjList){
        // Boolean Array to keep track of whether the vertex is visited or not
        boolean[] visited = new boolean[V];
        // Boolean array to keep track of whether the vertex is already present in the Recursion Stack or not
        boolean[] dfsOngoing = new boolean[V];

        for (int vertex = 0; vertex < V; vertex++){
            if (!visited[vertex])
                if (detectCycleInDirectGraph_DFS(vertex, adjList, visited, dfsOngoing, V))
                    return true;
        }
        // Cycle not found in the graph
        return false;
    }





    /* ************************** Solution 2 : Using Coloring *************************************
    * Approach is same as previous solution, but we can do that the same using a single int[] array
      by using colors.
    * The idea is to do DFS of a given graph and while doing traversal, assign one of the below three colours to every vertex.
    * int[] array checks three things:
        1) whether the vertex is visited or not
        2) whether the DFS of current vertex is ongoing or not
        3) whether the vertex is in the DFS Recursion stack or not (implies DFS of current vertex is already completed)

      * COLOR 0: Vertex is not processed yet. Initially, all vertices are colored with 0. (DFS call mode made yet)
      * COLOR 1: DFS call for this vertex is ongoing (implies the vertex is in Recursion call stack for DFS)
                 DFS for this vertex has started, but not finished which means that all adjacent vertex
                 (in graph) of this vertex are not processed yet (or this vertex is in the function call stack)
      * COLOR 2: DFS call for this Vertex and all its descendants are processed (implies the vertex is not in the Recursion call
                 stack for DFS. It also implies no cycle can be found in the graph by visiting this Vertex)

      As, the "Cycle will be present only if a vertex is present in the recursion stack & we found it
      again somehow in DFS Recursion stack of other adjacent Vertex".

     * Time Complexity: O(V + E)     Same as DFS for Graph with adjacency list
     * Space Complexity: O(V)        Same as DFS for Graph with adjacency list
     */

    private boolean detectCycleInDirectGraph_DFS(int vertex, ArrayList<ArrayList<Integer>> adjList, int[] color, int V){
        color[vertex] = 1;

        for (int adjacentVertex : adjList.get(vertex)){
            // COLOR 0: Vertex is not precessed yet & not in Recursion Stack (DFS call hasn't been made yet for this vertex)
            if (color[adjacentVertex] == 0) {
                if (detectCycleInDirectGraph_DFS(adjacentVertex, adjList, color, V))
                    return true;
            }

            // COLOR 1: DFS call for this vertex is ongoing (implies the vertex is already in Recursion call stack for DFS)
            else if (color[adjacentVertex] == 1)
                return true;

            // else condition of "color[adjacentVertex] == 2" is ignored as no cycle can be found in the graph by visiting this Vertex
        }

        // COLOR 2: DFS call for this vertex is completed (implies the vertex is not in the Recursion call
        // stack for DFS. It also implies no cycle can be found in the graph by visiting this Vertex)
        color[vertex] = 2;
        return false;
    }

    public boolean detectCycle_Color_DFS(int V, ArrayList<ArrayList<Integer>> adjList){
        // Visited array to track various states of vertex in Directed graph
        int[] color = new int[V];

        for (int vertex = 0; vertex < V; vertex++)
            if (color[vertex] == 0  &&  detectCycleInDirectGraph_DFS(vertex, adjList, color, V))
                    return true;

        // Cycle not found in the graph
        return false;
    }
}
