package Graphs.TopologicalSort;
import java.util.ArrayList;
import java.util.Stack;

// Note: Topological Sort is defined only for DAG (Directed Acyclic Graph)
// https://youtu.be/Yh6EFazXipA
// https://takeuforward.org/data-structure/topological-sort-using-dfs/
// https://www.geeksforgeeks.org/topological-sorting/

public class TopologicalSort_DFS {
    /* ************************************ Intuition ************************************
     * It means linear ordering of vertices such that there is an edge u —> v, u appears before v in the ordering.
     * There can be multiple Topological sorts order for the given graph, but the condition should be
       if there is an edge u -> v then u should always appear before v.
     * Topological Sorting is applicable only for DAG(Directed Acyclic Graph). Why is it so?
          Because of the following reasons:
            * For Undirected graphs ,only u -> v is not applicable. It cannot be sure whether the
              edge is between u to v oOR v to u
            * In a cyclic graph there will always be a dependency factor. You cannot make sure that
              you can have linear ordering of vertices.

     * Main Intuition: Just because there was an edge from 'u' to 'v'. Dfs call will go from 'u' to 'v'.
          The neighbour vertex's DFS (dfs(v)) will get over first and then dfs(u) will be executed.
          Here we are making sure that if u -> v, then we will first push v into the stack and
          then u will be pushed. This is how Topological order is maintained in the Stack.

     * In DFS, we start from a vertex, we first print it and then recursively call DFS for its adjacent vertices.
       In topological sorting, we use a temporary stack. We don’t print the vertex immediately,
       we first recursively call topological sorting for all its adjacent vertices, then push it to a stack.
       Finally, print contents of the stack.
       Note that a vertex is pushed to stack only when all of its adjacent vertices (and their adjacent
       vertices and so on) are already in the stack. This is how Topological order is maintained in the Stack.

     * Time Complexity : O(V + E)               Same as DFS for Graph with adjacency list
     * Space Complexity: O(3 * V) = O(V)
     */
    private void topologicalSort_DFS(int vertex, ArrayList<ArrayList<Integer>> adjList, Stack<Integer> topologicalSortedStack, boolean[] visited){
        // Mark the current node as visited
        visited[vertex] = true;

        // Iterate for all the adjacent nodes of 'v'. If any adjacent node to 'v' is not visited,
        // call topologicalSort function on it.
        for (int adjacentVertex : adjList.get(vertex)){
            if (!visited[adjacentVertex])
                topologicalSort_DFS(adjacentVertex, adjList, topologicalSortedStack, visited);
        }

        // Pushing current vertex to stack (only after when all of its adjacent vertices and their
        // adjacent vertices and so on are already in the stack)
        topologicalSortedStack.push(vertex);
    }

    public int[] topologicalSort(int V, ArrayList<ArrayList<Integer>> adjList){
        boolean[] visited = new boolean[V];

        // Topological Sorted stack (from top to bottom)
        Stack<Integer> topologicalSortedStack = new Stack<>();

        for (int vertex = 0; vertex < V; vertex++)
            if (!visited[vertex])
                topologicalSort_DFS(vertex, adjList, topologicalSortedStack, visited);

        // Converting topological sorted stack into an array
        int[] topologicalSort = new int[topologicalSortedStack.size()];
        for (int i = 0; i < topologicalSort.length; i++)
            topologicalSort[i] = topologicalSortedStack.pop();

        return topologicalSort;
    }
}
