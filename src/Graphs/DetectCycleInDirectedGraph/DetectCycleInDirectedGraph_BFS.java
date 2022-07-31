package Graphs.DetectCycleInDirectedGraph;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

// https://youtu.be/V6GxfKDyLBM
// https://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/
// PRE_REQUISITE: TOPOLOGICAL SORT / KAHN'S ALGORITHM

public class DetectCycleInDirectedGraph_BFS {
    /*
    * It means linear ordering of vertices such that there is an edge u —> v, u appears before v in
      the ordering.
    * INTUITION:
        * Here, we use the concept of in-degree (Number of edges pointing toward a node).
          We use the in-degree concept to find topological sorting
        * In the topological sort vertices with 0 in-degree appears at the first and the vertices
          with highest in-degree appears at the last.
        * In general, in topological sort, all the vertices appear in the increasing order of
          their in-degree.
        * If all the vertices has been traversed in Topological sort, then cycle in graph doesn't exist.
          As Topological sort defined only for DAG. Else a cycle is present in the graph if we are not able
          to traverse all vertices in Topological sort.
        * To clarify the (countOfVisitedVertices == V) logic:
             * In Kahn's algorithm, a node only enters the queue if its in-degree becomes zero, and
               then it further decrements the in-degree values of its adjacent nodes.
             * But in the case of cyclic graph (DCG), there will be a node from where the cycle will be
               starting and then coming to an end.
             * So, in order for the elements present in the cycle to get pushed into the queue,
               that cycle starting node's in-degree must become zero. But but but, that node's in-degree
               will never become zero as one of the node from the cycle (the last element of cycle)
               will be pointing towards the starting node.
               Just imagine with a diagram.
             * So if that vertex's in-degree will never become zero, then it will restrict its
               adjacent nodes (at least) to get evaluated and hence the total number of nodes getting
               pushed into the queue (countOfVisitedVertices) will never become equal to V (= V).

    * Time Complexity : O(V + E)                Same as BFS for Graph with adjacency list
    * Space Complexity: O(2 * V) = O(V)         Same as BFS for Graph with adjacency list
    */

    public boolean detectCycleInDirectedGraph(int V, ArrayList<ArrayList<Integer>> adjList){
        // Maintain the In-degree of every vertex in an array
        int[] inDegree = new int[V];

        // Finding the in-Degree for each vertex. This step takes O(V+E) time
        for (int vertex = 0; vertex < V; vertex++)
            for (int edgeToNode : adjList.get(vertex))
                inDegree[edgeToNode]++;

        // BFS Queue to be used for BFS algorithm
        Queue<Integer> bfsQueue = new ArrayDeque<>();

        // Adding all those vertices into the queue whose in-degree is 0.
        for (int vertex = 0; vertex < V; vertex++)
            if (inDegree[vertex] == 0)
                bfsQueue.add(vertex);

        // Maintaining the count of vertices traversed in topological sort
        int countOfVisitedVertices = 0;


        while (!bfsQueue.isEmpty()){
            //  Pop out the front vertex from the Queue, this will be candidate for topological sorted array
            int vertex = bfsQueue.remove();
            countOfVisitedVertices++;

            // For all adjacent vertices of current vertex, reduces their in-degree by 1.
            for (int adjacentVertex : adjList.get(vertex)){
                inDegree[adjacentVertex]--;

                // And add adjacent vertices to queue if their in-degree becomes 0.
                if (inDegree[adjacentVertex] == 0)
                    bfsQueue.add(adjacentVertex);
            }
        }

        // If count of visited nodes is not equal to the number of nodes in the graph, then the
        // topological sort is not possible for the given graph. This implies that the Directed Graph
        // contains a Cycle.
        // If all the vertices has been traversed in Topological sort, then cycle in graph doesn't exist.
        // As Topological sort defined only for DAG. Else we found a cycle in the graph.
        return !(countOfVisitedVertices == V);
    }
}
