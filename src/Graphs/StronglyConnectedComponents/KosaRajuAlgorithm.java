package Graphs.StronglyConnectedComponents;
import java.util.ArrayList;
import java.util.Stack;

// https://youtu.be/V8qIqJxCioo
// https://takeuforward.org/data-structure/kosarajus-algorithm-for-strongly-connected-componentsscc/
// MUST READ for Intuition: https://www.geeksforgeeks.org/strongly-connected-components/

/**
 * Intuition:  https://youtu.be/V8qIqJxCioo
               https://www.geeksforgeeks.org/strongly-connected-components/
        The idea behind KosaRaju’s algorithm is to do a DFS in a controlled fashion such that we won’t
        be able to go from one SCC to other SCC. One DFS call would visit all the nodes in an SCC only.

 * Time Complexity: O(3 * (V + E))  =  O(V + E)
        One O(V + E) to find the Topological ordering of vertices
        Another O(V + E) to find the transpose of the given graph
        Another O(V + E) to find the Strongly Connected Components (SCC) in the graph
 * Space Complexity: O(V + E) + O(2 * V)  =  O(V + E)
        O(V + E) space to store the transpose of the given graph
        O(V) to store the Topological ordering of vertices
        O(V) for the visited array to keep track of visited vertices in DFS
 */

public class KosaRajuAlgorithm {
    // Finding the Topological Sort Ordering of the given graph
    // BFS Topological sort method can't be used here, it won't work here
    public void dfsTopologicalSort(int vertex, ArrayList<ArrayList<Integer>> adjList, boolean[] visited, Stack<Integer> topologicalSort){
        visited[vertex] = true;

        for (int adjacentVertex : adjList.get(vertex)){
            if (!visited[adjacentVertex])
                dfsTopologicalSort(adjacentVertex, adjList, visited, topologicalSort);
        }
        // All vertices reachable from "vertex" are processed by now, push "vertex" to Stack
        // Adding all the vertices according to their finishing time/order (i.e, no new nodes to traverse)
        // This is same as Topological Sort
        topologicalSort.push(vertex);
    }


    // Simple DFS Traversal
    public void dfs(int vertex, ArrayList<ArrayList<Integer>> adjList, boolean[] visited, ArrayList<Integer> connectedComponents){
        visited[vertex] = true;
        connectedComponents.add(vertex);

        for (int adjacentVertex : adjList.get(vertex)){
            if (!visited[adjacentVertex])
                dfs(adjacentVertex, adjList, visited, connectedComponents);
        }
    }


    // Transposing or Reversing the graph, to transpose/reverse the graph we just need to reverse the
    // edges present in the graph
    public ArrayList<ArrayList<Integer>> transposeGraph(int V, ArrayList<ArrayList<Integer>> adjList){
        // Creating new Adjacency List for the Transposed Graph
        ArrayList<ArrayList<Integer>> transposedGraph = new ArrayList<>();

        for (int vertex = 0; vertex < V; vertex++)
            transposedGraph.add(new ArrayList<>());

        // Reversing the direction of Edges in the transposed graph
        // If there was an edge from 'u' -> 'v', then in transposed graph edge will be from 'v' to 'u'
        for (int vertex = 0; vertex < V; vertex++){
            for (int adjacentVertex : adjList.get(vertex)){
                transposedGraph.get(adjacentVertex).add(vertex);
            }
        }
        return transposedGraph;
    }


    public ArrayList<ArrayList<Integer>> getStronglyConnectedComponents_KosaRajuAlgorithm(int V, ArrayList<ArrayList<Integer>> adjList){
        // Visited  boolean array to keep track of visited vertices in finding topological sort
        boolean[] visited = new boolean[V];

        // Stack to store the topological sorting of the Graph
        // Since Directed graph may contain cycles, so it is exactly not the topological sorting
        // It just the ordering in the topological way
        // That's why Topological sorting for BFS can't be used here (Kahn's algo for topological sort
        // using BFS is not possible for Directed Cyclic graphs)
        // * Stack will store the vertices in the order of their finishing time/order,
        // * Bottom element in stack will be finished first (its out-degree will be minimum & in-degree will be maximum)
        // * Top element in stack will be finished at last (its out-degree will be maximum & in-degree will be minimum)
        // Recall it is same as topological sort
        Stack<Integer> topologicalSort = new Stack<>();

        // Call DSF for topological sort
        for (int vertex = 0; vertex < V; vertex++)
            if (!visited[vertex])
                dfsTopologicalSort(vertex, adjList, visited, topologicalSort);


        // Find the Transposed/Reversed graph of the given graph
        ArrayList<ArrayList<Integer>> transposedGraph = transposeGraph(V, adjList);


        // ArrayList to store the Strongly Connected Components (SCC)
        ArrayList<ArrayList<Integer>> stronglyConnectedComponents = new ArrayList<>();
        // Count of the Strongly Connected Components (SCC)
        int SCC_count = 0;

        // Visited array for DFS traversal in Transposed/Reversed graph
        visited = new boolean[V];

        // In Transposed graph,
        // Top element of the Stack will be the one with min. out-degree & max. in-degree
        // Bottom element of the Stack will be the one with max. out-degree & min. in-degree
        // Now process all vertices in order defined by Stack
        while (!topologicalSort.isEmpty()){
            // Pop a vertex from stack
            int vertex = topologicalSort.pop();

            if (!visited[vertex]){
                // If vertex is not visited, then there exists a new the Strongly Connected Component (SCC)
                ArrayList<Integer> scc = new ArrayList<>();
                stronglyConnectedComponents.add(scc);

                // Call DFS for Strongly connected component of the popped vertex
                dfs(vertex, transposedGraph, visited, scc);

                // Count of the Strongly Connected Components (SCC) will be the no. of times DFS is called in Transposed graph
                SCC_count++;
            }
        }
        return stronglyConnectedComponents;
    }
}
