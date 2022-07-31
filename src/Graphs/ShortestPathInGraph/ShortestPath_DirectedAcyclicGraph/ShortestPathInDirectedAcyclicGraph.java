package Graphs.ShortestPathInGraph.ShortestPath_DirectedAcyclicGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

// PRE-REQUISITE: DIJKSTRA ALGORITHM
// BFS Solution: https://youtu.be/jbhuqIASjoM

// DAG's always have a topological sort Solution
// Topological Sort Solution:
// https://youtu.be/CrxG4WJotgg
// https://www.geeksforgeeks.org/shortest-path-for-directed-acyclic-graphs/
// https://www.scaler.com/topics/data-structures/shortest-path-in-directed-acyclic-graph/
// INTUITION: https://stackoverflow.com/questions/37253739/intuition-behind-the-algorithm-to-compute-single-source-shortest-path-for-every
// INTUITION: https://www.codingninjas.com/codestudio/library/shortest-path-in-a-directed-acyclic-graph

public class ShortestPathInDirectedAcyclicGraph {
    /* ************************************* BFS Solution *****************************************
    * Solution is same as the "Shortest path in Undirected Graphs with Unit Weights" except that past cost is
      taken into consideration.
    * Dijkstra Algorithm can be used here, as Dijkstra Algorithm works for both directed and undirected, positively weighted graphs

    * The Intuition is to use the BFS algorithm.
    * Time Complexity : O(V*log(V) + E)                Same as Dijkstra Algorithm
    * Space Complexity: O(2 * V) = O(V)                Same as Dijkstra Algorithm
    */
    public int[] shortestPathsInDAG_BFS(int V, ArrayList<ArrayList<int[]>> adjList, int source){
        int[] shortestPath = new int[V];

        Arrays.fill(shortestPath, Integer.MAX_VALUE);
        shortestPath[source] = 0;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        minHeap.add(new int[]{source, 0});

        while (!minHeap.isEmpty()){
            int currVertex  = minHeap.remove()[0];

            for (int[] neighbour : adjList.get(currVertex)){
                int adjacentVertex = neighbour[0];
                int pathCost = neighbour[1];

                if (shortestPath[adjacentVertex] > shortestPath[currVertex] + pathCost){
                    shortestPath[adjacentVertex] = shortestPath[currVertex] + pathCost;
                    minHeap.add(new int[]{adjacentVertex, shortestPath[adjacentVertex]});
                }
            }
        }
        return shortestPath;
    }


    /* ************************************ Efficient Topological Sort Solution **************************************
     * In this solution, fewer comparisons are made as compared to BFS/DFS solution.
     * PRE_REQUISITE: "Topological Sort"
     * The Intuition is to use the Topological Sort Algorithm.
     *
     * Time Complexity: O(V + E)
       For a graph G=(V,E) time taken to find the topological ordering is O(V+E). After that, for
       every vertex V we run a loop to its adjacent vertices.
       So time taken in this step is also O(V + E). Hence, the overall time complexity is O(V+E)
     * Space Complexity: O(3 * V) = O(V)         Same as DFS for Graph with adjacency list
     *
     * Conclusion: In the case of the Directed Acyclic Graphs (DAG), finding the topological ordering
       of the vertices can help us find the single source shortest paths in O(V+E) time.
       Unlike the Bellman Ford algorithm which takes O(V\times E)O(V×E) time to calculate the same.
     */
    public int[] shortestPathsInDAG_TopologicalSort(int source, int V, ArrayList<ArrayList<int[]>> adjList){
        // Visited array for Topological Sort
        boolean[] visited = new boolean[V];

        // Topological Sorted stack (from top to bottom)
        Stack<Integer> topoSortStack = new Stack<>();

        // Finding Topological Sort of given graph
        for (int vertex = 0; vertex < V; vertex++)
            if (!visited[vertex])
                topologicalSort_DFS(vertex, adjList, topoSortStack, visited);


        // Array to store "Shortest path from source to the nodes"
        int[] shortestPath = new int[V];

        // Initialize distances to all vertices as infinite and distance to source as 0
        Arrays.fill(shortestPath, Integer.MAX_VALUE);
        shortestPath[source] = 0;

        // Process all the vertices in Graph in topological order
        // Here, processing a vertex means, updating distances of its adjacent vertices using the distance
        // of the current vertex from start.
        // If there are some vertices in the ordering before the ‘src’ vertex, they are not reachable from the
        // ‘src’ node, hence no need to update their distances from the ‘src’ node.
        while (!topoSortStack.isEmpty()){
            // Get the next vertex from topological order
            int currVertex = topoSortStack.pop();

            // Update distances of all adjacent vertices if those vertices have been previously visited
            // We will check if shortestPath[from source to vertex] != INF, which means if vertex is reachable from source
            if (shortestPath[currVertex] != Integer.MAX_VALUE){
                for (int[] neighbour : adjList.get(currVertex)){
                    int adjacentVertex = neighbour[0];
                    int pathCost = neighbour[1];

                    if (shortestPath[adjacentVertex] > shortestPath[currVertex] + pathCost)
                        shortestPath[adjacentVertex] = shortestPath[currVertex] + pathCost;
                }
            }
        }
        return shortestPath;
    }

    // DFS Algorithm for Topological Sort for DAGs
    public void topologicalSort_DFS(int vertex, ArrayList<ArrayList<int[]>> adjList, Stack<Integer> stack, boolean[] visited){
        visited[vertex] = true;

        for (int[] adjacentVertex : adjList.get(vertex)){
            if (!visited[adjacentVertex[0]])
                topologicalSort_DFS(adjacentVertex[0], adjList, stack, visited);
        }
        stack.push(vertex);
    }
}

/******************************** INTUITION BEHIND TOPOLOGICAL SORT SOLUTION ******************************
 * 1) Finding the shortest path to a vertex is easy if you already know the shortest paths to all the
      vertices that can precede it. Finding the longest path to a vertex in DAG is easy if you already
      know the longest path to all the vertices that can precede it.
      Processing the vertices in topological order ensures that by the time you get to a vertex,
      you've already processed all the vertices that can precede it.

    IMP: Dijkstra's algorithm is necessary for graphs that can contain cycles, because they can't be
    topologically sorted.

 * 2) Bellman-Ford algorithm, which can be used here to compute the shortest distances from a given
      ‘src’ node to all nodes in O(V*E).
      Note that if you are trying to use Dijkstra here, it won’t work because the weighted DAG can
      also have negative weights.
      Can we think of some better algorithm other than the Bellman-Ford algorithm?
      If we can have an ordering of vertices, all nodes that are not reachable from the ‘src’ node
      are kept on the left side of the ‘src’ node, and all reachable nodes are kept on its right.
      Why are we looking for such an order? Because we will not have to update the shortest path of
      the unreachable nodes.
      Another advantage is that from a vertex, we can relax all the edges connecting the other
      vertices. This process can be done for all vertices in this ordering one by one for all
      their respective edges.
      The intuition behind this is that, since we will find the shortest distance from the ‘src’ node,
      the ‘src’ node will be present in this ordering before all nodes are reachable from the ‘src’ node. So all vertices which form edges with ‘src’ nodes will get updated first. Then all the other edges will get relaxed one by one while visiting the vertices in this ordering.
      This will find the shortest distance from the ‘src’ node to all nodes.
      The ordering which we have been using in this discussion is the Topological sorted ordering
      of a graph.

 */
