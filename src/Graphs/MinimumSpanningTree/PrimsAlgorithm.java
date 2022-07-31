package Graphs.MinimumSpanningTree;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

// MST Introduction:                https://youtu.be/xsM8i0jVF1w
// Prims Algorithm Theory:          https://youtu.be/HnD676J56ak
// Prims Algorithm Implementation:  https://youtu.be/8KPEROaLK-0

// MST Introduction
// https://takeuforward.org/data-structure/minimum-spanning-tree-mst-using-prims-algo/
// https://www.geeksforgeeks.org/properties-of-minimum-spanning-tree-mst/
// https://www.geeksforgeeks.org/applications-of-minimum-spanning-tree/

// Prim Algorithm
// https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/
// https://www.geeksforgeeks.org/prims-mst-for-adjacency-list-representation-greedy-algo-6/

// Follow Up: Maximum Spanning Tree (Just change in the Initializations & Relational Operators directions)
// https://www.geeksforgeeks.org/maximum-spanning-tree-using-prims-algorithm/

public class PrimsAlgorithm {
    /** ******************************** Prims Algorithm: Brute Force ***********************************
     * We are kind of Doing BFS
     * Time Complexity: O(V^2 + E)
     * Space Complexity: O(3 * V) = O(V)
     */
    public int primsAlgorithm_V1(int V, ArrayList<ArrayList<Vertex>> adjList){
        // Starting vertex of MST can be chosen any vertex in range [0, V-1]
        int startVertex = new Random().nextInt(0, V);

        // "MST_EdgeWeightFromParent" array holds the weight/cost of the MST between the vertex & its parent vertex (given by MST_Parent[])
        // (initialized to INT_MAX, except the index 0 which is set with value of zero)
        int[] MST_EdgeWeightFromParent = new int[V];

        // Boolean array which indicates whether a vertex is already included in MST or not, initialized to false
        boolean[] MST_Included = new boolean[V];

        // Array to store constructed MST
        // "MST_Parent[]" indicates the parent of a particular vertex in the MST (initialized to -1,
        // to indicate that parent not found yet).  The parent array is the output array which is used to show the constructed MST.
        int[] MST_Parent = new int[V];

        // Initializations
        for (int vertex = 0; vertex < V; vertex++){
            MST_Parent[vertex] = -1;
            MST_EdgeWeightFromParent[vertex] = Integer.MAX_VALUE;
        }
        // Always include first start vertex in MST. Make edge weight 0 so that this vertex is picked as
        // first vertex
        MST_EdgeWeightFromParent[startVertex] = 0;

        // The MST will have V vertices & V-1 Edges. So, we need to connect all V vertices by V-1 edges.
        // So, we loop for V-1 times
        for (int edge = 1; edge <= V - 1; edge++){

            // Finding the vertex with "Minimum Edge Weight from its parent", from all the vertices
            // that are not yet included in MST
            int minEdgeWeightVertex = -1, minEdgeWeight = Integer.MAX_VALUE;
            for (int vertex = 0; vertex < V; vertex++)
                if (!MST_Included[vertex]  &&  MST_EdgeWeightFromParent[vertex] < minEdgeWeight){
                    minEdgeWeightVertex = vertex;
                    minEdgeWeight = MST_EdgeWeightFromParent[vertex];
                }

            // Add the picked vertex to the MST Set
            MST_Included[minEdgeWeightVertex] = true;

            // Update Edge weight b/w adjacent vertices & its parent vertex (which is picked vertex).
            // Consider only those vertices which are not yet included in MST
            for (Vertex neighbour : adjList.get(minEdgeWeightVertex)){
                if (!MST_Included[neighbour.vertex]  &&  MST_EdgeWeightFromParent[neighbour.vertex] > neighbour.edgeWeight){
                    MST_EdgeWeightFromParent[neighbour.vertex] = neighbour.edgeWeight;
                    MST_Parent[neighbour.vertex] = minEdgeWeightVertex;
                }
            }
        }

        // Sum of all Edge weights in MST_EdgeWeightFromParent[] will be the Cost of construction of MST
        int MST_ConstructionCost = 0;

        // Print the constructed MST stored in MST_parent[], this will store all the edges in our MST
        System.out.println("Edges in MST are:");
        for (int vertex = 0; vertex < V; vertex++) {
            if (vertex != startVertex)
                System.out.println(vertex + " <---> " + MST_Parent[vertex]);

            MST_ConstructionCost += MST_EdgeWeightFromParent[vertex];
        }
        return MST_ConstructionCost;
    }


