package Graphs.TopologicalSort;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

// https://youtu.be/rZv_jHZva34
// https://takeuforward.org/data-structure/topological-sort-bfs/
// https://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/

public class TopologicalSort_BFS {
    /*
     * It means linear ordering of vertices such that there is an edge u —> v, u appears before v in
       the ordering.
     * INTUITION:
         * The question states that if there is an edge between u and v then u should appear
           before v, Which means we have to start this algo from a node that doesn't have any previous
           edges. But how to find that node that has no edge before if?
         * Here, we use the concept of in-degree (Number of edges pointing toward a node).
           We use the in-degree concept to find topological sorting
         * In the topological sort vertices with 0 in-degree appears at the first and the vertices
           with highest in-degree appears at the last.
         * In general, in topological sort, all the vertices appear in the increasing order of
           their in-degree.

     * Time Complexity : O(V + E)                Same as BFS for Graph with adjacency list
     * Space Complexity: O(2 * V) = O(V)         Same as BFS for Graph with adjacency list
     */
    public int[] topologicalSort(int V, ArrayList<ArrayList<Integer>> adjList){
        // output array for topologically sorted vertices
        int[] topologicalSort = new int[V];

        // maintain the In-degree of every vertex
        int[] inDegree = new int[V];

        // Finding the in-Degree for each vertex
        for (int vertex = 0; vertex < V; vertex++)
            for (int edgeToVertex : adjList.get(vertex))
                inDegree[edgeToVertex]++;

        // BFS Queue to be used for BFS algorithm
        Queue<Integer> bfsQueue = new ArrayDeque<>();

        // Adding all those vertices into the queue whose in-degree is 0.
        for (int vertex = 0; vertex < V; vertex++)
            if (inDegree[vertex] == 0)
                bfsQueue.add(vertex);

        int index = 0;

        while (!bfsQueue.isEmpty()){
            //  Pop out the front vertex from the Queue, add this to topological sorted array (SEE Intuition)
            int vertex = bfsQueue.remove();
            topologicalSort[index++] = vertex;

            // For all adjacent vertices of current vertex, reduces their in-degree by 1.
            // We reduce the in-degree of adjacent nodes by 1, to reduce the incoming edge dependency to the
            // adjacent vertex. Basically, we are removing the current vertex & then solving for smaller sub-graph
            // as if current vertex didn't exit
            for (int adjacentVertex : adjList.get(vertex)){
                inDegree[adjacentVertex]--;

                // And add adjacent vertices to queue if their in-degree becomes 0.
                if (inDegree[adjacentVertex] == 0)
                    bfsQueue.add(adjacentVertex);
            }
        }
        return topologicalSort;
    }
}
