package Graphs.BFS_BreadthFirstSearch;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

// BFS: BFS is basically traversing the adjacent vertex at first (for each & every vertex)
// https://youtu.be/UeE67iCK2lQ
// https://takeuforward.org/data-structure/breadth-first-searchbfs-level-order-traversal/
// https://www.geeksforgeeks.org/implementation-of-bfs-using-adjacency-matrix/
// https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/

public class BFS_AdjacencyMatrix {
    /*
    * BFS uses a queue data structure for the traversal.
    * Time Complexity: O(V) + O(V * V)  =  O(V*V)
        * O(V) time to carry out BFS of every vertex if not done
        * For the (current) vertex whose BFS is going on, we loop through (from 1 to V) to find all the
          neighbours. This will take O(V*V) time for every unvisited vertex.
    * Auxiliary Space: O(V)
        * O(V) space due to visited array and Queue to maintain BFS traversal
    */
    // Version 1 of BFS
    public void bfs(int node, int V, int[][] adjMatrix, boolean[] visited, ArrayList<Integer> bfs){
        Queue<Integer> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(node);
        visited[node] = true;

        while (!bfsQueue.isEmpty()){
            int size = bfsQueue.size();

            for (int i = 1; i <= size; i++){
                int currentVertex = bfsQueue.remove();
                bfs.add(currentVertex);

                for (int vertex = 0; vertex < V; vertex++) {
                    boolean isAdjacentNeighbourVertex = adjMatrix[currentVertex][vertex] != 0;
                    if (isAdjacentNeighbourVertex && !visited[vertex]) {
                        visited[vertex] = true;
                        bfsQueue.add(vertex);
                    }
                }
            }
        }
    }

    // Version 2 of BFS. Here we don't need to maintain the count of Vertex at current BFS level
    public void bfs_(int node, int V, int[][] adjMatrix, boolean[] visited, ArrayList<Integer> bfs){
        Queue<Integer> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(node);
        visited[node] = true;

        while (!bfsQueue.isEmpty()){
            int currentVertex = bfsQueue.remove();
            bfs.add(currentVertex);

            for (int vertex = 0; vertex < V; vertex++) {
                boolean isAdjacentNeighbourVertex = adjMatrix[currentVertex][vertex] != 0;
                if (isAdjacentNeighbourVertex && !visited[vertex]) {
                    visited[vertex] = true;
                    bfsQueue.add(vertex);
                }
            }
        }
    }


    public void breadthFirstSearch(int[][] adjMatrix, int V){
        // Assuming Vertex are in the Range [0, V-1]
        // V -> Total no. of Vertices
        boolean[] visited = new boolean[V];
        int countOfConnectedComponentsInGraph = 0;

        ArrayList<Integer> bfsTraversal = new ArrayList<>();

        // This will only do the BFS of only one component starting with vertex 0 (if there are more the one component)
        // bfs(0, V, adjMatrix, visited, bfsTraversal);

        for (int vertex = 0; vertex < V; vertex++){
            if (!visited[vertex]){
                countOfConnectedComponentsInGraph++;
                bfs(vertex, V, adjMatrix, visited, bfsTraversal);
            }
        }
        System.out.println("\nTotal no. of (disconnected) components in the Graph is: "+ countOfConnectedComponentsInGraph);
    }
}
