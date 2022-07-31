package Graphs.MinimumSpanningTree;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;

// PRE_REQUISITE: DISJOINT SET DATA STRUCTURE
// Kruskal Algorithm is a Greedy Algorithm
// https://youtu.be/1KRmCzBl_mQ
// Great Reading: https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/
// IMP: https://www.geeksforgeeks.org/difference-between-prims-and-kruskals-algorithm-for-mst/#:~:text=Prim

public class KruskalAlgorithm {
    /** *********************************** KRUSKAL ALGORITHM ***************************************
     * 1. Sort all the edges in non-decreasing order of their weight.
     * 2. Pick the smallest edge. Check if it forms a cycle with the spanning tree formed so far.
          If cycle is not formed, include this edge. Else, discard it.
          This step uses a Disjoint Set Union Data Structure.
     * 3. Repeat step2 until there are (V-1) edges in the spanning tree OR until all edges has been processed.
     *
     * Time Complexity: O(V + E) + O(E*log(E) + E)  =  O(E * log(E))
        * If we ignored the cost of generating array-list of Edges this will take O(V + E) time
        * Sorting of all the 'E' edges will take O(E * log(E)) time
        *
     * Space Complexity: O(V + E)
        * If we ignore the output array of Edges in constructed MST
        * O(E) for Arraylist of Edges (which we are sorting)
        * O(V) for Disjoint set (rank[] & parent[] array)
     */
    public int kruskalAlgorithm(int V, ArrayList<ArrayList<int[]>> adjList){
        // Construct ArrayList<Edge> from the adjacencyList of graphs
        ArrayList<Edge> edges = convertAdjacencyListToEdgeList(V, adjList);

        // Sort all the Edges in increasing order of weights (uses Comparable.compareTo() function)
        Collections.sort(edges);

        // Disjoint Set Data structure to avoid cycle in the MST construction
        DisjointSet disjointSet = new DisjointSet(V);

        // Output ArrayList to store the Edges present in our MST
        ArrayList<Edge> MST_Edges = new ArrayList<>();
        // Cost of Construction of MST
        int MST_ConstructionCost = 0;

        // Greedy Choice is to pick the smallest weight edge that does not cause a cycle in the MST
        // constructed so far.
        // There is no need to run till all the edges, we can stop when number of edges included in MST
        // becomes (V - 1). This can be done via a counter & "for loop" for "edges ArrayList"
        // for (int i = 0; i < edges.size() && edgeCount < V; i++){}
        for (Edge edge: edges){
            if (!disjointSet.belongsToMST(edge.startVertex, edge.endVertex)){
                MST_ConstructionCost += edge.edgeWeight;
                MST_Edges.add(edge);
                disjointSet.union(edge.startVertex, edge.endVertex);
            }
        }
        return MST_ConstructionCost;
    }


    private ArrayList<Edge> convertAdjacencyListToEdgeList(int V, ArrayList<ArrayList<int[]>> adjList) {
        ArrayList<Edge> edges = new ArrayList<>();
        HashSet<Edge> set = new HashSet<>();

        for (int vertex = 0; vertex < V; vertex++){
            for (int[] neighbour : adjList.get(vertex)){
                Edge edge = new Edge(vertex, neighbour[0], neighbour[1]);

                if (!set.contains(edge)){
                    edges.add(edge);
                    set.add(edge);
                }
            }
        }
        return edges;
    }


    // *****************************  Structure of the Edges of the Graph  *****************************
    // "Edge" Structure for Undirected graph, for Directed graph see "Bellman Algorithm"
     static class Edge implements Comparable<Edge>{
        private final int startVertex, endVertex, edgeWeight;

        public Edge(int startVertex, int endVertex, int edgeWeight) {
            this.startVertex = startVertex;
            this.endVertex = endVertex;
            this.edgeWeight = edgeWeight;
        }

        // Implementing Comparable for Sorting Edges in Increasing order of "Weights"
        @Override
        public int compareTo(Edge o) {
            return this.edgeWeight - o.edgeWeight;
        }

        // For implementing HashSet<Edge> to convert adjacency list into a ArrayList of Edges
        @Override
        public boolean equals(Object o){
            if (o == null || o.getClass() != this.getClass())
                return false;
            Edge edge = (Edge) o;

            // Since the graph is an Undirected graph, we need to handle two case separately
            // For edges like (start, end, edgeWeight) = (1, 2, 10) and (1, 2, 10)  both are same
            boolean condition1 = this.startVertex == edge.startVertex && this.endVertex == edge.endVertex;

            // For edges like (start, end, edgeWeight) = (1, 2, 10) and (2, 1, 10)  they both too are same
            boolean condition2 = this.startVertex == edge.endVertex && this.endVertex == edge.startVertex;

            return (condition1 || condition2)  &&  this.edgeWeight == edge.edgeWeight;
        }

        // Hashing function
        @Override
        public int hashCode(){
            return Objects.hash(startVertex, endVertex, edgeWeight);
        }
    }


    // ******************************** Disjoint Set Data Structure ********************************
    static class DisjointSet{
        private final int[] parent;
        private final int[] rank;

        public DisjointSet(int V){
            this.rank = new int[V];
            this.parent = new int[V];

            for (int i = 0; i < V; i++)
                parent[i] = i;
        }

        public int parentOf(int u){
            if (u == parent[u])
                return u;

            return parent[u] = parentOf(parent[u]);
        }

        // A function that does union of two sets of x and y (uses union by rank)
        public void union(int u, int v){
            int u_parent = parentOf(u);
            int v_parent = parentOf(v);

            if (u_parent == v_parent)
                return;

            if (rank[u_parent] > rank[v_parent])
                parent[v_parent] = u_parent;
            else if (rank[v_parent] > rank[u_parent])
                parent[u_parent] = v_parent;
            else{
                parent[u_parent] = v_parent;
                rank[v_parent]++;
            }
        }

        public boolean belongsToMST(int u, int v){
            return parentOf(u) == parentOf(v);
        }
    }
}
