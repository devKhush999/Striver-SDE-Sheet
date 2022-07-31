package Graphs.GraphQuestions.CloneGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// https://youtu.be/f2EfGComRKM
// https://www.geeksforgeeks.org/clone-an-undirected-graph/

public class CloneGraph_DFS {
    /************************************ DFS Solution 1 **********************************************
     * Time Complexity:  O(V + E)
        * DFS Algorithm is modified
     * Space Complexity: O(2 * V) = O(V)
        * O(V) for DFS Recursion Stack Space
        * O(V) for DFS Visited HashMap
     */
    public void dfs(GraphNode vertex, HashMap<Integer, GraphNode> visited){
        // Mark the Vertex to be Visited in the Visited HashMap
        visited.put(vertex.value, new GraphNode(vertex.value));

        // Retrieve the corresponding copied Vertex (in new Graph) from the Visited HashMap
        // It will be stored in Visited Hashmap
        GraphNode vertexInNewGraph = visited.get(vertex.value);

        // Traverse all the neighbour of current Vertex
        for (GraphNode neighbour : vertex.neighbors){
            // If the adjacent vertex is not present in Visited Array, it means it hasn't been visited yet
            // So as usual make a DFS call for the adjacent unvisited Neighbour vertex
            // visited & considered in DFS
            if (!visited.containsKey(neighbour.value)){
                dfs(neighbour, visited);
            }

            // Make the connection in the new Graph
            // Add all the adjacent vertex (in New Graph) to the "neighbours" list of current vertex (in New Graph)
            // Irrespective of whether the adjacent vertex is visited or not
            GraphNode adjacentInNewGraph = visited.get(neighbour.value);
            vertexInNewGraph.neighbors.add(adjacentInNewGraph);
        }
    }

    public GraphNode cloneGraph_DFS1(GraphNode node) {
        // if the actual node is empty there is nothing to copy, so return null
        if (node == null) return null;

        // Instead of Visited array, we create a Visited HashMap.
        // Just think of Visited Boolean array as Visited Boolean HashMap.
        // Though We will create an HashMap of Node (and not Boolean) why ?, because we have to store all the
        // copied nodes (in Graph) of particular vertex, whether it's visited or not.
        // This helps to store all the copied nodes in Graph.
        // So, initially in Visited HashMap, nothing is stored indicating no nodes have been visited yet.
        // If any node is visited, we will store the respective (copied) node at the index into the
        // visited HashMap, it can be retrieved easily.
        HashMap<Integer, GraphNode> visited = new HashMap<>();

        // Call the DFS for the starting given vertex (with the visited array)
        dfs(node, visited);

        // return the start Vertex in new Graph
        int startVertex = node.value;
        return visited.get(startVertex);
    }

    /*********************************** DFS Solution 2 *******************************************
     *  Array Hashing Solution of the Same Solution
     *  Recall Hashing using Arrays are Faster
     *  Maximum vertex value is with value 100 as per constraints, So Visited array is of size 100

     * Time Complexity:  O(V + E)
        * DFS Algorithm is modified
     * Space Complexity: O(2 * V) = O(V)
        * O(V) for DFS Recursion Stack Space
        * O(V) for DFS Visited Array
     */
    public void dfs(GraphNode vertex, GraphNode[] visited){
        // Mark the Vertex to be Visited in the Visited Array
        visited[vertex.value] = new GraphNode(vertex.value);

        // Retrieve the corresponding copied Vertex (in new Graph) from the Visited Array
        // It will be stored in Visited Array
        GraphNode vertexInNewGraph = visited[vertex.value];

        // Traverse all the neighbour of current Vertex
        for (GraphNode neighbour : vertex.neighbors){
            // If the adjacent vertex is not present in Visited Array, it means it hasn't been visited yet
            // So as usual make a DFS call for the adjacent unvisited Neighbour vertex
            // visited & considered in DFS
            if (visited[neighbour.value] == null){
                dfs(neighbour, visited);
            }

            // Make the connection in the new Graph
            // Add all the adjacent vertex (in New Graph) to the "neighbours" list of current vertex (in New Graph)
            // Irrespective of whether the adjacent vertex is visited or not
            GraphNode adjacentInNewGraph = visited[neighbour.value];
            vertexInNewGraph.neighbors.add(adjacentInNewGraph);
        }
    }

    public GraphNode cloneGraph_DFS2(GraphNode node) {
        // if the actual node is empty there is nothing to copy, so return null
        if (node == null) return null;

        // We will create an array of Node (and not Boolean) why ?, because we have to store all the
        // copied nodes (in Graph) of particular vertex, whether it's visited or not, so in the Node[]
        // visited array, initially null is stored indicating no nodes have been visited yet.
        // If any node is visited, we will store the respective (copied) node at the index, and
        // it can be retrieved easily.
        GraphNode[] visited = new GraphNode[101];

        // Call the DFS for the starting given vertex (with the visited array)
        dfs(node, visited);

        // return the start Vertex in new Graph
        int startVertex = node.value;
        return visited[startVertex];
    }


    // Graph Node
    static class GraphNode {
        public int value;
        public List<GraphNode> neighbors;
        public GraphNode(int value) {
            this.value = value;
            neighbors = new ArrayList<>();
        }
    }
}