package Graphs.BFS_BreadthFirstSearch;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

// BFS: BFS is basically traversing the adjacent vertex at first (for each & every vertex)
// https://youtu.be/UeE67iCK2lQ
// https://takeuforward.org/data-structure/breadth-first-searchbfs-level-order-traversal/
// https://www.geeksforgeeks.org/implementation-of-bfs-using-adjacency-matrix/
// https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/

public class BFS_AdjacencyList {
    /*
    * BFS uses a queue data structure for the traversal.
    * Time Complexity: O(V) + O(V + E)  =  O(V + E)
        * O(V) time to carry out BFS of every vertex if not done
        * BFS traversal will be done for all 'V' vertices. This will take O(V) time.
        * For the (current) vertex whose BFS is going on, we loop through the neighbourhood vertex.
          As adjacency list only provides us with the neighbour & adjacent vertex of the current vertex.
          So, in total if there are E edges, total time for which "for loop" will run (inside while loop)
          is O(E). So, total time complexity is O(V + E)
    * Auxiliary Space: O(V)
        * O(V) space due to visited array and Queue to maintain BFS traversal
    */
    public void bfs(int node, int V, ArrayList<ArrayList<Integer>> adjList, boolean[] visited, ArrayList<Integer> bfs){
        Queue<Integer> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(node);
        visited[node] = true;

        while (!bfsQueue.isEmpty()){
            int currentVertex = bfsQueue.remove();
            bfs.add(currentVertex);

            for (int adjacentVertex : adjList.get(currentVertex)){
                if (!visited[adjacentVertex]){
                    visited[adjacentVertex] = true;
                    bfsQueue.add(adjacentVertex);
                }
            }
        }
    }

    public void  breadthFirstSearch(int V, ArrayList<ArrayList<Integer>> adjList){
        // Assuming Vertex are in the Range [0, V-1]
        // V -> Total no. of Vertices
        boolean[] visited = new boolean[V];
        int countOfConnectedComponentsInGraph = 0;

        ArrayList<Integer> bfsTraversal = new ArrayList<>();

        // This will only do the BFS of only one component starting with vertex 0 (if there are more the one component)
        // bfs(0, V, adjList, visited, bfsTraversal);

        for (int vertex = 0; vertex < V; vertex++){
            if (!visited[vertex]){
                countOfConnectedComponentsInGraph++;
                bfs(vertex, V, adjList, visited, bfsTraversal);
            }
        }
        System.out.println("\nTotal no. of (disconnected) components in the Graph is: "+ countOfConnectedComponentsInGraph);
    }
}
