package Graphs.GraphQuestions.CloneGraph;
import java.util.*;

// https://youtu.be/f2EfGComRKM
// https://www.geeksforgeeks.org/clone-an-undirected-graph/

public class CloneGraph_BFS {
    /************************************ BFS Solution 1 **********************************************
     * Time Complexity:  O(V + E)
        * BFS Algorithm is modified
     * Space Complexity: O(2 * V) = O(V)
        * O(V) for BFS Queue to store the vertices in original Graph
        * O(V) for BFS Visited HashMap
     */
    public GraphNode cloneGraph_BFS1(GraphNode node) {
        // if the actual node is empty there is nothing to copy, so return null
        if (node == null)
            return null;

        // Instead of Visited array, we create a Visited HashMap.
        // Just think of Visited boolean array as Visited Boolean HashMap.
        // Though We will create an HashMap of Node (and not Boolean) why ?, because we have to store all the
        // copied nodes (in Graph) of particular vertex, whether it's visited or not.
        // This helps to store all the copied nodes in Graph.
        // So, initially in Visited HashMap, nothing is stored indicating no nodes have been visited yet.
        // If any node is visited, we will store the respective Copied node at the index into the
        // visited HashMap, and it can be retrieved that easily.
        HashMap<Integer, GraphNode> visited = new HashMap<>();

        // Copy the source node (start node) in New Graph into visited hashmap
        int startVertex = node.value;
        visited.put(startVertex, new GraphNode(startVertex));

        // Normal BFS Queue. Queue will contains the vertices in original Graph
        Queue<GraphNode> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(node);

        // Normal BFS Algorithm
        while (!bfsQueue.isEmpty()){
            // Remove the front vertex in Queue
            GraphNode vertexInOldGraph = bfsQueue.remove();
            // Retrieve the corresponding copied Vertex from the Visited HashMap
            GraphNode vertexInNewGraph = visited.get(vertexInOldGraph.value);

            // Traverse all the neighbour of current Vertex
            for (GraphNode neighbour : vertexInOldGraph.neighbors){
                // If the adjacent vertex is not present in Visited HashMap, it means it hasn't been visited yet
                // Add it into the Visited HashMap (add a new Node()) indicating Neighbour has been
                // visited & considered in BFS
                // Also add the neighbour into the BFS Queue
                if (!visited.containsKey(neighbour.value)){
                    visited.put(neighbour.value, new GraphNode(neighbour.value));
                    bfsQueue.add(neighbour);
                }

                // Make the connection in the new Graph
                // Add all the adjacent vertex (in New Graph) to the "neighbours" list of current vertex (in New Graph)
                // Irrespective of whether the adjacent vertex is visited or not
                GraphNode adjacentInNewGraph = visited.get(neighbour.value);
                vertexInNewGraph.neighbors.add(adjacentInNewGraph);
            }
        }
        // return the start Vertex in new Graph
        return visited.get(startVertex);
    }

    /*********************************** BFS Solution 2 *******************************************
     *  Array Hashing Solution of the Same Solution
     *  Recall Hashing using Arrays are Faster
     *  Maximum vertex value is with value 100 as per constraints, So Visited array is of size 100

     * Time Complexity:  O(V + E)
        * BFS Algorithm is modified
     * Space Complexity: O(2 * V) = O(V)
        * O(V) for BFS Queue to store the vertices in original Graph
        * O(V) for BFS Visited Array
     */
    public GraphNode cloneGraph_BFS2(GraphNode node) {
        // if the actual node is empty there is nothing to copy, so return null
        if (node == null)
            return null;

        // We will create an array of Node (and not Boolean) why ?, because we have to store all the
        // copied nodes (in Graph) of particular vertex, whether it's visited or not, so in the Node[]
        // visited array, initially null is stored indicating no nodes have been visited yet.
        // If any node is visited, we will store the respective (copied) node at the index, and it can
        // be retrieved easily
        GraphNode[] visited = new GraphNode[101];

        // Copy the source node (start node) in New Graph into visited array
        int startVertex = node.value;
        visited[startVertex] = new GraphNode(startVertex);

        // Normal BFS Queue. Queue will contains the vertices in original Graph
        Queue<GraphNode> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(node);

        // Normal BFS Algorithm
        while (!bfsQueue.isEmpty()){
            // Remove the front vertex in Queue
            GraphNode vertex = bfsQueue.remove();
            // Retrieve the corresponding copied Vertex from the Visited array
            GraphNode vertexInNewGraph = visited[vertex.value];

            // Traverse all the neighbour of current Vertex
            for (GraphNode neighbour : vertex.neighbors){
                // If the adjacent vertex is not present in Visited Array, it means it hasn't been visited yet
                // Add it into the Visited Array (add a new Node()) indicating Neighbour has been
                // visited & considered in BFS
                // Also add the neighbour into the BFS Queue
                if (visited[neighbour.value] == null){
                    visited[neighbour.value] = new GraphNode(neighbour.value);
                    bfsQueue.add(neighbour);
                }

                // Make the connection in the new Graph
                // Add all the adjacent vertex (in New Graph) to the "neighbours" list of current vertex (in New Graph)
                // Irrespective of whether the adjacent vertex is visited or not
                GraphNode adjacentInNewGraph = visited[neighbour.value];
                vertexInNewGraph.neighbors.add(adjacentInNewGraph);
            }
        }
        // return the start Vertex in new Graph
        return visited[startVertex];
    }


    // Graph Node
    static class GraphNode {
        public int value;
        public List<GraphNode> neighbors;
        public GraphNode(int val) {
            this.value = val;
            neighbors = new ArrayList<>();
        }
    }
}