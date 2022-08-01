package Graphs.GraphQuestions.CourseSchedule;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

// Pre-requisite : "Detect Cycle in Directed Graph" (under graph algorithm)

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Model the problem as the Graph problem
        int V = numCourses;
        int[][] edges = prerequisites;

        // Adjacency list
        ArrayList<Integer>[] adjList = adjacencyList(V, edges);

        // Check for Cycle in case of Directed Graph (DAG)
        return checkCycleInDirectedGraph(V, adjList);
    }


    // Exactly same as check for Cycle in Directed Graph
    // BFS approach (Topological Sorting) for check of Cycle in Directed Graph
    private boolean checkCycleInDirectedGraph(int V, ArrayList<Integer>[] adjList){
        int[] inDegree = new int[V];
        
        for (int vertex = 0; vertex < V; vertex++)
            for (int neighbour : adjList[vertex])
                inDegree[neighbour]++;
        
        Queue<Integer> bfsQueue = new ArrayDeque<>();
        
        for (int vertex = 0; vertex < V; vertex++)
            if (inDegree[vertex] == 0)
                bfsQueue.add(vertex);
        
        int verticesCount = 0;
        
        while (!bfsQueue.isEmpty()){
            int vertex = bfsQueue.remove();
            verticesCount++;
            
            for (int neighbour : adjList[vertex]){
                inDegree[neighbour]--;
                
                if (inDegree[neighbour] == 0)
                    bfsQueue.add(neighbour);
            }
        }
        return verticesCount == V; 
    }


    // Construct an Adjacency List for the Directed graph from the edges
    private ArrayList<Integer>[] adjacencyList(int V, int[][] edges){
        ArrayList<Integer>[] adjList = new ArrayList[V];
        
        for (int i = 0; i < V; i++)
            adjList[i] = new ArrayList<>();
        
        for (int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            adjList[u].add(v);
        }
        return adjList;
    }
}