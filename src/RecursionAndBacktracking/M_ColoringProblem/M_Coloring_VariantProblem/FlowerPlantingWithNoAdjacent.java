package RecursionAndBacktracking.M_ColoringProblem.M_Coloring_VariantProblem;

// https://youtu.be/wuVwUK25Rfc
// https://takeuforward.org/data-structure/m-coloring-problem/
// https://www.geeksforgeeks.org/m-coloring-problem-backtracking-5/

public class FlowerPlantingWithNoAdjacent {
    /*
     * There are 'n' vertices/garden with values starting from 1 to n
     * We have been given just Edges (links between gardens) as Graph.
     * So, we consider the values of nodes as the indices of the adjacency matrix (1 to n)
     * This is a variation of m-coloring graph problem, aka 4-Coloring problem.
     *
     * Time Complexity: O(n^4)       Since we are applying 4 colors on each of the 'n' gardens/vertex
     * Space Complexity: O(n) + O(n)  =  O(n)
     * One O(n) space due to Map used to keep track of flower-type given to garden/vertex
     * Another O(n) due to Recursion stack space, depth of recursion wll be at most 'n'
     */
    public int[] gardenWithNoAdjacentFlowers(int n, int[][] graphEdges) {
        // Map to keep track of flower type given to each garden
        int[] flowerPlacedMap = new int[n];
        // 4-coloring problem, There are only 4-types of FLowers denoted by numbers from 1 to 4
        int m = 4;

        // whether all the 'n Gardens' can be decorated with 4 types of flowers
        boolean gardenDecorated = canDecorateGardensWithFlowers(1, m, n, flowerPlacedMap, graphEdges);
        // return the arrangements of flowers in each garden (arrangement of colors in case of M-coloring Problem)
        return flowerPlacedMap;
    }

    private boolean canDecorateGardensWithFlowers(int garden, int m, int n, int[] flowerPlacedMap, int[][] graphEdges){
        // If we have decorated all the N vertices/gardens, return true.
        if (garden == n + 1)
            return true;

        // Trying every flower type on the current vertex/garden(vertex number to be precise) from 1 to m with the help of a 'for loop'
        for (int flowerType = 1; flowerType <= m; flowerType++){

            // If it is possible to decorate the current garden/vertex with current flowerType-type i.e, none of the adjacent vertex have the same color.
            if (canPlaceFlowerInGarden(garden, flowerType, flowerPlacedMap, graphEdges)){
                // Then color the current garden/vertex with current flower-type, using a flower Map (Array Map)
                // "garden - 1" due to 1-based indexing of gardens
                flowerPlacedMap[garden - 1] = flowerType;

                // After decorating the current garden/vertex, try to decorate the next garden/vertex (& hence all the 'n' gardens)
                // using same steps via recursion
                if (canDecorateGardensWithFlowers(garden + 1, m, n, flowerPlacedMap, graphEdges))
                    return true;

                // If we are unable to decorate the next garden/vertex, then we un-decorate current garden/vertex
                // and try to decorate the current garden/vertex with next flower-type
                flowerPlacedMap[garden - 1] = 0;
            }
        }
        // If every flower-type from 1 to m have been tried on current garden/vertex, and it was not possible to
        // decorate it with any of the m types of flowers then return false.
        return false;
    }

    private boolean canPlaceFlowerInGarden(int garden, int flowerType, int[] flowerPlacedMap, int[][] graphEdges){
        // For each adjacent neighbour of current vertex/garden (given by edges in adjacency matrix)
        // check whether any of the adjacent neighbour vertex/garden has the same flower-type or not
        for (int[] graphEdge : graphEdges){
            // If 1st vertex of the edge is the current garden
            // if the adjacent neighbour garden/vertex has same flower-type, then current
            // flower-type can't be used to decorate current garden
            if (graphEdge[0] == garden) {
                int neighbourGarden = graphEdge[1];
                if (flowerPlacedMap[neighbourGarden - 1] == flowerType)      // 1-based indexing
                    return false;
            }

            // If 2ns vertex of the edge is the current garden
            // if the adjacent neighbour garden/vertex has same flower-type, then current
            // flower-type can't be used to decorate current garden
            if (graphEdge[1] == garden) {
                int neighbourGarden = graphEdge[0];
                if (flowerPlacedMap[neighbourGarden - 1] == flowerType)     // 1-based indexing
                    return false;
            }
        }
        return true;
    }
}

// TODO: To optimize this solution into something better Time Complexity
