package RecursionAndBacktracking.M_ColoringProblem;
import java.util.ArrayList;

// https://youtu.be/wuVwUK25Rfc
// https://takeuforward.org/data-structure/m-coloring-problem/
// https://www.geeksforgeeks.org/m-coloring-problem-backtracking-5/

public class M_ColoringProblem_AdjacencyList {
    /*
     * Suppose, there are 'n' nodes/vertex with values starting from 0 to n-1
     * We have been given adjacency List as Graph.
     * So, we consider the values of nodes/vertex as the indices of the adjacency matrix (0 to n-1)
     *
     * Time Complexity: O(n^m)       Since we are applying m colors on each of the 'n' nodes/vertex
     * Space Complexity: O(n) + O(n)  =  O(n)
     * One O(n) space due to Map used to keep track of color given to nodes/vertex
     * Another O(n) due to Recursion stack space, depth of recursion wll be at most 'n'
     */
    public boolean graphColoring(ArrayList<Integer>[] adjacencyList , int m, int n) {
        // Map to keep track of color given to each vertex
        int[] vertexColorMap = new int[n];

        // Basically starting from vertex/node 0 color one by one the different vertices.
        return canColorGraph(0, n, m, adjacencyList, vertexColorMap);
    }

    private boolean canColorGraph(int node, int n, int m, ArrayList<Integer>[] adjacencyList, int[] vertexColorMap){
        // If we have colored all the N vertices/nodes, return true.
        if (node == n)
            return true;

        // Trying every color on the current vertex(vertex number to be precise) from 1 to m with the help of a 'for loop'
        for (int color = 1; color <= m ; color++){

            // If it is possible to color the current node/vertex with current color i.e, none of the adjacent vertex have the same color.
            if (canColorNode(node, color, vertexColorMap, adjacencyList)){
                // Then color the current vertex with current color, using a color Map (Array Map)
                vertexColorMap[node] = color;

                // After coloring the current node, try to color the next node/vertex (& hence all the 'n' nodes)
                // using same steps via recursion
                if (canColorGraph(node + 1, n, m, adjacencyList, vertexColorMap))
                    return true;

                // If we are unable to color the next node/vertex, then take off the color of current node
                // and try to color the current node/vertex with next color
                vertexColorMap[node] = 0;
            }
        }
        // If every color from 1 to m have been tried on current node/vertex, and it was not possible to
        // color it with any of the m colors then return false.
        return false;
    }

    private boolean canColorNode(int nodeColored, int color, int[] vertexColorMap, ArrayList<Integer>[] adjacencyList){
        // For each adjacent neighbour of current vertex (given by vertex in adjacency List)
        // check whether any of the adjacent neighbour vertex has the same color or not
        for (int neighbour : adjacencyList[nodeColored]){

            // if any of the adjacent neighbour vertex has same color, then current color can't be colored with same color
            if (vertexColorMap[neighbour] == color)
                return false;
        }
        return true;
    }
}