    /** ******************************** Prims Algorithm: Efficient ***********************************
     * The idea is to traverse all vertices of graph using BFS and use a Min Heap to store the
       vertices not yet included in MST
     * Time Complexity: O(V*log(V) + E)
     * Space Complexity: O(3 * V) = O(V)
     */
    public int primsAlgorithm_V2(int V, ArrayList<ArrayList<Vertex>> adjList){
        // Starting vertex of MST can be chosen any vertex in range [0, V-1]
        int startVertex = new Random().nextInt(0, V);

        // "MST_EdgeWeightFromParent" array holds the weight/cost of the MST between the vertex & its parent vertex (given by MST_Parent[])
        // (initialized to INT_MAX, except the index 0 which is set with value of zero)
        int[] MST_EdgeWeightFromParent = new int[V];

        // Boolean array which indicates whether a vertex is already included in MST or not, initialized to false
        boolean[] MST_Included = new boolean[V];

        // Array to store constructed MST
        // "MST_Parent[]" indicates the parent of a particular vertex in the MST (initialized to -1,
        // to indicate that parent not found yet).  The parent array is the output array which is used to show the constructed MST.
        int[] MST_Parent = new int[V];

        // Initializations
        for (int vertex = 0; vertex < V; vertex++){
            MST_Parent[vertex] = -1;
            MST_EdgeWeightFromParent[vertex] = Integer.MAX_VALUE;
        }
        // Always include first start vertex in MST. Make edge weight 0 so that this vertex is picked as
        // first vertex
        MST_EdgeWeightFromParent[startVertex] = 0;


        // IMP: Min-Heap will store the Vertices that will be part of our MST & not graph
        // We can do better by storing the Vertices that are not included in our MST yet inside a MinHeap
        // As we are just finding the vertex with minimum edge from parent
        PriorityQueue<Vertex> minHeap = new PriorityQueue<>((a, b) -> a.edgeWeight - b.edgeWeight);
        minHeap.add(new Vertex(startVertex, MST_EdgeWeightFromParent[startVertex]));
        // minHeap.add(new Vertex(0, MST_EdgeWeightFromParent[0]));     // if starting from vertex 0

        while (!minHeap.isEmpty()){
            // Extract the vertex from MinHeap with "Minimum Edge Weight from its parent", from all the
            // vertices that are not yet included in MST
            int minEdgeWeightVertex = minHeap.remove().vertex;

            // Add the picked vertex to the MST Set
            MST_Included[minEdgeWeightVertex] = true;

            // Update Edge weight b/w adjacent vertices & its parent vertex (which is picked vertex).
            // Consider only those vertices which are not yet included in MST
            for (Vertex neighbour : adjList.get(minEdgeWeightVertex)){
                if (!MST_Included[neighbour.vertex]  &&  MST_EdgeWeightFromParent[neighbour.vertex] > neighbour.edgeWeight){
                    MST_Parent[neighbour.vertex] = minEdgeWeightVertex;
                    MST_EdgeWeightFromParent[neighbour.vertex] = neighbour.edgeWeight;
                    minHeap.add(new Vertex(neighbour.vertex, MST_EdgeWeightFromParent[neighbour.vertex]));
                }
            }
        }

        // Sum of all Edge weights in MST_EdgeWeightFromParent[] will be the Cost of construction of MST
        int MST_ConstructionCost = 0;

        // Print the constructed MST stored in MST_parent[], this will store all the edges in our MST
        System.out.println("Edges in MST are:");
        for (int vertex = 0; vertex < V; vertex++) {
            if (vertex != startVertex)
                System.out.println(vertex + " <---> " + MST_Parent[vertex]);

            MST_ConstructionCost += MST_EdgeWeightFromParent[vertex];
        }
        return MST_ConstructionCost;
    }


    static class Vertex{
        int vertex;         // Stores destination vertex in adjacency list
        int edgeWeight;     // Stores weight of  "vertex <--> destination_vertex" in the adjacency list
        public Vertex(int vertex, int edgeWeight) {
            this.vertex = vertex;
            this.edgeWeight = edgeWeight;
        }
    }
}
